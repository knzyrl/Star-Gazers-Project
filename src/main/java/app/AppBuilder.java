package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.AstronomyPictureApiDataAccessObject; // will need to rename
import data_access.EventsDataAccessObject;
import data_access.GeocoderDataAccessObject;
import data_access.MoonPhaseDataAccessObject;
import data_access.NasaNeoApiDataAccessObject;
import data_access.StarChartDataAccessObject;
import interface_adapter.apod_date.APODController;
import interface_adapter.apod_date.APODPresenter;
import interface_adapter.display_events.DisplayEventsController;
import interface_adapter.display_events.DisplayEventsPresenter;
import interface_adapter.display_moon_phase.DisplayMoonPhaseController;
import interface_adapter.display_moon_phase.DisplayMoonPhasePresenter;
import interface_adapter.display_star_chart.DisplayStarChartController;
import interface_adapter.display_star_chart.DisplayStarChartPresenter;
import interface_adapter.events.EventsController;
import interface_adapter.events.EventsPresenter;
import interface_adapter.fail.FailController;
import interface_adapter.fail.FailPresenter;
import interface_adapter.geocoding.GeocodingController;
import interface_adapter.geocoding.GeocodingPresenter;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.moon_phase.MoonPhaseController;
import interface_adapter.moon_phase.MoonPhasePresenter;
import interface_adapter.near_earth_objects.NearEarthObjectsController;
import interface_adapter.near_earth_objects.NearEarthObjectsPresenter;
import interface_adapter.star_chart.StarChartController;
import interface_adapter.star_chart.StarChartPresenter;
import use_case.apod_date.APODInteractor;
import use_case.events.EventsInteractor;
import use_case.geocoding.GeocodingInteractor;
import use_case.moon_phase.MoonPhaseInteractor;
import use_case.near_earth_objects.NearEarthObjectsInteractor;
import use_case.star_chart.StarChartInteractor;
import view.APODView;
import view.DisplayEventsView;
import view.DisplayGeocoderView;
import view.DisplayMoonPhaseView;
import view.DisplayNearEarthObjectsView;
import view.DisplayStarChartView;
import view.EventsView;
import view.FailView;
import view.GeocoderView;
import view.HomeView;
import view.MoonPhaseView;
import view.NameGeocoderView;
import view.NearEarthObjectsView;
import view.NoAddressFoundView;
import view.StarChartView;
import view.ViewManager;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManager viewManager = new ViewManager(cardLayout, cardPanel);
    private final StarChartDataAccessObject starChartDao = new StarChartDataAccessObject();
    private final EventsDataAccessObject eventsDao = new EventsDataAccessObject();
    private final MoonPhaseDataAccessObject moonPhaseDao = new MoonPhaseDataAccessObject();
    private final GeocoderDataAccessObject geocoderDao = new GeocoderDataAccessObject();
    private final NasaNeoApiDataAccessObject nasaNeoDao = new NasaNeoApiDataAccessObject();
    private HomeView homeView;
    private FailView failView;
    private StarChartView starChartView;
    private MoonPhaseView moonPhaseView;
    private DisplayStarChartView displayStarChartView;
    private EventsView eventsView;
    private DisplayEventsView displayEventsView;
    private APODView apodView;
    private DisplayMoonPhaseView displayMoonPhaseView;
    private GeocoderView geocoderView;
    private NameGeocoderView nameGeocoderView;
    private DisplayGeocoderView displayGeocoderView;
    private NoAddressFoundView noAddressFoundView;
    private NearEarthObjectsView nearEarthObjectsView;
    private DisplayNearEarthObjectsView displayNearEarthObjectsView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Add the Home View to the application.
     * @return this builder
     */
    public AppBuilder addHomeView() {
        // Create the HomeView
        homeView = new HomeView();
        cardPanel.add(homeView, homeView.getViewName());

        // Create and set the HomeController
        final HomePresenter homePresenter = new HomePresenter(viewManager);
        final HomeController homeController = new HomeController(homePresenter);
        homeView.setHomeController(homeController);

        return this;
    }

    /**
     * Adds the Fail View to the application.
     * @return this builder
     */
    public AppBuilder addFailView() {
        failView = new FailView();
        cardPanel.add(failView, failView.getViewName());
        return this;
    }

    /**
     * Adds the APOD View to the application.
     * @return this builder
     */

    public AppBuilder addApodView() { // will need to rename
        apodView = new APODView();
        AstronomyPictureDataAccessObject dataAccessObject = new AstronomyPictureApiDataAccessObject();
        APODPresenter presenter = new APODPresenter(apodView);
        APODInteractor interactor = new APODInteractor(presenter, dataAccessObject, viewManager);
        APODController controller = new APODController(interactor);
        apodView.setController(controller);
        cardPanel.add(apodView, apodView.getViewName());
        return this;
    }

    /**
     * Adds the Star Chart View to the application.
     * @return this builder
     */
    public AppBuilder addStarChartView() {
        starChartView = new StarChartView();
        cardPanel.add(starChartView, starChartView.getViewName());
        return this;
    }

    /**
     * Adds the Moon Phase View to the application.
     * @return this builder
     */
    public AppBuilder addMoonPhaseView() {
        moonPhaseView = new MoonPhaseView();
        cardPanel.add(moonPhaseView, moonPhaseView.getViewName());
        return this;
    }

    /**
     * Adds the Geocoding View to the application.
     * @return this builder
     */
    public AppBuilder addGeocodingView() {
        geocoderView = new GeocoderView();
        cardPanel.add(geocoderView, geocoderView.getViewName());
        return this;
    }

    /**
     * Adds the Display Geocoder View to the application.
     * @return this builder
     */
    public AppBuilder addDisplayGeocoderView() {
        displayGeocoderView = new DisplayGeocoderView();
        cardPanel.add(displayGeocoderView, displayGeocoderView.getViewName());
        return this;
    }

    /**
     * Adds the No Address Found View to the application.
     * @return this builder
     */
    public AppBuilder addNoAddressFoundView() {
        noAddressFoundView = new NoAddressFoundView();
        cardPanel.add(noAddressFoundView, noAddressFoundView.getViewName());
        return this;
    }

    /**
     * Adds the Name Geocoder View to the application.
     * @return this builder
     */
    public AppBuilder addNameGeocoderView() {
        nameGeocoderView = new NameGeocoderView();
        cardPanel.add(nameGeocoderView, nameGeocoderView.getViewName());
        return this;
    }

    /**
     * Adds the Display Star Chart View to the application.
     * @return this builder
     */
    public AppBuilder addDisplayStarChartView() {
        displayStarChartView = new DisplayStarChartView();
        cardPanel.add(displayStarChartView, displayStarChartView.getViewName());
        return this;
    }

    /**
     * Adds the Events View to the application.
     * @return this builder
     */
    public AppBuilder addEventsView() {
        eventsView = new EventsView();
        cardPanel.add(eventsView, eventsView.getViewName());
        return this;
    }

    /**
     * Adds the Display Events View to the application.
     * @return this builder
     */
    public AppBuilder addDisplayEventsView() {
        displayEventsView = new DisplayEventsView();
        cardPanel.add(displayEventsView, displayEventsView.getViewName());
        return this;
    }

    /**
     * Adds the Display Moon Phase View to the application.
     * @return this builder
     */
    public AppBuilder addDisplayMoonPhaseView() {
        displayMoonPhaseView = new DisplayMoonPhaseView();
        cardPanel.add(displayMoonPhaseView, displayMoonPhaseView.getViewName());
        return this;
    }

    /**
     * Adds the NEO View to the application.
     * @return this builder
     */
    public AppBuilder addNeoView() {
        nearEarthObjectsView = new NearEarthObjectsView();
        cardPanel.add(nearEarthObjectsView, nearEarthObjectsView.getViewName());
        return this;
    }

    /**
     * Adds the Display NEO View to the application.
     * @return this builder
     */
    public AppBuilder addDisplayNeoView() {
        displayNearEarthObjectsView = new DisplayNearEarthObjectsView();
        cardPanel.add(displayNearEarthObjectsView, displayNearEarthObjectsView.getViewName());
        return this;
    }

    /**
     * Adds the Home Interface to the application.
     * @return this builder
     */
    public AppBuilder addHomeInterface() {
        final HomePresenter homePresenter = new HomePresenter(viewManager);
        final HomeController homeController = new HomeController(homePresenter);
        homeView.setHomeController(homeController);
        return this;
    }

    /**
     * Adds the Fail Interface to the application.
     * @return this builder
     */
    public AppBuilder addFailInterface() {
        final FailPresenter failPresenter = new FailPresenter(viewManager);
        final FailController failController = new FailController(failPresenter);
        failView.setFailViewController(failController);
        return this;
    }

    /**
     * Adds the Star Chart Use Case to the application.
     * @return this builder
     */
    public AppBuilder addStarChartUseCase() {
        final StarChartPresenter starChartPresenter = new StarChartPresenter(viewManager);
        final StarChartInteractor starChartInteractor = new StarChartInteractor(starChartDao, starChartPresenter);
        final StarChartController starChartController = new StarChartController(starChartInteractor);
        starChartView.setStarChartController(starChartController);

        return this;
    }

    /**
     * Adds the Moon Phase Use Case to the application.
     * @return this builder
     */
    public AppBuilder addMoonPhaseUseCase() {
        final MoonPhasePresenter moonPhasePresenter = new MoonPhasePresenter(viewManager);
        final MoonPhaseInteractor moonPhaseInteractor = new MoonPhaseInteractor(moonPhaseDao, moonPhasePresenter);
        final MoonPhaseController moonPhaseController = new MoonPhaseController(moonPhaseInteractor);
        moonPhaseView.setMoonPhaseController(moonPhaseController);

        return this;
    }

    /**
     * Adds the Geocoder Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGeocoderUseCase() {

        final GeocodingPresenter geocodingPresenter = new GeocodingPresenter(viewManager, displayGeocoderView);
        final GeocodingInteractor geocodingInteractor = new GeocodingInteractor(geocoderDao, geocodingPresenter);
        final GeocodingController geocodingController =
                new GeocodingController(geocodingInteractor, geocodingPresenter);

        displayGeocoderView.setDisplayStarChartController(geocodingController);

        geocoderView.setGeoCodingController(geocodingController);
        noAddressFoundView.setGeocodingController(geocodingController);
        nameGeocoderView.setGeoCodingController(geocodingController);

        return this;
    }

    /**
     * Adds the Display Star Chart Interface to the application.
     * @return this builder
     */
    public AppBuilder addDisplayStarChartInterface() {
        final DisplayStarChartPresenter displayStarChartPresenter = new DisplayStarChartPresenter(viewManager);
        final DisplayStarChartController displayStarChartController =
                new DisplayStarChartController(displayStarChartPresenter);
        displayStarChartView.setDisplayStarChartController(displayStarChartController);
        return this;
    }

    /**
     * Adds the Events Use Case to the application.
     * @return this builder
     */
    public AppBuilder addEventsUseCase() {
        final EventsPresenter eventsPresenter = new EventsPresenter(viewManager);
        final EventsInteractor eventsInteractor = new EventsInteractor(eventsDao, eventsPresenter);
        final EventsController eventsController = new EventsController(eventsInteractor);
        eventsView.setEventsController(eventsController);
        return this;
    }

    /**
     * Adds the Display Events Interface to the application.
     * @return this builder
     */
    public AppBuilder addDisplayEventsInterface() {
        final DisplayEventsPresenter displayEventsPresenter = new DisplayEventsPresenter(viewManager);
        final DisplayEventsController displayEventsController = new DisplayEventsController(displayEventsPresenter);
        displayEventsView.setDisplayEventsController(displayEventsController);
        return this;
    }

    /**
     * Adds the Display Moon Phase Interface to the application.
     * @return this builder
     */
    public AppBuilder addDisplayMoonPhaseInterface() {
        final DisplayMoonPhasePresenter displayMoonPhasePresenter = new DisplayMoonPhasePresenter(viewManager);
        final DisplayMoonPhaseController displayMoonPhaseController =
                new DisplayMoonPhaseController(displayMoonPhasePresenter);
        displayMoonPhaseView.setDisplayMoonPhaseController(displayMoonPhaseController);
        return this;
    }

    /**
     * Adds the NEO Use Case to the application.
     * @return this builder
     */
    public AppBuilder addNeoUseCase() {
        final NearEarthObjectsPresenter nearEarthObjectsPresenter = new NearEarthObjectsPresenter(viewManager);
        nearEarthObjectsPresenter.setDisplayNeoView(displayNearEarthObjectsView);
        final NearEarthObjectsInteractor neoInteractor =
                new NearEarthObjectsInteractor(nasaNeoDao, nearEarthObjectsPresenter);
        final NearEarthObjectsController nearEarthObjectsController = new NearEarthObjectsController(neoInteractor);
        nearEarthObjectsView.setNearEarthObjectsController(nearEarthObjectsController);
        return this;
    }

    /**
     * Adds the Display NEO Interface to the application.
     * @return this builder
     */
    public AppBuilder addDisplayNeoInterface() {
        final NearEarthObjectsPresenter displayNearEarthObjectsPresenter = new NearEarthObjectsPresenter(viewManager);
        displayNearEarthObjectsPresenter.setDisplayNeoView(displayNearEarthObjectsView);
        displayNearEarthObjectsView.setPresenter(displayNearEarthObjectsPresenter);
        return this;
    }

    /**
     * Creates the JFrame for the application.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Star Gazers App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        return application;
    }
}
