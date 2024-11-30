package app;

import data_access.APODdateAPIDataAccessObject;
import data_access.EventsDataAccessObject;
import data_access.MoonPhaseDataAccessObject;
import data_access.StarChartDataAccessObject;
import interface_adapter.APOD_date.APODController;
import interface_adapter.APOD_date.APODPresenter;
import data_access.GeocoderDataAccessObject;
import data_access.NASANeoDataAccessObject;
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
import interface_adapter.star_chart.StarChartController;
import interface_adapter.star_chart.StarChartPresenter;
import interface_adapter.near_earth_objects.NEOController;
import interface_adapter.near_earth_objects.NEOPresenter;
import use_case.apod_date.APODInteractor;
import use_case.events.EventsInteractor;
import use_case.moon_phase.MoonPhaseInteractor;
import use_case.geocoding.GeocodingInteractor;
import use_case.star_chart.StarChartInteractor;
import use_case.near_earth_objects.NEOInteractor;
import view.*;
import view.APODView;
import view.DisplayEventsView;
import view.DisplayStarChartView;
import view.EventsView;
import view.HomeView;
import view.StarChartView;
import view.NEOView;
import view.DisplayNEOView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;


public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManager viewManager = new ViewManager(cardLayout, cardPanel);
    private final StarChartDataAccessObject starChartDAO = new StarChartDataAccessObject();
    private final EventsDataAccessObject eventsDAO = new EventsDataAccessObject();
    private final MoonPhaseDataAccessObject moonPhaseDAO = new MoonPhaseDataAccessObject();
    private final GeocoderDataAccessObject geocoderDAO = new GeocoderDataAccessObject();
    private final NASANeoDataAccessObject nasaNeoDAO = new NASANeoDataAccessObject();
    private HomeView homeView;
    private StarChartView starChartView;
    private MoonPhaseView moonPhaseView;
    private DisplayStarChartView displayStarChartView;
    private EventsView eventsView;
    private DisplayEventsView displayEventsView;
    private APODView apodView;
    private DisplayMoonPhaseView displayMoonPhaseView;
    private GeocoderView geocoderView;
    private DisplayGeocoderView displayGeocoderView;
    private NoAddressFoundView noAddressFoundView;
    private NEOView neoView;
    private DisplayNEOView displayNEOView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addHomeView() {
        // Create the HomeView
        homeView = new HomeView();
        cardPanel.add(homeView, homeView.getViewName());

        // Create and set the HomeController
        HomePresenter homePresenter = new HomePresenter(viewManager);
        HomeController homeController = new HomeController(homePresenter);
        homeView.setHomeController(homeController);

        return this;
    }


    public AppBuilder addAPODView() {
        apodView = new APODView();
        cardPanel.add(apodView, apodView.getViewName());

        APODPresenter presenter = new APODPresenter(apodView);
        APODdateAPIDataAccessObject dataAccessObject = new APODdateAPIDataAccessObject();
        APODInteractor interactor = new APODInteractor(presenter, dataAccessObject, viewManager);
        APODController controller = new APODController(interactor);

        apodView.setController(controller); // Ensure this is called
        return this;
    }


    public AppBuilder addStarChartView() {
        starChartView = new StarChartView();
        cardPanel.add(starChartView, starChartView.getViewName());
        return this;
    }

    public AppBuilder addMoonPhaseView() {
        moonPhaseView = new MoonPhaseView();
        cardPanel.add(moonPhaseView, moonPhaseView.getViewName());
        return this;
    }

    public AppBuilder addGeocodingView() {
        geocoderView = new GeocoderView();
        cardPanel.add(geocoderView, geocoderView.getViewName());
        return this;
    }

    public AppBuilder addDisplayGeocoderView() {
        displayGeocoderView = new DisplayGeocoderView();
        cardPanel.add(displayGeocoderView, displayGeocoderView.getViewName());
        return this;
    }

    public AppBuilder addNoAddressFoundView() {
        noAddressFoundView = new NoAddressFoundView();
        cardPanel.add(noAddressFoundView, noAddressFoundView.getViewName());
        return this;
    }

    public AppBuilder addDisplayStarChartView() {
        displayStarChartView = new DisplayStarChartView();
        cardPanel.add(displayStarChartView, displayStarChartView.getViewName());
        return this;
    }

    public AppBuilder addEventsView() {
        eventsView = new EventsView();
        cardPanel.add(eventsView, eventsView.getViewName());
        return this;
    }

    public AppBuilder addDisplayEventsView() {
        displayEventsView = new DisplayEventsView();
        cardPanel.add(displayEventsView, displayEventsView.getViewName());
        return this;
    }

    public AppBuilder addDisplayMoonPhaseView() {
        displayMoonPhaseView = new DisplayMoonPhaseView();
        cardPanel.add(displayMoonPhaseView, displayMoonPhaseView.getViewName());
        return this;
    }

    public AppBuilder addNEOView() {
        neoView = new NEOView();
        cardPanel.add(neoView, neoView.getViewName());
        return this;
    }

    public AppBuilder addDisplayNEOView() {
        displayNEOView = new DisplayNEOView();
        cardPanel.add(displayNEOView, displayNEOView.getViewName());
        return this;
    }

    public AppBuilder addHomeInterface() {
        HomePresenter homePresenter = new HomePresenter(viewManager);
        HomeController homeController = new HomeController(homePresenter);
        homeView.setHomeController(homeController);
        return this;
    }

    public AppBuilder addStarChartUseCase() {
        StarChartPresenter starChartPresenter = new StarChartPresenter(viewManager);
        starChartPresenter.setDisplayStarChartView(displayStarChartView);
        StarChartInteractor starChartInteractor = new StarChartInteractor(starChartDAO, starChartPresenter);
        StarChartController starChartController = new StarChartController(starChartInteractor);
        starChartView.setStarChartController(starChartController);

        return this;
    }

    public AppBuilder addMoonPhaseUseCase() {
        MoonPhasePresenter moonPhasePresenter = new MoonPhasePresenter(viewManager);
        moonPhasePresenter.setDisplayMoonPhaseView(displayMoonPhaseView);
        MoonPhaseInteractor moonPhaseInteractor = new MoonPhaseInteractor(moonPhaseDAO, moonPhasePresenter);
        MoonPhaseController moonPhaseController = new MoonPhaseController(moonPhaseInteractor);
        moonPhaseView.setMoonPhaseController(moonPhaseController);

        return this;
    }

    public AppBuilder addGeocoderUseCase() {

        GeocodingPresenter geocodingPresenter = new GeocodingPresenter(viewManager, displayGeocoderView);
        GeocodingInteractor geocodingInteractor = new GeocodingInteractor(geocoderDAO, geocodingPresenter);
        GeocodingController geocodingController = new GeocodingController(geocodingInteractor, geocodingPresenter);

        displayGeocoderView.setDisplayStarChartController(geocodingController);

        geocoderView.setGeoCodingController(geocodingController);
        noAddressFoundView.setGeocodingController(geocodingController);

        return this;
    }

    public AppBuilder addDisplayStarChartInterface() {
        DisplayStarChartPresenter displayStarChartPresenter = new DisplayStarChartPresenter(viewManager);
        DisplayStarChartController displayStarChartController = new DisplayStarChartController(displayStarChartPresenter);
        displayStarChartView.setDisplayStarChartController(displayStarChartController);
        return this;
    }

    public AppBuilder addEventsUseCase() {
        EventsPresenter eventsPresenter = new EventsPresenter(viewManager);
        eventsPresenter.setDisplayEventsView(displayEventsView);
        EventsInteractor eventsInteractor = new EventsInteractor(eventsDAO, eventsPresenter);
        EventsController eventsController = new EventsController(eventsInteractor);
        eventsView.setEventsController(eventsController);
        return this;
    }

    public AppBuilder addDisplayEventsInterface() {
        DisplayEventsPresenter displayEventsPresenter = new DisplayEventsPresenter(viewManager);
        DisplayEventsController displayEventsController = new DisplayEventsController(displayEventsPresenter);
        displayEventsView.setDisplayEventsController(displayEventsController);
        return this;
    }

    public AppBuilder addDisplayMoonPhaseInterface() {
        DisplayMoonPhasePresenter displayMoonPhasePresenter = new DisplayMoonPhasePresenter(viewManager);
        DisplayMoonPhaseController displayMoonPhaseController = new DisplayMoonPhaseController(displayMoonPhasePresenter);
        displayMoonPhaseView.setDisplayMoonPhaseController(displayMoonPhaseController);
        return this;
    }

    public AppBuilder addNEOUseCase() {
        NEOPresenter neoPresenter = new NEOPresenter(viewManager);
        neoPresenter.setDisplayNEOView(displayNEOView);
        NEOInteractor neoInteractor = new NEOInteractor(nasaNeoDAO, neoPresenter);
        NEOController neoController = new NEOController(neoInteractor);
        neoView.setNEOController(neoController);
        return this;
    }

    public AppBuilder addDisplayNEOInterface() {
        NEOPresenter displayNEOPresenter = new NEOPresenter(viewManager);
        displayNEOPresenter.setDisplayNEOView(displayNEOView);
        displayNEOView.setPresenter(displayNEOPresenter);
        return this;
    }


    public JFrame build() {
        final JFrame application = new JFrame("Star Gazers App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        return application;
    }
}
