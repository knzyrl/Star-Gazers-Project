package use_case;

import data_access.StarChartDataAccessObject;
import entity.StarChart;
import org.junit.Test;
import use_case.star_chart.StarChartInputBoundary;
import use_case.star_chart.StarChartInputData;
import use_case.star_chart.StarChartInteractor;
import use_case.star_chart.StarChartOutputBoundary;

import static org.junit.Assert.*;

public class StarChartInteractorTest {

    @Test
    public void starChartTest() {
        StarChartInputData inputData = new StarChartInputData("0.00", "0.00", "1970-01-01");
        StarChartDataAccessObject mockDAO = new StarChartDataAccessObject() {
            @Override
            public String executeQuery(String query) {
                return "starChartImgURL.png";
            }
        };

        StarChartOutputBoundary starChartPresenter = new StarChartOutputBoundary() {
            @Override
            public void displayStarChart(StarChart starChart) {
                assertEquals("0.00", starChart.getLongitude());
                assertEquals("0.00", starChart.getLatitude());
                assertEquals("1970-01-01", starChart.getDate());
                assertEquals("starChartImgURL.png", starChart.getImgURL());
            }

            @Override
            public void back() {
                fail("Return to home view unexpected.");
            }
        };

        StarChartInputBoundary starChartInteractor = new StarChartInteractor(mockDAO, starChartPresenter);
        starChartInteractor.execute(inputData);
    }
}
