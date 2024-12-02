package entity;

import java.util.ArrayList;
import java.util.List;

import kong.unirest.core.json.JSONArray;
import kong.unirest.core.json.JSONObject;

/**
 * Represents a list of astronomical events for a specified celestial body and date range.
 * Parses a JSON response to extract event details and encapsulates them in an {@link EventsList}.
 */
public class EventsList {
    private String longitude;
    private String latitude;
    private String dateStart;
    private String dateEnd;
    private String body;
    private List<Event> eventsList;

    /**
     * Constructs an {@code EventsList} object with the specified parameters and JSON response.
     * Parses the JSON response to populate the list of events.
     *
     * @param longitude The longitude of the observer's location.
     * @param latitude  The latitude of the observer's location.
     * @param dateStart The start date of the event range in the format "YYYY-MM-DD".
     * @param dateEnd   The end date of the event range in the format "YYYY-MM-DD".
     * @param body      The celestial body associated with the events.
     * @param response  A JSON response from the astronomy API containing event details.
     */
    public EventsList(String longitude, String latitude, String dateStart, String dateEnd, String body,
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
