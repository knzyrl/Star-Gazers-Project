package use_case;

import data_access.AstronomyApiDataAccessObject;
import data_access.StarChartDataAccessObject;
import org.junit.jupiter.api.Test;
import use_case.star_chart.*;

import static org.junit.jupiter.api.Assertions.*;

class StarChartInteractorTest {

    @Test
    void successTest() {
        AstronomyApiDataAccessObject mockDAO = new StarChartDataAccessObject() {
            @Override
            public String executeQuery(String query) {
                return "starChartImgURL.png";
            }
        };

        StarChartInputData inputData = new StarChartInputData("0.00", "0.00", "1970-01-01");

        StarChartOutputBoundary starChartPresenter = new StarChartOutputBoundary() {
            @Override
            public void displayStarChart(StarChartOutputData starChartOutputData) {
                assertEquals("0.00", starChartOutputData.getLongitude());
                assertEquals("0.00", starChartOutputData.getLatitude());
                assertEquals("1970-01-01", starChartOutputData.getDate());
                assertEquals("starChartImgURL.png", starChartOutputData.getImgURL());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure unexpected.");
            }

            @Override
            public void back() {
                fail("Return to home view unexpected.");
            }
        };

        StarChartInputBoundary starChartInteractor = new StarChartInteractor(mockDAO, starChartPresenter);
        starChartInteractor.execute(inputData);
    }

    @Test
    void longitudeFormatFailTest() {
        AstronomyApiDataAccessObject mockDAO = new StarChartDataAccessObject();

        StarChartInputData inputData = new StarChartInputData("longitude", "0.00", "1970-01-01");

        StarChartOutputBoundary starChartPresenter = new StarChartOutputBoundary() {
            @Override
            public void displayStarChart(StarChartOutputData starChartOutputData) {
                fail("Success unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Longitude value invalid. Please ensure the input is a decimal " +
                        "between -180.00 and 180.00.", errorMessage);
            }

            @Override
            public void back() {
                fail("Return to home view unexpected.");
            }
        };

        StarChartInputBoundary starChartInteractor = new StarChartInteractor(mockDAO, starChartPresenter);
        starChartInteractor.execute(inputData);
    }

    @Test
    void longitudeBoundFailTest() {
        AstronomyApiDataAccessObject mockDAO = new StarChartDataAccessObject();

        StarChartInputData inputData = new StarChartInputData("9999.99", "0.00", "1970-01-01");

        StarChartOutputBoundary starChartPresenter = new StarChartOutputBoundary() {
            @Override
            public void displayStarChart(StarChartOutputData starChartOutputData) {
                fail("Success unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Longitude value invalid. Please ensure the input is a decimal " +
                        "between -180.00 and 180.00.", errorMessage);
            }

            @Override
            public void back() {
                fail("Return to home view unexpected.");
            }
        };

        StarChartInputBoundary starChartInteractor = new StarChartInteractor(mockDAO, starChartPresenter);
        starChartInteractor.execute(inputData);
    }

    @Test
    void latitudeFormatFailTest() {
        AstronomyApiDataAccessObject mockDAO = new StarChartDataAccessObject();

        StarChartInputData inputData = new StarChartInputData("0.00", "latitude", "1970-01-01");

        StarChartOutputBoundary starChartPresenter = new StarChartOutputBoundary() {
            @Override
            public void displayStarChart(StarChartOutputData starChartOutputData) {
                fail("Success unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Latitude value invalid. Please ensure the input is a decimal " +
                        "between -90.00 and 90.00.", errorMessage);
            }

            @Override
            public void back() {
                fail("Return to home view unexpected.");
            }
        };

        StarChartInputBoundary starChartInteractor = new StarChartInteractor(mockDAO, starChartPresenter);
        starChartInteractor.execute(inputData);
    }

    @Test
    void latitudeBoundFailTest() {
        AstronomyApiDataAccessObject mockDAO = new StarChartDataAccessObject();

        StarChartInputData inputData = new StarChartInputData("0.00", "-9999.99", "1970-01-01");

        StarChartOutputBoundary starChartPresenter = new StarChartOutputBoundary() {
            @Override
            public void displayStarChart(StarChartOutputData starChartOutputData) {
                fail("Success unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Latitude value invalid. Please ensure the input is a decimal " +
                        "between -90.00 and 90.00.", errorMessage);
            }

            @Override
            public void back() {
                fail("Return to home view unexpected.");
            }
        };

        StarChartInputBoundary starChartInteractor = new StarChartInteractor(mockDAO, starChartPresenter);
        starChartInteractor.execute(inputData);
    }

    @Test
    void dateFormatFailTest() {
        AstronomyApiDataAccessObject mockDAO = new StarChartDataAccessObject();

        StarChartInputData inputData = new StarChartInputData("0.00", "0.00", "date");

        StarChartOutputBoundary starChartPresenter = new StarChartOutputBoundary() {
            @Override
            public void displayStarChart(StarChartOutputData starChartOutputData) {
                fail("Success unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Date invalid. Please ensure the input is a valid date in YYYY-MM-DD format.",
                        errorMessage);
            }

            @Override
            public void back() {
                fail("Return to home view unexpected.");
            }
        };

        StarChartInputBoundary starChartInteractor = new StarChartInteractor(mockDAO, starChartPresenter);
        starChartInteractor.execute(inputData);
    }

    @Test
    void backTest() {
        AstronomyApiDataAccessObject mockDAO = new StarChartDataAccessObject() {
            @Override
            public String executeQuery(String query) {
                return "starChartImgURL.png";
            }
        };

        StarChartOutputBoundary starChartPresenter = new StarChartOutputBoundary() {
            @Override
            public void displayStarChart(StarChartOutputData starChartOutputData) {
                fail("Success unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure unexpected");
            }

            @Override
            public void back() {
                // This is expected
            }
        };

        StarChartInputBoundary starChartInteractor = new StarChartInteractor(mockDAO, starChartPresenter);
        starChartInteractor.execute();
    }

}
