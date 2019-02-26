package neighbors.com.spacetrader.ui.universe;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.florent37.shapeofview.shapes.CircleView;

import neighbors.com.spacetrader.model.SolarSystem;

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
        display.setBorderWidth(12);
        display.setBorderColor(this.getColor());
        display.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) display.getLayoutParams();
        layout.height = 30;
        layout.width = 30;
        display.setX(system.getLocation().getX() * 4 + 200);
        display.setY(system.getLocation().getY() * 5 + 400);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog info = new MaterialDialog(window);
                info.title(null, system.getName());
                info.message(null, "TechLvl: " + system.getTechLvl() + "\n" +
                        "Resource: " + system.getResource().getName(), false, 1F);
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
                return Color.WHITE;
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
        return Color.BLACK;
    }
}
