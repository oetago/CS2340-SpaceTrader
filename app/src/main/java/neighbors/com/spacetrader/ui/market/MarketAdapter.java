package neighbors.com.spacetrader.ui.market;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import androidx.recyclerview.widget.RecyclerView;
import neighbors.com.spacetrader.R;
import neighbors.com.spacetrader.model.Good;


public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {
    private Map<Good, Integer> buyGoods;
    private Map<Good, Integer> sellGoods;
    private MarketActivity current;

    public final class MarketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView item;
        TextView bPrice;
        TextView sPrice;
        Button trade;
        EditText amount;
        Good resource;

        public MarketViewHolder(View v) {
            super(v);
            item = v.findViewById(R.id.item);
            trade = v.findViewById(R.id.tradeButton);
            bPrice = v.findViewById(R.id.bPrice);
            sPrice = v.findViewById(R.id.sPrices);
            amount = v.findViewById(R.id.amount);
            trade.setOnClickListener(this);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.equals(trade)) {
                if (amount.getText().toString().equals("")) {
                    MaterialDialog retry = new MaterialDialog(current);
                    retry.title(null, "Please input valid amount of good");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(current);

                builder.setTitle(item.getText());

                builder.setMessage("Are you sure you want to exchange " + String.valueOf(amount.getText()) + " of " + item.getText());

                // Set click listener for alert dialog buttons
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                if (current.getViewModel().buyGood(resource, Integer.valueOf(amount.getText().toString()))) {
                                    current.updateCredits();
                                    Toast.makeText(current, "bought", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(current, "not enough credits", Toast.LENGTH_LONG).show();
                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                if (current.getViewModel().sellGood(resource, Integer.valueOf(amount.getText().toString()))) {
                                    current.updateCredits();
                                    Toast.makeText(current, "sold", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(current, "not enough cargo", Toast.LENGTH_LONG).show();
                                }
                                break;
                        }
                    }
                };
                builder.setPositiveButton("Buy", dialogClickListener);
                builder.setNegativeButton("Sell", dialogClickListener);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }


    public MarketAdapter(Map<Good, Integer> buy, Map<Good, Integer> sell, MarketActivity curr) {
        buyGoods = buy;
        sellGoods = sell;
        current = curr;
    }

    @Override
    public MarketAdapter.MarketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.market_item, parent, false);
        MarketViewHolder vh = new MarketViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MarketViewHolder holder, int position) {
        Set<Good> goodSet = buyGoods.keySet();
        Object[] things = goodSet.toArray();
        Good[] goods = Arrays.copyOf(things, things.length, Good[].class);
        Good good = goods[position];

        holder.item.setText(good.toString());
        holder.resource = good;
        holder.sPrice.setText(String.valueOf(current.getViewModel().getSellPrice(good)));
        holder.bPrice.setText(String.valueOf(current.getViewModel().getBuyPrice(good)));
    }

    @Override
    public int getItemCount() {
        return buyGoods.size();
    }


}
