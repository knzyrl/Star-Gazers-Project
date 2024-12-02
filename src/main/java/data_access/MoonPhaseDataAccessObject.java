package data_access;

import java.util.Base64;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

/**
 * Data Access Object for interacting with the Moon Phase API.
 * Provides methods to execute queries for moon phase data using Astronomy API.
 */
public class MoonPhaseDataAccessObject implements AstronomyApiDataAccessObject {

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
        final String appId = "44f82562-233e-4f52-af5d-ca47ea0decc6";
        final String appSecret = "7e5f03d003ca9fb895eec14a61da205ae1a577578911b4abfde55226093e7dd5ff34eec5bc"
                + "f8a9bdb6881a5cda86ef312cc1ef0c466f647bbc3a429db7cceb0c25015d0a67653f07de92d3577c82a6a44af9e0"
                + "51eb2aa8c30f0fac00887d322d3992a1125a6b630bc94e3e122cf28d6b";
        final String authString = Base64.getEncoder().encodeToString((appId + ":" + appSecret).getBytes());

        final HttpResponse<String> response = Unirest.post("https://api.astronomyapi.com/api/v2/studio/moon-phase")
                .header("Authorization", String.format("Basic %s", authString))
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
        System.out.println(moondao.executeQuery("{\"style\":{\"moonStyle\":\"default\","
                + "\"backgroundStyle\":\"stars\",\"backgroundColor\":\"#000000\",\"headingColor\":\"#ffffff\","
                + "\"textColor\":\"#ffffff\"},\"observer\":{\"latitude\":33.775867,\"longitude\":-84.39733,\"date"
                + "\":\"2024-11-08\"},\"view\":{\"type\":\"portrait-simple\",\"parameters\":{}}}"));
    }
}
