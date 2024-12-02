package data_access;

import kong.unirest.core.Unirest;
import kong.unirest.core.json.JSONObject;

/**
 * Data Access Object for retrieving astronomical events data from the Astronomy API.
 * This class provides methods to execute queries and fetch event data, such as moon phases or other celestial events.
 */
public class EventsDataAccessObject extends AstronomyApiDataAccessObject {

    /**
     * Executes an HTTP GET query to fetch astronomical events data from the Astronomy API.
     *
     * @param query The URL query string to execute. This should include all required parameters,
     *              such as latitude, longitude, elevation, date range, and time.
     * @return A {@link JSONObject} containing the table of event data from the API response.
     * @throws RuntimeException If an error occurs while fetching or parsing the API response.
     */
    @Override
    public JSONObject executeQuery(String query) {
        final JSONObject response = Unirest.get(query)
                .header("Authorization", String.format("Basic %s", AUTH_STRING))
                .asJson()
                .getBody()
                .getObject().getJSONObject("data").getJSONObject("table");
        return response;
    }

    /**
     * Main method for testing the EventsDataAccessObject functionality.
     * This method demonstrates how to use the {@link EventsDataAccessObject} to fetch
     * astronomical event data from the Astronomy API. The query retrieves moon events
     * for a specific location (latitude and longitude), elevation, date range, and time.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        final EventsDataAccessObject dao = new EventsDataAccessObject();

        final String query = "https://api.astronomyapi.com/api/v2/bodies/events/moon"
                + "?longitude=-84.39733&latitude=33.775867&elevation=1"
                + "&from_date=2020-12-20&to_date=2022-12-23&time=00%3A00%3A00";

        System.out.println(dao.executeQuery(query));
    }
}
