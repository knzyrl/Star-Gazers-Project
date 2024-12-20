package use_case.star_chart;

import data_access.AstronomyApiDataAccessObject;
import entity.StarChart;

/**
 * Class for the Interactor for the Star Chart use case.
 * Receives input data from the Controller, manipulates it according to the use case requirements and sends it to the
 * OutputBoundary.
 */
public class StarChartInteractor implements StarChartInputBoundary {
    private final AstronomyApiDataAccessObject starChartDataAccessObject;
    private final StarChartOutputBoundary starChartPresenter;

    public StarChartInteractor(AstronomyApiDataAccessObject starChartDataAccessObject,
                               StarChartOutputBoundary starChartPresenter) {
        this.starChartDataAccessObject = starChartDataAccessObject;
        this.starChartPresenter = starChartPresenter;
    }

    @Override
    public void execute(StarChartInputData starChartInputData) {
        final StarChart starChart = new StarChart(starChartInputData.getLongitude(), starChartInputData.getLatitude(),
                starChartInputData.getDate());

        if (!starChart.isValidLongitude()) {
            starChartPresenter.prepareFailView("Longitude value invalid. Please ensure the input is a "
                    + "decimal between -180.00 and 180.00.");
        }
        else if (!starChart.isValidLatitude()) {
            starChartPresenter.prepareFailView("Latitude value invalid. Please ensure the input is a "
                    + "decimal between -90.00 and 90.00.");
        }
        else if (!starChart.isValidDate()) {
            starChartPresenter.prepareFailView("Date invalid. Please ensure the input is a valid date "
                    + "in YYYY-MM-DD format.");
        }
        else {
            final String ra = starChart.calcRa();
            final String dec = starChart.calcdec();

            final String query = String.format("{\"style\":\"inverted\",\"observer\":{\"latitude\":%s,\"longitude\":%s,"
                            + "\"date\":\"%s\"},\"view\":{\"type\":\"area\",\"parameters\":{\"position\":"
                            + "{\"equatorial\":{\"rightAscension\":%s,\"declination\":%s}},\"zoom\":6}}}",
                    starChart.getLatitude(), starChart.getLongitude(), starChart.getDate(), ra, dec);
            final String imgUrl = starChartDataAccessObject.executeQuery(query);

            final StarChartOutputData starChartOutputData = new StarChartOutputData(starChart.getLongitude(),
                    starChart.getLatitude(), starChart.getDate(), imgUrl);
            starChartPresenter.displayStarChart(starChartOutputData);
        }
    }

    @Override
    public void execute() {
        starChartPresenter.back();
    }
}
