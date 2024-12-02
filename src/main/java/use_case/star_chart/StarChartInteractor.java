package use_case.star_chart;

import data_access.StarChartDataAccessObject;
import entity.StarChart;
import helper.AstronomyCalculations;

/**
 * Interactor for the Star Chart use case.
 */
public class StarChartInteractor implements StarChartInputBoundary {
    private final StarChartDataAccessObject starchartdao;
    private final StarChartOutputBoundary starChartPresenter;

    public StarChartInteractor(StarChartDataAccessObject starchartdao, StarChartOutputBoundary starChartPresenter) {
        this.starchartdao = starchartdao;
        this.starChartPresenter = starChartPresenter;
    }

    @Override
    public void execute(StarChartInputData starChartInputData) {
        final String longitude = starChartInputData.getLongitude();
        final String latitude = starChartInputData.getLatitude();
        final String date = starChartInputData.getDate();
        final String ra = Double.toString(AstronomyCalculations.calcRa(longitude, date));
        final String dec = Double.toString(Math.round(Double.parseDouble(latitude)));
        final String query = String.format(
                "{\"style\":\"inverted\",\"observer\":{\"latitude\":%s,\"longitude\":%s,\"date\":\"%s\"},"
                        + "\"view\":{\"type\":\"area\",\"parameters\":{\"position\":{\"equatorial\":{"
                        + "\"rightAscension\":%s,"
                        + "\"declination\":%s}},\"zoom\":6}}}",
                latitude, longitude, date, ra, dec
        );
        final String imgUrl = starchartdao.executeQuery(query);

        final StarChart starChart = new StarChart(longitude, latitude, date, imgUrl);
        starChartPresenter.displayStarChart(starChart);
    }

    /**
     * Executes the default behavior for the Star Chart use case, typically navigating back to a previous view.
     */
    public void execute() {
        starChartPresenter.back();
    }
}
