package use_case;

import data_access.EventsDataAccessObject;
import entity.EventsList;
import kong.unirest.core.json.JSONArray;
import kong.unirest.core.json.JSONObject;
import org.junit.Test;
import use_case.events.EventsInputBoundary;
import use_case.events.EventsInputData;
import use_case.events.EventsInteractor;
import use_case.events.EventsOutputBoundary;

import static org.junit.Assert.*;

public class EventsInteractorTest {
    @Test
    public void eventsTest() {
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
            public void displayEvents(EventsList eventsList) {
                assertEquals("0.00", eventsList.getLongitude());
                assertEquals("0.00", eventsList.getLatitude());
                assertEquals("1970-01-01", eventsList.getDateStart());
                assertEquals("1970-01-01", eventsList.getDateEnd());
                assertEquals("body", eventsList.getBody());
                assertEquals("total_solar_eclipse", eventsList.getEventsList().get(0).getType());
            }

            @Override
            public void back() {
                fail("Return to home view unexpected.");
            }
        };

        EventsInputBoundary eventsInteractor = new EventsInteractor(mockDAO, eventsPresenter);
        eventsInteractor.execute(inputData);
    }
}
