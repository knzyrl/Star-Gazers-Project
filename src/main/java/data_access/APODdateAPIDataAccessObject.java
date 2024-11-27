package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APODdateAPIDataAccessObject {
    private static final String API_KEY = "t0ffL1YMYJdWoGmEwkozuorP21pLnPtVaPvXdsi2"; // Replace with your NASA API key
    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod";

    /**
     * Fetches APOD data from the NASA API.
     *
     * @return JSON response as a string.
     */
    public String fetchAPOD() {
        try {
            // Construct the URL
            URL url = new URL(BASE_URL + "?api_key=" + API_KEY);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString(); // Return JSON response
            } else {
                throw new RuntimeException("Failed to fetch APOD: HTTP error code " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching APOD data: " + e.getMessage(), e);
        }
    }
}
