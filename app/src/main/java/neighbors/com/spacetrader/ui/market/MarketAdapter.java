package neighbors.com.spacetrader.ui.market;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import androidx.recyclerview.widget.RecyclerView;
import neighbors.com.spacetrader.R;
import neighbors.com.spacetrader.model.Good;
import neighbors.com.spacetrader.model.Market;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.TransactionResponse;

/**
 * Market Adapter to display trading info
 */
public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {
    private final Context context;
    private final Market market;
    private final Player player;
    private final MarketViewUpdate update;

    /**
     * Creates an instance of MarketAdapter with a given context, market,
     * player, and marketviewupdate
     * @param context The context of the app
     * @param market The market to use
     * @param player The player to interact with
     * @param update The MarketViewUpdate update
     */
    public MarketAdapter(Context context, Market market, Player player, MarketViewUpdate update) {
        this.market = market;
        this.context = context;
        this.player = player;
        this.update = update;
    }

    @Override
    public MarketAdapter.MarketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.market_item, parent, false);
        return new MarketViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MarketViewHolder holder, int position) {
        holder.bind(market.getGood(position));
    }

    @Override
    public int getItemCount() {
        return market.goodsCount();
    }

    /**
     *
     */
    interface MarketViewUpdate {
        void updateCredits();
    }

    public class MarketViewHolder extends RecyclerView.ViewHolder {
        private final TextView item;
        private final TextView bPrice;
        private final TextView sPrice;
        private final Button trade;
        private final EditText amountEditText;

        /**
         * Creates an instance of MarketViewHolder to use in the
         * RecyclerView
         * @param v the view to grab layout items from
         */
        public MarketViewHolder(View v) {
            super(v);
            item = v.findViewById(R.id.item);
            trade = v.findViewById(R.id.tradeButton);
            bPrice = v.findViewById(R.id.bPrice);
            sPrice = v.findViewById(R.id.sPrices);
            amountEditText = v.findViewById(R.id.amount);

//            v.setOnClickListener(this);
        }

        /**
         * Binds a good to the view to display to the user
         * @param good the Good to display
         */
        public void bind(final Good good) {
            item.setText(good.getName());
            sPrice.setText(String.valueOf(market.getGoodSellPrice(good)));
            bPrice.setText(String.valueOf(market.getGoodBuyPrice(good)));
            trade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tradeOnClick(good);
                }
            });
        }

        /**
         * Handel Trade onClick
         *
         * @param good the good to handel
         */
        private void tradeOnClick(final Good good) {
            if (amountEditText.getText().toString().isEmpty()) {
                showDialog("Please input valid amountEditText of good");
                return;
            }
            final TransactionProcessor transaction = new TransactionProcessor(market, player);
            final int amount = Integer.valueOf(amountEditText.getText().toString().trim());

            AlertDialog alertDialog = new AlertDialog.Builder(context)
                    .setTitle(good.getName())
                    .setMessage(getDialogTradeMessage(good, amount))
                    .setPositiveButton(context.getString(R.string.buy), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TransactionResponse response = transaction.buyGood(good, amount);
                            handleResponse(response);
                        }
                    })
                    .setNegativeButton(context.getString(R.string.sell), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TransactionResponse response = transaction.sellGood(good, amount);
                            handleResponse(response);
                        }
                    }).create();
            alertDialog.show();
        }

        /**
         * Shows a dialog with message
         *
         * @param message message to be displayed
         */
        private void showDialog(String message) {
            MaterialDialog retry = new MaterialDialog(context);
            retry.title(null, "Trade");
            retry.message(null, message, false, 0F);
            retry.show();
        }

        @SuppressLint("DefaultLocale")
        private String getDialogTradeMessage(Good good, int amount) {
            return String.format("Are you sure you want to exchange %s of %s\n" + "You have: %d", amount, good.getName(), player.getQuantity(good));
        }

        private void handleResponse(TransactionResponse response) {
            switch (response) {
                case ERROR:
                    showToast("Some error Happened");
                    break;
                case COMPLETED:
                    showToast("Successful transaction");
                    update.updateCredits();
                    break;
                case NOT_ENOUGH_MONEY:
                    showToast("Not enough credits!");
                    break;
                case NOT_ENOUGH_ITEM:
                    showToast("Not enough of Good!");
                    break;
                case NOT_ENOUGH_SPACE:
                    showToast("Not enough space!");
                    break;
            }

        }

        private void showToast(String message) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
