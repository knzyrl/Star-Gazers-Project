package data_access;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

/**
 * Data Access Object for interacting with the Moon Phase API.
 * Provides methods to execute queries for moon phase data using Astronomy API.
 */
public class MoonPhaseDataAccessObject extends AstronomyApiDataAccessObject {

    /**
     * Executes an HTTP POST query to retrieve moon phase data from the Astronomy API.
     *
     * @param query A JSON-formatted string containing the query parameters. The JSON object
     *              should include the style, observer's latitude, longitude, date, and view type.
     * @return A {@link String} containing the processed moon phase data response.
     *         The method trims unnecessary metadata from the response.
     * @throws RuntimeException If an error occurs while executing the query.
     */
    @Override
    public String executeQuery(String query) {
        final HttpResponse<String> response = Unirest.post("https://api.astronomyapi.com/api/v2/studio/moon-phase")
                .header("Authorization", String.format("Basic %s", AUTH_STRING))
                .body(query)
                .asString();
        return response.getBody().substring(21, response.getBody().length() - 3);
    }

    /**
     * Main method for testing the functionality of the MoonPhaseDataAccessObject.
     * Sends a sample JSON query to the Moon Phase API and prints the result.
     * The query includes the observer's location, date, and display style.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        final MoonPhaseDataAccessObject moondao = new MoonPhaseDataAccessObject();
        final String query = "{\"style\":{\"moonStyle\":\"default\",\"backgroundStyle\":\"stars\","
                + "\"backgroundColor\":\"#000000\",\"headingColor\":\"#ffffff\",\"textColor\":\"#ffffff\"},"
                + "\"observer\":{\"latitude\":33.775867,\"longitude\":-84.39733,\"date\":\"2024-11-08\"},"
                + "\"view\":{\"type\":\"portrait-simple\",\"parameters\":{}}}";
        System.out.println(moondao.executeQuery(query));
    }
}
