package use_case.star_chart;

import data_access.StarChartDataAccessObject;
import entity.StarChart;
import helper.AstronomyCalculations;
import interface_adapter.star_chart.StarChartPresenter;

import java.io.IOException;

public class StarChartInteractor {
    private final StarChartDataAccessObject starChartDAO;
    private final StarChartPresenter starChartPresenter;

    public StarChartInteractor(StarChartDataAccessObject starChartDAO, StarChartPresenter starChartPresenter) {
        this.starChartDAO = starChartDAO;
        this.starChartPresenter = starChartPresenter;
    }

    public void execute(StarChartInputData starChartInputData) throws IOException {
        final String longitude = starChartInputData.getLongitude();
        final String latitude = starChartInputData.getLatitude();
        final String date = starChartInputData.getDate();
        final String ra = Double.toString(AstronomyCalculations.calcRA(longitude, date));
        final String dec = Double.toString(Math.round(Double.parseDouble(latitude)));

        final String query = String.format("{\"style\":\"inverted\",\"observer\":{\"latitude\":%s,\"longitude\":%s,\"date\":\"%s\"},\"view\":{\"type\":\"area\",\"parameters\":{\"position\":{\"equatorial\":{\"rightAscension\":%s,\"declination\":%s}},\"zoom\":6}}}", latitude, longitude, date, ra, dec);
        final String imgURL = starChartDAO.executeQuery(query);

        final StarChart starChart = new StarChart(longitude, latitude, date, imgURL);
        starChartPresenter.displayStarChart(starChart);
    }

    public void execute() {
        starChartPresenter.back();
    }
}
