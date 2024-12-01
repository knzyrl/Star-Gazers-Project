package use_case.star_chart;

import data_access.StarChartDataAccessObject;
import entity.StarChart;

public class StarChartInteractor implements StarChartInputBoundary {
    private final StarChartDataAccessObject starChartDAO;
    private final StarChartOutputBoundary starChartPresenter;

    public StarChartInteractor(StarChartDataAccessObject starChartDAO, StarChartOutputBoundary starChartPresenter) {
        this.starChartDAO = starChartDAO;
        this.starChartPresenter = starChartPresenter;
    }

    @Override
    public void execute(StarChartInputData starChartInputData) {
        StarChart starChart = new StarChart(starChartInputData.getLongitude(), starChartInputData.getLatitude(),
                starChartInputData.getDate());

        if (!starChart.isValidLongitude()) {
            starChartPresenter.prepareFailView("Longitude value invalid. Please ensure the input is a " +
                    "decimal between -180.00 and 180.00.");
            return;
        } else if (!starChart.isValidLatitude()) {
            starChartPresenter.prepareFailView("Latitude value invalid. Please ensure the input is a " +
                    "decimal between -90.00 and 90.00.");
            return;
        } else if (!starChart.isValidDate()) {
            starChartPresenter.prepareFailView("Date invalid. Please ensure the input is a valid date " +
                    "in YYYY-MM-DD format.");
            return;
        }

        final String ra = starChart.calcRA();
        final String dec = starChart.calcDEC();

        final String query = String.format("{\"style\":\"inverted\",\"observer\":{\"latitude\":%s,\"longitude\":%s," +
                        "\"date\":\"%s\"},\"view\":{\"type\":\"area\",\"parameters\":{\"position\":{\"equatorial\":" +
                        "{\"rightAscension\":%s,\"declination\":%s}},\"zoom\":6}}}", starChart.getLatitude(),
                starChart.getLongitude(), starChart.getDate(), ra, dec);
        final String imgURL = starChartDAO.executeQuery(query);

        final StarChartOutputData starChartOutputData = new StarChartOutputData(starChart.getLongitude(),
                starChart.getLatitude(), starChart.getDate(), imgURL);
        starChartPresenter.displayStarChart(starChartOutputData);
    }

    @Override
    public void execute() {
        starChartPresenter.back();
    }
}
