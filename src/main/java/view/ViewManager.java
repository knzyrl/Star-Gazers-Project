package view;

import use_case.events.EventsOutputData;
import use_case.star_chart.StarChartOutputData;

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

    public void displayStarChart(StarChartOutputData starChartOutputData) {
        cardLayout.show(views, "display star chart");
        DisplayStarChartView displayStarChartView = (DisplayStarChartView) getCurrentCard();
        displayStarChartView.refresh(starChartOutputData);
    }

    public void displayEvents(EventsOutputData eventsOutputData) {
        cardLayout.show(views, "display events");
        DisplayEventsView displayEventsView = (DisplayEventsView) getCurrentCard();
        displayEventsView.refresh(eventsOutputData);
    }

    private JPanel getCurrentCard() {
        JPanel result = null;
        for (Component component : views.getComponents()) {
            if (component.isVisible()) {
                result = (JPanel) component;
            }
        }
        return result;
    }
}
