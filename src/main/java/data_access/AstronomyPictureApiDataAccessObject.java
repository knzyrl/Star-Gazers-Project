package data_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Data access object for retrieving Astronomy Picture of the Day (AstronomyPicture) data from NASA's API.
 * This class provides methods to fetch AstronomyPicture data for the current date or a specific date.
 */
public class AstronomyPictureApiDataAccessObject {
    private static final int VALID_RESPONSE_CODE = 200;
    private static final String API_KEY = "t0ffL1YMYJdWoGmEwkozuorP21pLnPtVaPvXdsi2";
    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod";

    /**
     * Fetches the Astronomy Picture of the Day (AstronomyPicture) data for the current date.
     *
     * @return The AstronomyPicture data as a JSON string.
     * @throws RuntimeException If the request fails or an error occurs while fetching the data.
     */
    public String fetchAstronomyPicture() {
        return fetchAstronomyPictureByDate(null);
    }

    /**
     * Fetches the Astronomy Picture of the Day (AstronomyPicture) data for a specified date.
     *
     * @param date The date for which to fetch the AstronomyPicture data, in the format "YYYY-MM-DD".
     *             If null or empty, the current date's AstronomyPicture data is fetched.
     * @return The AstronomyPicture data as a JSON string.
     * @throws RuntimeException If the request fails or an error occurs while fetching the data.
     */
    public String fetchAstronomyPictureByDate(String date) {
        try {
            String urlStr = BASE_URL + "?api_key=" + API_KEY;
            if (date != null && !date.isEmpty()) {
                urlStr += "&date=" + date;
            }

            final URL url = new URL(urlStr);
            System.out.println("API Request URL: " + url);

            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            final int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == VALID_RESPONSE_CODE) {
                final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                final StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            }
            else {
                throw new RuntimeException("Failed to fetch AstronomyPicture: HTTP error code " + responseCode);
            }
        }
        catch (MalformedURLException ex) {
            throw new RuntimeException("Invalid URL format: " + ex.getMessage(), ex);
        }
        catch (IOException ioException) {
            throw new RuntimeException("Error during input/output operation: " + ioException.getMessage(), ioException);
        }
    }
}