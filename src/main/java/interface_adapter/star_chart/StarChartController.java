package interface_adapter.star_chart;

import java.io.IOException;

import use_case.star_chart.StarChartInputData;
import use_case.star_chart.StarChartInteractor;

public class StarChartController {
    private final StarChartInteractor starChartInteractor;

    public StarChartController(StarChartInteractor starChartInteractor) {
        this.starChartInteractor = starChartInteractor;
    }

    /**
     * Calls the interactor to execute the star chart use case.
     * @param longitude the longitude input by the user
     * @param latitude the latitude input by the user
     * @param date the date input by the user
     * @throws IOException thrown when the interactor fails to parse the image URL
     */
    public void execute(String longitude, String latitude, String date) throws IOException {
        final StarChartInputData starChartInputData = new StarChartInputData(longitude, latitude, date);
        starChartInteractor.execute(starChartInputData);
    }

    /**
     * Calls the interactor to return to the home view.
     */
    public void execute() {
        starChartInteractor.execute();
    }
}
