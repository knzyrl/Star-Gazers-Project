package app;

import data_access.EventsDataAccessObject;
import data_access.StarChartDataAccessObject;
import interface_adapter.display_events.DisplayEventsController;
import interface_adapter.display_events.DisplayEventsPresenter;
import interface_adapter.display_star_chart.DisplayStarChartController;
import interface_adapter.display_star_chart.DisplayStarChartPresenter;
import interface_adapter.events.EventsController;
import interface_adapter.events.EventsPresenter;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.star_chart.StarChartController;
import interface_adapter.star_chart.StarChartPresenter;
import use_case.events.EventsInteractor;
import use_case.star_chart.StarChartInteractor;
import data_access.APODdateAPIDataAccessObject;
import interface_adapter.APOD_date.APODController;
import interface_adapter.APOD_date.APODPresenter;
import use_case.apod_date.APODInteractor;
import view.APODView; // not sure if I need this?
import view.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManager viewManager = new ViewManager(cardLayout, cardPanel);
    private final StarChartDataAccessObject starChartDAO = new StarChartDataAccessObject();
    private final EventsDataAccessObject eventsDAO = new EventsDataAccessObject();
    private HomeView homeView;
    private StarChartView starChartView;
    private DisplayStarChartView displayStarChartView;
    private EventsView eventsView;
    private DisplayEventsView displayEventsView;
    private APODView apodView; // Add APOD View


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addHomeView() {
        homeView = new HomeView();
        cardPanel.add(homeView, homeView.getViewName());
        return this;
    }

    // Add APOD view to the card layout
    public AppBuilder addAPODView() {
        apodView = new APODView();
        cardPanel.add(apodView, apodView.getViewName());
        return this;
    }

    // Connect APOD presenter and controller
    public AppBuilder addAPODInterface() {
        APODPresenter presenterView = new APODPresenter(viewManager);
        APODdateAPIDataAccessObject dataAccessObject = new APODdateAPIDataAccessObject();
        APODInteractor interactor = new APODInteractor(presenterView, dataAccessObject);
        APODController controller = new APODController(interactor);
        apodView.setDisplayAPODController(controller);
        return this;
    }

    public AppBuilder addStarChartView() {
        starChartView = new StarChartView();
        cardPanel.add(starChartView, starChartView.getViewName());
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

    public JFrame build() {
        final JFrame application = new JFrame("Star Gazers App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        return application;
    }
}
