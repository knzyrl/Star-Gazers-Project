package interface_adapter.star_chart;

import java.io.IOException;

import use_case.star_chart.StarChartInputData;
import use_case.star_chart.StarChartInteractor;

/**
 * Controller for the Star Chart use case.
 */
public class StarChartController {
    private final StarChartInteractor starChartInteractor;

    public StarChartController(StarChartInteractor starChartInteractor) {
        this.starChartInteractor = starChartInteractor;
    }

    /**
     * Executes the Star Chart use case with the provided input data.
     *
     * @param longitude The longitude of the location.
     * @param latitude  The latitude of the location.
     * @param date      The date for generating the star chart, in "YYYY-MM-DD" format.
     * @throws IOException If an I/O error occurs during execution.
     */
    public void execute(String longitude, String latitude, String date) throws IOException {
        final StarChartInputData starChartInputData = new StarChartInputData(longitude, latitude, date);
        starChartInteractor.execute(starChartInputData);
    }

    /**
     * Executes the default Star Chart use case without additional input data.
     */
    public void execute() {
        starChartInteractor.execute();
    }
}
