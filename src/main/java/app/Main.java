package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addHomeView()
                .addStarChartView()
                .addDisplayStarChartView()
                .addEventsView()
                .addDisplayEventsView()
                .addAPODView()
                .addHomeInterface()
                .addStarChartUseCase()
                .addDisplayStarChartInterface()
                .addEventsUseCase()
                .addDisplayEventsInterface()
                .build();
        application.pack();
        application.setVisible(true);
    }
}
