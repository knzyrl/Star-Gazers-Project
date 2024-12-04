package use_case;

import data_access.AstronomyApiDataAccessObject;
import data_access.MoonPhaseDataAccessObject;
import org.junit.Test;
import use_case.moon_phase.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class MoonPhaseInteractorTest {

    @Test
    public void moonPhaseSuccessTest() throws IOException {
        MoonPhaseInputData moonPhaseInputData = new MoonPhaseInputData("-84.39733", "33.775867", "2024-11-06");
       
        AstronomyApiDataAccessObject moonPhaseDAO = new MoonPhaseDataAccessObject() {
            public String executeQuery(String query) {
                return "moonChartImgURL.png";
            }
        };

        MoonPhaseOutputBoundary moonPhasePresenter = new MoonPhaseOutputBoundary() {
            @Override
            public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) {
                assertEquals("-84.39733", moonPhaseOutputData.getLongitude());
                assertEquals("33.775867", moonPhaseOutputData.getLatitude());
                assertEquals("2024-11-06", moonPhaseOutputData.getDate());
                assertEquals("moonChartImgURL.png", moonPhaseOutputData.getImageURL());
            }

            @Override
            public void back() {
                fail("Return to home view is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Fail view is unexpected");
            }
        };

        MoonPhaseInputBoundary moonPhaseInteractor = new MoonPhaseInteractor(moonPhaseDAO, moonPhasePresenter);
        moonPhaseInteractor.execute(moonPhaseInputData);

    }

    @Test
    public void moonPhaseIncorrectLatitudeFormatTest() throws IOException {
        MoonPhaseInputData moonPhaseInputData = new MoonPhaseInputData("-84.39733", "incorrect format", "2024-11-06");

        AstronomyApiDataAccessObject moonPhaseDAO = new MoonPhaseDataAccessObject() {

            public String executeQuery(String query) {
                return "moonChartImgURL.png";
            }
        };

        MoonPhaseOutputBoundary moonPhasePresenter = new MoonPhaseOutputBoundary() {

            @Override
            public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) throws IOException {
                fail("Successful display is unexpected");
            }

            @Override
            public void back() {
                fail("Return to home view is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid format. Please ensure that the date is in YYYY-MM-DD format, and that the latitude and longitude are both in decimal format.", error);
            }
        };

        MoonPhaseInputBoundary moonPhaseInteractor = new MoonPhaseInteractor(moonPhaseDAO, moonPhasePresenter);
        moonPhaseInteractor.execute(moonPhaseInputData);
    }

    @Test
    public void moonPhaseIncorrectLongitudeFormatTest() throws IOException {
        MoonPhaseInputData moonPhaseInputData = new MoonPhaseInputData("incorrect format", "33.775867", "2024-11-06");

        AstronomyApiDataAccessObject moonPhaseDAO = new MoonPhaseDataAccessObject() {

            public String executeQuery(String query) {
                return "moonChartImgURL.png";
            }
        };

        MoonPhaseOutputBoundary moonPhasePresenter = new MoonPhaseOutputBoundary() {

            @Override
            public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) throws IOException {
                fail("Successful display is unexpected");
            }

            @Override
            public void back() {
                fail("Return to home view is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid format. Please ensure that the date is in YYYY-MM-DD format, and that the latitude and longitude are both in decimal format.", error);
            }
        };

        MoonPhaseInputBoundary moonPhaseInteractor = new MoonPhaseInteractor(moonPhaseDAO, moonPhasePresenter);
        moonPhaseInteractor.execute(moonPhaseInputData);
    }

    @Test
    public void moonPhaseIncorrectDateFormatTest() throws IOException {
        MoonPhaseInputData moonPhaseInputData = new MoonPhaseInputData("-84.39733", "33.775867", "incorrect format");

        AstronomyApiDataAccessObject moonPhaseDAO = new MoonPhaseDataAccessObject() {

            public String executeQuery(String query) {
                return "moonChartImgURL.png";
            }
        };

        MoonPhaseOutputBoundary moonPhasePresenter = new MoonPhaseOutputBoundary() {

            @Override
            public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) throws IOException {
                fail("Successful display is unexpected");
            }

            @Override
            public void back() {
                fail("Return to home view is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid format. Please ensure that the date is in YYYY-MM-DD format, and that the latitude and longitude are both in decimal format.", error);
            }
        };

        MoonPhaseInputBoundary moonPhaseInteractor = new MoonPhaseInteractor(moonPhaseDAO, moonPhasePresenter);
        moonPhaseInteractor.execute(moonPhaseInputData);
    }

    @Test
    public void moonPhaseIncorrectLatitudeValueTest() throws IOException {
        MoonPhaseInputData moonPhaseInputData = new MoonPhaseInputData("-84.39733", "-200.00", "2024-11-06");

        AstronomyApiDataAccessObject moonPhaseDAO = new MoonPhaseDataAccessObject() {

            public String executeQuery(String query) {
                return "moonChartImgURL.png";
            }
        };

        MoonPhaseOutputBoundary moonPhasePresenter = new MoonPhaseOutputBoundary() {

            @Override
            public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) throws IOException {
                fail("Successful display is unexpected");
            }

            @Override
            public void back() {
                fail("Return to home view is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid value. Ensure that latitude is between -90.00 and 90.00 and longitude is between -180.00 and 180.00.", error);
            }
        };

        MoonPhaseInputBoundary moonPhaseInteractor = new MoonPhaseInteractor(moonPhaseDAO, moonPhasePresenter);
        moonPhaseInteractor.execute(moonPhaseInputData);
    }

    @Test
    public void moonPhaseIncorrectLongitudeValueTest() throws IOException {
        MoonPhaseInputData moonPhaseInputData = new MoonPhaseInputData("-200.00", "33.775867", "2024-11-06");

        AstronomyApiDataAccessObject moonPhaseDAO = new MoonPhaseDataAccessObject() {

            public String executeQuery(String query) {
                return "moonChartImgURL.png";
            }
        };

        MoonPhaseOutputBoundary moonPhasePresenter = new MoonPhaseOutputBoundary() {

            @Override
            public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) throws IOException {
                fail("Successful display is unexpected");
            }

            @Override
            public void back() {
                fail("Return to home view is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid value. Ensure that latitude is between -90.00 and 90.00 and longitude is between -180.00 and 180.00.", error);
            }
        };

        MoonPhaseInputBoundary moonPhaseInteractor = new MoonPhaseInteractor(moonPhaseDAO, moonPhasePresenter);
        moonPhaseInteractor.execute(moonPhaseInputData);
    }

    @Test
    public void moonPhaseIncorrectLatitudeAndLongitudeValueTest() throws IOException {
        MoonPhaseInputData moonPhaseInputData = new MoonPhaseInputData("-200.00", "-200.00", "2024-11-06");

        AstronomyApiDataAccessObject moonPhaseDAO = new MoonPhaseDataAccessObject() {

            public String executeQuery(String query) {
                return "moonChartImgURL.png";
            }
        };

        MoonPhaseOutputBoundary moonPhasePresenter = new MoonPhaseOutputBoundary() {

            @Override
            public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) throws IOException {
                fail("Successful display is unexpected");
            }

            @Override
            public void back() {
                fail("Return to home view is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid value. Ensure that latitude is between -90.00 and 90.00 and longitude is between -180.00 and 180.00.", error);
            }
        };

        MoonPhaseInputBoundary moonPhaseInteractor = new MoonPhaseInteractor(moonPhaseDAO, moonPhasePresenter);
        moonPhaseInteractor.execute(moonPhaseInputData);
    }

    @Test
    public void backTest() {
        AstronomyApiDataAccessObject moonPhaseDAO = new MoonPhaseDataAccessObject() {
            @Override
            public String executeQuery(String query) {
                return "moonChartImgURL.png";
            }
        };

        MoonPhaseOutputBoundary moonPhasePresenter = new MoonPhaseOutputBoundary() {
            @Override
            public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) {
                fail("Successful display is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Fail view is unexpected");
            }
            @Override
            public void back() {
                // Expected case
            }
        };

        MoonPhaseInputBoundary moonPhaseInteractor = new MoonPhaseInteractor(moonPhaseDAO, moonPhasePresenter);
        moonPhaseInteractor.execute();
    }

}
