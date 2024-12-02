package data_access;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

/**
 * Data Access Object for interacting with the Star Chart API.
 * Provides methods to execute queries and retrieve star chart data from the Astronomy API.
 */
public class StarChartDataAccessObject extends AstronomyApiDataAccessObject {

    /**
     * Executes an HTTP POST query to retrieve star chart data from the Astronomy API.
     * The query should be a JSON-formatted string containing the observer's location,
     * date, and the desired star chart view style and parameters.
     *
     * @param query A JSON-formatted string containing the query parameters.
     * @return A processed {@link String} containing the star chart data response.
     *         The method trims unnecessary metadata from the response body.
     * @throws RuntimeException If an error occurs during the HTTP request or response parsing.
     */
    @Override
    public String executeQuery(String query) {
        final HttpResponse<String> response = Unirest.post("https://api.astronomyapi.com/api/v2/studio/star-chart")
                .header("Authorization", String.format("Basic %s", AUTH_STRING))
                .body(query)
                .asString();
        return response.getBody().substring(21, response.getBody().length() - 3);
    }

    /**
     * Main method for testing the functionality of the StarChartDataAccessObject.
     * Sends a sample JSON query to the Star Chart API and prints the result.
     * The query includes the observer's location, date, and parameters for the star chart view.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        final StarChartDataAccessObject dao = new StarChartDataAccessObject();
        final String query = "{"
                + "\"style\":\"inverted\","
                + "\"observer\":{\"latitude\":33.775867,\"longitude\":-84.39733,\"date\":\"2024-11-04\"},"
                + "\"view\":{\"type\":\"area\",\"parameters\":{"
                + "\"position\":{\"equatorial\":{\"rightAscension\":0,\"declination\":0}},"
                + "\"zoom\":6}}"
                + "}";
        System.out.println(dao.executeQuery(query));
    }
}
