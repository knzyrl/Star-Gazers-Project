package use_case;

import data_access.EventsDataAccessObject;
import kong.unirest.core.json.JSONArray;
import kong.unirest.core.json.JSONObject;
import org.junit.jupiter.api.Test;
import use_case.events.*;

import static org.junit.jupiter.api.Assertions.*;

class EventsInteractorTest {
    @Test
    void successTest() {
        EventsInputData inputData = new EventsInputData("0.00", "0.00", "1970-01-01", "1970-01-01", "body");
        EventsDataAccessObject mockDAO = new EventsDataAccessObject() {
            @Override
            public JSONObject executeQuery(String query) {
                JSONObject response = new JSONObject();
                JSONArray dates = new JSONArray();
                dates.put("1970-01-01");
                response.put("header", dates);
                JSONArray events = new JSONArray();
                response.put("rows", events);
                JSONObject row = new JSONObject();
                events.put(row);
                JSONArray cells = new JSONArray();
                row.put("cells", cells);
                JSONObject cell = new JSONObject();
                cells.put(cell);
                cell.put("type", "total_solar_eclipse");
                return response;
            }
        };

        EventsOutputBoundary eventsPresenter = new EventsOutputBoundary() {
            @Override
            public void displayEvents(EventsOutputData eventsOutputData) {
                assertEquals("0.00", eventsOutputData.getLongitude());
                assertEquals("0.00", eventsOutputData.getLatitude());
                assertEquals("1970-01-01", eventsOutputData.getDateStart());
                assertEquals("1970-01-01", eventsOutputData.getDateEnd());
                assertEquals("body", eventsOutputData.getBody());
                assertEquals("total_solar_eclipse", eventsOutputData.getEventsList().get(0).getType());
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

        EventsInputBoundary eventsInteractor = new EventsInteractor(mockDAO, eventsPresenter);
        eventsInteractor.execute(inputData);
    }

    @Test
    void longitudeFormatFailTest() {
        EventsDataAccessObject mockDAO = new EventsDataAccessObject();

        EventsInputData inputData = new EventsInputData("longitude", "0.00", "1970-01-01", "1970-01-01", "body");

        EventsOutputBoundary eventsPresenter = new EventsOutputBoundary() {
            @Override
            public void displayEvents(EventsOutputData eventsOutputData) {
                fail("Success unexpected.");
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

        EventsInputBoundary eventsInteractor = new EventsInteractor(mockDAO, eventsPresenter);
        eventsInteractor.execute(inputData);
    }

    @Test
    void longitudeBoundFailTest() {
        EventsDataAccessObject mockDAO = new EventsDataAccessObject();

        EventsInputData inputData = new EventsInputData("9999.99", "0.00", "1970-01-01", "1970-01-01", "body");

        EventsOutputBoundary eventsPresenter = new EventsOutputBoundary() {
            @Override
            public void displayEvents(EventsOutputData eventsOutputData) {
                fail("Success unexpected.");
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

        EventsInputBoundary eventsInteractor = new EventsInteractor(mockDAO, eventsPresenter);
        eventsInteractor.execute(inputData);
    }

    @Test
    void latitudeFormatFailTest() {
        EventsDataAccessObject mockDAO = new EventsDataAccessObject();

        EventsInputData inputData = new EventsInputData("0.00", "latitude", "1970-01-01", "1970-01-01", "body");

        EventsOutputBoundary eventsPresenter = new EventsOutputBoundary() {
            @Override
            public void displayEvents(EventsOutputData eventsOutputData) {
                fail("Success unexpected.");
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

        EventsInputBoundary eventsInteractor = new EventsInteractor(mockDAO, eventsPresenter);
        eventsInteractor.execute(inputData);
    }

    @Test
    void latitudeBoundFailTest() {
        EventsDataAccessObject mockDAO = new EventsDataAccessObject();

        EventsInputData inputData = new EventsInputData("0.00", "-9999.99", "1970-01-01", "1970-01-01", "body");

        EventsOutputBoundary eventsPresenter = new EventsOutputBoundary() {
            @Override
            public void displayEvents(EventsOutputData eventsOutputData) {
                fail("Success unexpected.");
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

        EventsInputBoundary eventsInteractor = new EventsInteractor(mockDAO, eventsPresenter);
        eventsInteractor.execute(inputData);
    }

    @Test
    void dateFormatFailTest() {
        EventsDataAccessObject mockDAO = new EventsDataAccessObject();

        EventsInputData inputData = new EventsInputData("0.00", "0.00", "dateStart", "dateEnd", "body");

        EventsOutputBoundary eventsPresenter = new EventsOutputBoundary() {
            @Override
            public void displayEvents(EventsOutputData eventsOutputData) {
                fail("Success unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Dates invalid. Please ensure the inputs are valid dates in YYYY-MM-DD format.",
                        errorMessage);
            }

            @Override
            public void back() {
                fail("Return to home view unexpected.");
            }
        };

        EventsInputBoundary eventsInteractor = new EventsInteractor(mockDAO, eventsPresenter);
        eventsInteractor.execute(inputData);
    }

    @Test
    void backTest() {
        EventsDataAccessObject mockDAO = new EventsDataAccessObject();

        EventsOutputBoundary eventsPresenter = new EventsOutputBoundary() {
            @Override
            public void displayEvents(EventsOutputData eventsOutputData) {
                fail("Success unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure unexpected.");
            }

            @Override
            public void back() {
                // This is expected
            }
        };

        EventsInputBoundary eventsInteractor = new EventsInteractor(mockDAO, eventsPresenter);
        eventsInteractor.execute();
    }
}
