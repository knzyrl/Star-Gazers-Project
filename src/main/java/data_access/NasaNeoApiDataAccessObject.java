package data_access;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

/**
 * Data Access Object for retrieving Near-Earth Object (NEO) data from NASA's NeoWs API.
 * This class provides methods to fetch NEO data for a specified date range.
 */
public class NasaNeoApiDataAccessObject implements NasaNeoDataAccessObject {
    private static final String BASE_URL = "https://api.nasa.gov/neo/rest/v1/feed";
    private static final String API_KEY = "Qv2W2djvAWmcsnAxcB7l7A2iSmwh7t8Vc0s7OiXa";
    private static final int HTTP_OK = 200;

    @Override
    public String fetchNearEarthObjects(String startDate, String endDate) {
        final String query = String.format("?start_date=%s&end_date=%s&api_key=%s", startDate, endDate, API_KEY);

        final HttpResponse<String> response = Unirest.get(BASE_URL + query)
                .asString();

        if (response.getStatus() == HTTP_OK) {
            return response.getBody();
        }
        else {
            throw new RuntimeException("Failed to fetch data. HTTP Response Code: " + response.getStatus());
        }
    }
}
