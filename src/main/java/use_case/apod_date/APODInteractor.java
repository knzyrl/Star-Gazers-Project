package use_case.apod_date;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;


public class APODInteractor {
    private final APODOutputBoundary outputBoundary;

    public APODInteractor(APODOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void fetchRandomAPOD() {
        try {
            String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=t0ffL1YMYJdWoGmEwkozuorP21pLnPtVaPvXdsi2";
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = new Scanner(connection.getInputStream()).useDelimiter("\\A").next();
                JSONObject json = new JSONObject(response);

                String imageUrl = json.optString("url", "No Image Available");
                String description = json.optString("explanation", "No Description Available");
                String title = json.optString("title", "Untitled");

                APODOutputData outputData = new APODOutputData(imageUrl, description, title);
                outputBoundary.presentAPOD(outputData);
            } else {
                System.out.println("Error: API response code " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
