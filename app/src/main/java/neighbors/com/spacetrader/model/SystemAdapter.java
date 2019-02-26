package neighbors.com.spacetrader.model;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.florent37.shapeofview.shapes.CircleView;

public class SystemAdapter {
    private SolarSystem system;
    private CircleView cView;
    private Context window;

    public SystemAdapter(SolarSystem pSystem, Context pContext) {
        system = pSystem;
        cView = viewSetUp(pContext);
        window = pContext;
    }

    private CircleView viewSetUp(Context con) {
        CircleView display = new CircleView(con);
        display.setBorderWidth(5);
        display.setBorderColor(this.getColor());
        display.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) display.getLayoutParams();
        layout.height = 30;
        display.setX(system.getLocation().getX() * 3 - 200);
        display.setY(system.getLocation().getY() * 4 + 400);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog info = new MaterialDialog(window);
                info.setTitle(system.getName());
                info.show();
            }
        });
        return display;
    }

    public CircleView getView() {
        return cView;
    }

    public int getColor() {
        String tech = system.getTechLvl();
        switch (tech) {
            case "Pre-Agriculture":
                return Color.BLACK;
            case "Agriculture":
                return Color.RED;
            case "Medieval":
                return Color.BLUE;
            case "Renaissance":
                return Color.YELLOW;
            case "Early Industrial":
                return Color.GREEN;
            case "Industrial":
                return Color.MAGENTA;
            case "Post-Industrial":
                return Color.CYAN;
            case "Hi-Tech":
                return Color.LTGRAY;
        }
        return Color.WHITE;
    }
}
