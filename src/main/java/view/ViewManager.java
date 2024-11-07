package view;

import javax.swing.*;
import java.awt.*;

public class ViewManager {
    private final CardLayout cardLayout;
    private final JPanel views;

    public ViewManager(CardLayout cardLayout, JPanel views) {
        this.cardLayout = cardLayout;
        this.views = views;
    }

    public JPanel getViews() {
        return this.views;
    }

    public void show (String viewName) {
        cardLayout.show(views, viewName);
    }
}
