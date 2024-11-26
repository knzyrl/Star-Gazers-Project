package data_access;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

public class APODdateAPIDataAccessObject {
    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod";
    private static final String API_KEY = "t0ffL1YMYJdWoGmEwkozuorP21pLnPtVaPvXdsi2";

    /**
     * Fetches the Astronomy Picture of the Day data from NASA's APOD API for a specific date.
     *
     * @param date The date for which to fetch APOD data (YYYY-MM-DD).
     * @return Raw JSON response from the API.
     */
    public String fetchAPODByDate(String date) {
        String query = String.format("?date=%s&api_key=%s", date, API_KEY);

        HttpResponse<String> response = Unirest.get(BASE_URL + query)
                .asString();

        if (response.getStatus() == 200) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch data. HTTP Response Code: " + response.getStatus());
        }
    }

    /**
     * Fetches the Astronomy Picture of the Day data for a random date.
     * If no date is specified, the API defaults to today's date.
     *
     * @return Raw JSON response from the API.
     */
    public String fetchRandomAPOD() {
        String query = String.format("?api_key=%s", API_KEY);

        HttpResponse<String> response = Unirest.get(BASE_URL + query)
                .asString();

        if (response.getStatus() == 200) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch data. HTTP Response Code: " + response.getStatus());
        }
    }

}
