package app;

import data_access.GeocoderDataAccessObject;
import data_access.StarChartDataAccessObject;
import interface_adapter.display_star_chart.DisplayStarChartController;
import interface_adapter.display_star_chart.DisplayStarChartPresenter;
import interface_adapter.geocoding.GeocodingController;
import interface_adapter.geocoding.GeocodingPresenter;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.star_chart.StarChartController;
import interface_adapter.star_chart.StarChartPresenter;
import use_case.geocoding.GeocodingInteractor;
import use_case.star_chart.StarChartInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManager viewManager = new ViewManager(cardLayout, cardPanel);
    private final StarChartDataAccessObject starChartDAO = new StarChartDataAccessObject();
    private final GeocoderDataAccessObject geocoderDAO = new GeocoderDataAccessObject();
    private HomeView homeView;
    private StarChartView starChartView;
    private DisplayStarChartView displayStarChartView;
    private GeocoderView geocoderView;
    private DisplayGeocoderView displayGeocoderView;
    private NoAddressFoundView noAddressFoundView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addHomeView() {
        homeView = new HomeView();
        cardPanel.add(homeView, homeView.getViewName());
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

    public AppBuilder addGeocoderUseCase() {

        GeocodingPresenter geocodingPresenter = new GeocodingPresenter(viewManager, displayGeocoderView);
        GeocodingInteractor geocodingInteractor = new GeocodingInteractor(geocoderDAO, geocodingPresenter);
        GeocodingController geocodingController = new GeocodingController(geocodingInteractor, geocodingPresenter);

        displayGeocoderView.setDisplayStarChartController(geocodingController);

        geocoderView.setGeoCodingController(geocodingController);
        noAddressFoundView.setGeocodingController(geocodingController);

        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Star Gazers App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        return application;
    }
}
