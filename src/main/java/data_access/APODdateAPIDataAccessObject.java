package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APODdateAPIDataAccessObject {
    private static final String API_KEY = "t0ffL1YMYJdWoGmEwkozuorP21pLnPtVaPvXdsi2";
    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod";

    public String fetchAPOD() {
        return fetchAPODByDate(null);
    }

    public String fetchAPODByDate(String date) {
        try {
            String urlStr = BASE_URL + "?api_key=" + API_KEY;
            if (date != null && !date.isEmpty()) {
                urlStr += "&date=" + date; // Add the date parameter
            }

            URL url = new URL(urlStr);
            System.out.println("API Request URL: " + url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            } else {
                throw new RuntimeException("Failed to fetch APOD: HTTP error code " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching APOD data: " + e.getMessage(), e);
        }
    }
}
