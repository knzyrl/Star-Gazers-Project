package data_access;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

/**
 * Data Access Object for retrieving Near-Earth Object (NEO) data from NASA's NeoWs API.
 * This class provides methods to fetch NEO data for a specified date range.
 */
public class NasaNeoDataAccessObject {
    private static final String BASE_URL = "https://api.nasa.gov/neo/rest/v1/feed";
    private static final String API_KEY = "Qv2W2djvAWmcsnAxcB7l7A2iSmwh7t8Vc0s7OiXa";

    /**
     * Fetches Near-Earth Objects (NEO) data from NASA's NeoWs API for a specified date range.
     * The date range must not exceed 7 days as per the API's limitations.
     *
     * @param startDate The start date for the data range in the format "YYYY-MM-DD".
     * @param endDate   The end date for the data range in the format "YYYY-MM-DD".
     * @return A raw JSON response from the API containing NEO data.
     * @throws RuntimeException If the HTTP request fails or the API returns a non-200 status code.
     */
    public String fetchNearEarthObjects(String startDate, String endDate) {
        final String query = String.format("?start_date=%s&end_date=%s&api_key=%s", startDate, endDate, API_KEY);

        final HttpResponse<String> response = Unirest.get(BASE_URL + query)
                .asString();

        if (response.getStatus() == 200) {
            return response.getBody();
        }
        else {
            throw new RuntimeException("Failed to fetch data. HTTP Response Code: " + response.getStatus());
        }
    }
}
