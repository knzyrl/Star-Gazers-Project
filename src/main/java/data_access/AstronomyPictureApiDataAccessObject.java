package data_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Data Access Object for retrieving Astronomy Picture of the Day (APOD) data from NASA's API.
 * Implements the AstronomyPictureDataAccessObject interface.
 */

public class AstronomyPictureApiDataAccessObject implements AstronomyPictureDataAccessObject {
    private static final String API_KEY = "t0ffL1YMYJdWoGmEwkozuorP21pLnPtVaPvXdsi2";
    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod";

    @Override
    public String fetchAstronomyPicture() {
        return fetchAstronomyPictureByDate(null);
    }

    @Override
    public String fetchAstronomyPictureByDate(String date) {
        try {
            String urlStr = BASE_URL + "?api_key=" + API_KEY;
            if (date != null && !date.isEmpty()) {
                urlStr += "&date=" + date;
            }

            final URL url = new URL(urlStr);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            final int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
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
