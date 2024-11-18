package data_access;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

public class NASANeoDataAccessObject {
    private static final String BASE_URL = "https://api.nasa.gov/neo/rest/v1/feed";
    private static final String API_KEY = "Qv2W2djvAWmcsnAxcB7l7A2iSmwh7t8Vc0s7OiXa";

    /**
     * Fetches Near-Earth Objects data from NASA's NeoWs API.
     *
     * @param startDate The start date for the data range (YYYY-MM-DD).
     * @param endDate The end date for the data range (YYYY-MM-DD).
     * @return Raw JSON response from the API.
     */
    public String fetchNearEarthObjects(String startDate, String endDate) {
        String query = String.format("?start_date=%s&end_date=%s&api_key=%s", startDate, endDate, API_KEY);

        HttpResponse<String> response = Unirest.get(BASE_URL + query)
                .asString();

        if (response.getStatus() == 200) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch data. HTTP Response Code: " + response.getStatus());
        }
    }
}