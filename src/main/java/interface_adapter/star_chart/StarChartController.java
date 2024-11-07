package interface_adapter.star_chart;

import use_case.star_chart.StarChartInputData;
import use_case.star_chart.StarChartInteractor;

import java.io.IOException;

public class StarChartController {
    private final StarChartInteractor starChartInteractor;

    public StarChartController(StarChartInteractor starChartInteractor) {
        this.starChartInteractor = starChartInteractor;
    }

    public void execute(String longitude, String latitude, String date) throws IOException {
        StarChartInputData starChartInputData = new StarChartInputData(longitude, latitude, date);
        starChartInteractor.execute(starChartInputData);
    }

    public void execute() {
        starChartInteractor.execute();
    }
}
