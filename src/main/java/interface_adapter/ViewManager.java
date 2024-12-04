package interface_adapter;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.List;

import javax.swing.JPanel;

import use_case.apod_date.ApodOutputData;
import use_case.events.EventsOutputData;
import use_case.geocoding.GeocodingOutputData;
import use_case.moon_phase.MoonPhaseOutputData;
import use_case.near_earth_objects.NearEarthObjectsOutputData;
import use_case.star_chart.StarChartOutputData;
import view.ApodView;
import view.DisplayEventsView;
import view.DisplayGeocoderView;
import view.DisplayMoonPhaseView;
import view.DisplayNearEarthObjectsView;
import view.DisplayStarChartView;
import view.FailView;

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

    /**
     * Method to display the Astronomical Picture/Video of the day to the user.
     * @param apodOutputData is the output data to be presented.
     */
    public void displayApod(ApodOutputData apodOutputData) {
        cardLayout.show(views, "display apod");
        final ApodView displayApodView = (ApodView) getCurrentCard();
        displayApodView.displayApod(apodOutputData.getTitle(), apodOutputData.getDescription(),
                apodOutputData.getMediaType(), apodOutputData.getUrl(), apodOutputData.getThumbnailUrl());
    }

    /**
     * Method to display the Near-Earth Objects list to the user.
     * @param neoOutputData output data to be presented
     */
    public void displayNearEarthObjectsData(List<NearEarthObjectsOutputData> neoOutputData) {
        cardLayout.show(views, "display NEO view");
        final DisplayNearEarthObjectsView displayNearEarthObjectsView = (DisplayNearEarthObjectsView) getCurrentCard();
        displayNearEarthObjectsView.displayNearEarthObjectsData(neoOutputData);
    }

    /**
     * Method to display the results of the geocoding use case to the user.
     * @param geocodingOutputData output data to be presented
     */
    public void displayLocation(GeocodingOutputData geocodingOutputData) {
        cardLayout.show(views, "display geocoded information");
        final DisplayGeocoderView displayGeocoderView = (DisplayGeocoderView) getCurrentCard();
        displayGeocoderView.displayLocation(geocodingOutputData);
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
