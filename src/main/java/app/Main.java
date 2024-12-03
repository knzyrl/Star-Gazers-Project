package app;

import javax.swing.JFrame;

public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addHomeView()
                .addFailView()
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
                .addNeoView()
                .addDisplayNeoView()
                .addAPODView()
                .addHomeInterface()
                .addFailInterface()
                .addStarChartUseCase()
                .addDisplayStarChartInterface()
                .addMoonPhaseUseCase()
                .addDisplayMoonPhaseInterface()
                .addEventsUseCase()
                .addDisplayEventsInterface()
                .addNeoUseCase()
                .addDisplayNeoInterface()
                .build();
        application.pack();
        application.setVisible(true);
    }
}
