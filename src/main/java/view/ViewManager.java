package view;

import java.awt.*;

import javax.swing.*;

import use_case.events.EventsOutputData;
import use_case.moon_phase.MoonPhaseOutputData;
import use_case.star_chart.StarChartOutputData;

/**
 * Class for managing the views displayed to the user.
 */
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

    /**
     * Method responsible for displaying a particular view to the user.
     * @param viewName of the view to be displayed.
     */
    public void show(String viewName) {
        cardLayout.show(views, viewName);
    }

    /**
     * Method responsible for showing the Fail View in the case of errors.
     * @param errorMessage to be shown.
     */
    public void showFailView(String errorMessage) {
        cardLayout.show(views, "fail");
        final FailView failView = (FailView) getCurrentCard();
        failView.refresh(errorMessage);
    }

    /**
     * Method to display the Star Chart to the user.
     * @param starChartOutputData is the output data for the Star Chart to be presented.
     */
    public void displayStarChart(StarChartOutputData starChartOutputData) {
        cardLayout.show(views, "display star chart");
        final DisplayStarChartView displayStarChartView = (DisplayStarChartView) getCurrentCard();
        displayStarChartView.refresh(starChartOutputData);
    }

    /**
     * Method to display the Moon Phase to the user.
     * @param moonPhaseOutputData is the output data of the Moon Phase to be presented.
     */
    public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) {
        cardLayout.show(views, "display Moon Phase");
        final DisplayMoonPhaseView displayMoonPhaseView = (DisplayMoonPhaseView) getCurrentCard();
        displayMoonPhaseView.refresh(moonPhaseOutputData);
    }

    /**
     * Method to display Solar/Lunar Events to the user.
     * @param eventsOutputData is the output data of the events to be displayed.
     */
    public void displayEvents(EventsOutputData eventsOutputData) {
        cardLayout.show(views, "display events");
        final DisplayEventsView displayEventsView = (DisplayEventsView) getCurrentCard();
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
