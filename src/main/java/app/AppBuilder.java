package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.AstronomyPictureApiDataAccessObject;
import data_access.EventsDataAccessObject;
import data_access.GeocoderDataAccessObject;
import data_access.MoonPhaseDataAccessObject;
import data_access.NasaNeoDataAccessObject;
import data_access.StarChartDataAccessObject;
import interface_adapter.astronomyPicture_date.AstronomyPictureController;
import interface_adapter.astronomyPicture_date.AstronomyPicturePresenter;
import interface_adapter.display_events.DisplayEventsController;
import interface_adapter.display_events.DisplayEventsPresenter;
import interface_adapter.display_moon_phase.DisplayMoonPhaseController;
import interface_adapter.display_moon_phase.DisplayMoonPhasePresenter;
import interface_adapter.display_star_chart.DisplayStarChartController;
import interface_adapter.display_star_chart.DisplayStarChartPresenter;
import interface_adapter.events.EventsController;
import interface_adapter.events.EventsPresenter;
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
import use_case.apod_date.AstronomyPictureInteractor;
import use_case.events.EventsInteractor;
import use_case.geocoding.GeocodingInteractor;
import use_case.moon_phase.MoonPhaseInteractor;
import use_case.near_earth_objects.NearEarthObjectsInteractor;
import use_case.star_chart.StarChartInteractor;
import view.APODView;
import view.DisplayEventsView;
import view.DisplayGeocoderView;
import view.DisplayMoonPhaseView;
import view.DisplayNEOView;
import view.DisplayStarChartView;
import view.EventsView;
import view.GeocoderView;
import view.HomeView;
import view.MoonPhaseView;
import view.NEOView;
import view.NameGeocoderView;
import view.NoAddressFoundView;
import view.StarChartView;
import view.ViewManager;

/**
 * The AppBuilder class constructs and initializes the components of the Star Gazers app,
 * including views, controllers, presenters, and interactors.
 * This class uses the Builder design pattern to modularly add and configure
 * various application features.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManager viewManager = new ViewManager(cardLayout, cardPanel);
    private final StarChartDataAccessObject starchartdao = new StarChartDataAccessObject();
    private final EventsDataAccessObject eventsdao = new EventsDataAccessObject();
    private final MoonPhaseDataAccessObject moonphasedao = new MoonPhaseDataAccessObject();
    private final GeocoderDataAccessObject geocoderdao = new GeocoderDataAccessObject();
    private final NasaNeoDataAccessObject nasaneodao = new NasaNeoDataAccessObject();

    // View fields
    private HomeView homeView;
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
    private NEOView neoView;
    private DisplayNEOView displayneoview;

    /**
     * Constructor for the AppBuilder class.
     * Initializes the card panel layout for switching between views.
     */
    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds and configures the HomeView.
     *
     * @return The AppBuilder instance for chaining.
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
     * Adds and configures the APODView (Astronomy Picture of the Day).
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addAstronomyPictureView() {
        apodView = new APODView();
        cardPanel.add(apodView, apodView.getViewName());

        final AstronomyPicturePresenter presenter = new AstronomyPicturePresenter(apodView);
        final AstronomyPictureApiDataAccessObject dataAccessObject = new AstronomyPictureApiDataAccessObject();
        final AstronomyPictureInteractor interactor = new AstronomyPictureInteractor(presenter, dataAccessObject,
                viewManager);
        final AstronomyPictureController controller = new AstronomyPictureController(interactor);

        // Ensure this is called
        apodView.setController(controller);
        return this;
    }

    /**
     * Adds and configures the Star Chart view.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addStarChartView() {
        starChartView = new StarChartView();
        cardPanel.add(starChartView, starChartView.getViewName());
        return this;
    }

    /**
     * Adds and configures the Moon Phase view.
     * This view displays information about the current moon phase.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addMoonPhaseView() {
        moonPhaseView = new MoonPhaseView();
        cardPanel.add(moonPhaseView, moonPhaseView.getViewName());
        return this;
    }

    /**
     * Adds and configures the Geocoder view.
     * This view allows users to input an address and see the corresponding coordinates on a map.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addGeocodingView() {
        geocoderView = new GeocoderView();
        cardPanel.add(geocoderView, geocoderView.getViewName());
        return this;
    }

    /**
     * Adds and configures the Display Geocoder view.
     * This view displays the results of a geocoding operation, including the resolved address and coordinates.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addDisplayGeocoderView() {
        displayGeocoderView = new DisplayGeocoderView();
        cardPanel.add(displayGeocoderView, displayGeocoderView.getViewName());
        return this;
    }

    /**
     * Adds and configures the No Address Found view.
     * This view is displayed when no address is found for a user's geocoding request.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addNoAddressFoundView() {
        noAddressFoundView = new NoAddressFoundView();
        cardPanel.add(noAddressFoundView, noAddressFoundView.getViewName());
        return this;
    }

    /**
     * Adds and configures the Name Geocoder view.
     * This view allows users to input a name to retrieve associated geographical data.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addNameGeocoderView() {
        nameGeocoderView = new NameGeocoderView();
        cardPanel.add(nameGeocoderView, nameGeocoderView.getViewName());
        return this;
    }

    /**
     * Adds and configures the Display Star Chart view.
     * This view displays a detailed star chart based on user-selected parameters
     * such as location, date, and time.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addDisplayStarChartView() {
        displayStarChartView = new DisplayStarChartView();
        cardPanel.add(displayStarChartView, displayStarChartView.getViewName());
        return this;
    }

    /**
     * Adds and configures the Events view.
     * This view displays a list of astronomical events.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addEventsView() {
        eventsView = new EventsView();
        cardPanel.add(eventsView, eventsView.getViewName());
        return this;
    }

    /**
     * Adds and configures the Display Events view.
     * This view shows detailed information about a selected astronomical event.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addDisplayEventsView() {
        displayEventsView = new DisplayEventsView();
        cardPanel.add(displayEventsView, displayEventsView.getViewName());
        return this;
    }

    /**
     * Adds and configures the Display Moon Phase view.
     * This view shows detailed information about the moon phase for a specific date.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addDisplayMoonPhaseView() {
        displayMoonPhaseView = new DisplayMoonPhaseView();
        cardPanel.add(displayMoonPhaseView, displayMoonPhaseView.getViewName());
        return this;
    }

    /**
     * Adds and configures the Near-Earth Objects (NEO) view.
     * This view displays information about NEOs, including their closest approach to Earth.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addNearEarthObjectsView() {
        neoView = new NEOView();
        cardPanel.add(neoView, neoView.getViewName());
        return this;
    }

    /**
     * Adds and configures the Display Near-Earth Object (NEO) view.
     * This view shows detailed information about a specific NEO.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addDisplayNearEarthObjectsView() {
        displayneoview = new DisplayNEOView();
        cardPanel.add(displayneoview, displayneoview.getViewName());
        return this;
    }

    /**
     * Adds the Home interface to the application, initializing its presenter and controller.
     *
     * @return The {@code AppBuilder} instance with the Home interface configured.
     */
    public AppBuilder addHomeInterface() {
        final HomePresenter homePresenter = new HomePresenter(viewManager);
        final HomeController homeController = new HomeController(homePresenter);
        homeView.setHomeController(homeController);
        return this;
    }

    /**
     * Adds and configures the Star Chart use case.
     * This use case generates star charts based on user-provided inputs such as location and date.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addStarChartUseCase() {
        final StarChartPresenter starChartPresenter = new StarChartPresenter(viewManager);
        starChartPresenter.setDisplayStarChartView(displayStarChartView);
        final StarChartInteractor starChartInteractor = new StarChartInteractor(starchartdao, starChartPresenter);
        final StarChartController starChartController = new StarChartController(starChartInteractor);
        starChartView.setStarChartController(starChartController);

        return this;
    }

    /**
     * Adds and configures the Moon Phase use case.
     * This use case calculates moon phases for specific dates and displays them to the user.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addMoonPhaseUseCase() {
        final MoonPhasePresenter moonPhasePresenter = new MoonPhasePresenter(viewManager);
        moonPhasePresenter.setDisplayMoonPhaseView(displayMoonPhaseView);
        final MoonPhaseInteractor moonPhaseInteractor = new MoonPhaseInteractor(moonphasedao, moonPhasePresenter);
        final MoonPhaseController moonPhaseController = new MoonPhaseController(moonPhaseInteractor);
        moonPhaseView.setMoonPhaseController(moonPhaseController);

        return this;
    }

    /**
     * Adds and configures the Geocoding use case.
     * This use case allows users to input an address and retrieve the corresponding latitude and longitude.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addGeocoderUseCase() {

        final GeocodingPresenter geocodingPresenter = new GeocodingPresenter(viewManager, displayGeocoderView);
        final GeocodingInteractor geocodingInteractor = new GeocodingInteractor(geocoderdao, geocodingPresenter);
        final GeocodingController geocodingController =
                new GeocodingController(geocodingInteractor, geocodingPresenter);

        displayGeocoderView.setDisplayStarChartController(geocodingController);

        geocoderView.setGeoCodingController(geocodingController);
        noAddressFoundView.setGeocodingController(geocodingController);
        nameGeocoderView.setGeoCodingController(geocodingController);

        return this;
    }

    /**
     * Adds and configures the Display Star Chart interface.
     * This method initializes the presenter and controller for the Display Star Chart view,
     * linking the view to its corresponding controller and presenter for user interactions.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addDisplayStarChartInterface() {
        final DisplayStarChartPresenter displayStarChartPresenter = new DisplayStarChartPresenter(viewManager);
        final DisplayStarChartController displayStarChartController =
                new DisplayStarChartController(displayStarChartPresenter);
        displayStarChartView.setDisplayStarChartController(displayStarChartController);
        return this;
    }

    /**
     * Adds and configures the Events use case.
     * This use case retrieves and displays a list of upcoming astronomical events.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addEventsUseCase() {
        final EventsPresenter eventsPresenter = new EventsPresenter(viewManager);
        eventsPresenter.setDisplayEventsView(displayEventsView);
        final EventsInteractor eventsInteractor = new EventsInteractor(eventsdao, eventsPresenter);
        final EventsController eventsController = new EventsController(eventsInteractor);
        eventsView.setEventsController(eventsController);
        return this;
    }

    /**
     * Adds and configures the Display Events interface.
     * This method initializes the presenter and controller for the Display Events view,
     * linking the view to its corresponding controller and presenter to manage user interactions
     * for displaying detailed event information.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addDisplayEventsInterface() {
        final DisplayEventsPresenter displayEventsPresenter = new DisplayEventsPresenter(viewManager);
        final DisplayEventsController displayEventsController = new DisplayEventsController(displayEventsPresenter);
        displayEventsView.setDisplayEventsController(displayEventsController);
        return this;
    }

    /**
     * Adds and configures the Display Moon Phase interface.
     * This method initializes the presenter and controller for the Display Moon Phase view,
     * linking the view to its corresponding controller and presenter to handle user interactions
     * for displaying detailed moon phase information for specific dates.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addDisplayMoonPhaseInterface() {
        final DisplayMoonPhasePresenter displayMoonPhasePresenter = new DisplayMoonPhasePresenter(viewManager);
        final DisplayMoonPhaseController displayMoonPhaseController =
                new DisplayMoonPhaseController(displayMoonPhasePresenter);
        displayMoonPhaseView.setDisplayMoonPhaseController(displayMoonPhaseController);
        return this;
    }

    /**
     * Adds and configures the Near-Earth Objects (NEO) use case.
     * This use case retrieves and displays information about NEOs and their closest approaches to Earth.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addNearEarthObjectsUseCase() {
        final NearEarthObjectsPresenter neoPresenter = new NearEarthObjectsPresenter(viewManager);
        neoPresenter.setDisplayNearEarthObjectsView(displayneoview);
        final NearEarthObjectsInteractor neoInteractor = new NearEarthObjectsInteractor(nasaneodao, neoPresenter);
        final NearEarthObjectsController neoController = new NearEarthObjectsController(neoInteractor);
        neoView.setNEOController(neoController);
        return this;
    }

    /**
     * Adds and configures the Display Near-Earth Object (NEO) interface.
     * This method initializes the presenter for the Display NEO view,
     * linking the view to its corresponding presenter to handle user interactions
     * for displaying detailed information about specific Near-Earth Objects.
     *
     * @return The AppBuilder instance for chaining.
     */
    public AppBuilder addDisplayNearEarthObjectsInterface() {
        final NearEarthObjectsPresenter displayNearEarthObjectsPresenter = new NearEarthObjectsPresenter(viewManager);
        displayNearEarthObjectsPresenter.setDisplayNearEarthObjectsView(displayneoview);
        displayneoview.setPresenter(displayNearEarthObjectsPresenter);
        return this;
    }

    /**
     * Builds the application frame with all configured views.
     * @return The constructed JFrame ready for display.
     */
    public JFrame build() {
        final JFrame application = new JFrame("Star Gazers App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        return application;
    }
}
