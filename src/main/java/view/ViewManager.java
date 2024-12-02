package view;

import use_case.events.EventsOutputData;
import use_case.moon_phase.MoonPhaseOutputData;
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

    public void showFailView(String errorMessage) {
        cardLayout.show(views, "fail");
        FailView failView = (FailView) getCurrentCard();
        failView.refresh(errorMessage);
    }

    public void displayStarChart(StarChartOutputData starChartOutputData) {
        cardLayout.show(views, "display star chart");
        DisplayStarChartView displayStarChartView = (DisplayStarChartView) getCurrentCard();
        displayStarChartView.refresh(starChartOutputData);
    }

    public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) {
        cardLayout.show(views, "display Moon Phase");
        DisplayMoonPhaseView displayMoonPhaseView = (DisplayMoonPhaseView) getCurrentCard();
        displayMoonPhaseView.refresh(moonPhaseOutputData);
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
