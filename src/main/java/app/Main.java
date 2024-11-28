package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addHomeView()
                .addStarChartView()
                .addDisplayStarChartView()
                .addMoonPhaseView()
                .addDisplayMoonPhaseView()
                .addGeocodingView()
                .addDisplayGeocoderView()
                .addNoAddressFoundView()
                .addGeocoderUseCase()
                .addEventsView()
                .addDisplayEventsView()
                .addNEOView()
                .addDisplayNEOView()
                .addAPODView()
                .addHomeInterface()
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


