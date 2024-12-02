package app;

import javax.swing.JFrame;

/**
 * Entry point for the application.
 * This class initializes the application by to constructing the views,
 * controllers, and use cases, and displaying the main application window.
 */
public class Main {

    /**
     * Main method to launch the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addHomeView()
                .addStarChartView()
                .addDisplayStarChartView()
                .addMoonPhaseView()
                .addDisplayMoonPhaseView()
                .addGeocodingView()
                .addNameGeocoderView()
                .addDisplayGeocoderView()
                .addNoAddressFoundView()
                .addGeocoderUseCase()
                .addEventsView()
                .addDisplayEventsView()
                .addNearEarthObjectsView()
                .addDisplayNearEarthObjectsView()
                .addAstronomyPictureView()
                .addHomeInterface()
                .addStarChartUseCase()
                .addDisplayStarChartInterface()
                .addMoonPhaseUseCase()
                .addDisplayMoonPhaseInterface()
                .addEventsUseCase()
                .addDisplayEventsInterface()
                .addNearEarthObjectsUseCase()
                .addDisplayNearEarthObjectsInterface()
                .build();
        application.pack();
        application.setVisible(true);
    }
}
