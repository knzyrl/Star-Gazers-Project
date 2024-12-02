package app;

import view.NameGeocoderView;

import javax.swing.*;

public class Main {
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
                .addNEOView()
                .addDisplayNEOView()
                .addAPODView()
                .addHomeInterface()
                .addFailInterface()
                .addStarChartUseCase()
                .addDisplayStarChartInterface()
                .addMoonPhaseUseCase()
                .addDisplayMoonPhaseInterface()
                .addEventsUseCase()
                .addDisplayEventsInterface()
                .addNEOUseCase()
                .addDisplayNEOInterface()
                .build();
        application.pack();
        application.setVisible(true);
    }
}