package use_case.events;

import java.util.ArrayList;
import java.util.List;

import entity.Event;
import kong.unirest.core.json.JSONArray;
import kong.unirest.core.json.JSONObject;

/**
 * Class for output data for the Events use case.
 */
public class EventsOutputData {
    private String longitude;
    private String latitude;
    private String dateStart;
    private String dateEnd;
    private String body;
    private List<Event> eventsList;

    public EventsOutputData(String longitude, String latitude, String dateStart, String dateEnd, String body,
                            JSONObject response) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.body = body;

        eventsList = new ArrayList<>();
        final JSONArray dates = response.getJSONArray("header");
        final JSONArray events = response.getJSONArray("rows");
        for (int i = 0; i < dates.length(); i++) {
            final String type = events.getJSONObject(i).getJSONArray("cells").getJSONObject(0).get("type").toString();
            final String date = dates.get(i).toString().substring(0, 10);
            final Event event = new Event(this.body, type, date);
            eventsList.add(event);
        }
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getBody() {
        return body;
    }

    public List<Event> getEventsList() {
        return eventsList;
    }
}
