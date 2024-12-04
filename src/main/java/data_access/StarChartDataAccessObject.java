package data_access;

import java.util.Base64;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

/**
 * Data Access Object for interacting with the Star Chart API.
 * Provides methods to execute queries and retrieve star chart data from the Astronomy API.
 */
public class StarChartDataAccessObject implements AstronomyApiDataAccessObject {
    private final int magicNumber1 = 21;
    private final int magicNumber2 = 3;

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
        final String appId = "44f82562-233e-4f52-af5d-ca47ea0decc6";
        final String appSec = "7e5f03d003ca9fb895eec14a61da205ae1a577578911b4abfde55226093e7dd5ff34eec5bcf8a"
                + "9bdb6881a5cda86ef312cc1ef0c466f647bbc3a429db7cceb0c25015d0a67653f07de92d3577c82a6a44af9e051eb2aa8"
                + "c30f0fac00887d322d3992a1125a6b630bc94e3e122cf28d6b";
        final String authString = Base64.getEncoder().encodeToString((appId + ":" + appSec).getBytes());

        final HttpResponse<String> response = Unirest.post("https://api.astronomyapi.com/api/v2/studio/star-chart")
                .header("Authorization", String.format("Basic %s", authString))
                .body(query)
                .asString();
        return response.getBody().substring(magicNumber1, response.getBody().length() - magicNumber2);
    }
}
