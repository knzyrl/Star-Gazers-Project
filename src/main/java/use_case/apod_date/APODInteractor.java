package use_case.apod_date;

import data_access.APODdateAPIDataAccessObject;
import org.json.JSONObject;

public class APODInteractor implements APODInputBoundary {
    private final APODOutputBoundary outputBoundary;
    private final APODdateAPIDataAccessObject dataAccessObject;

    public APODInteractor(APODOutputBoundary outputBoundary, APODdateAPIDataAccessObject dataAccessObject) {
        this.outputBoundary = outputBoundary;
        this.dataAccessObject = dataAccessObject;
    }

    @Override
    public void fetchAPOD() {
        // Fetch data from API
        String jsonResponse = dataAccessObject.fetchAPOD();
        System.out.println("APODInteractor: API Response - " + jsonResponse);

        // Parse the JSON response
        JSONObject json = new JSONObject(jsonResponse);
        String title = json.optString("title", "No Title");
        String description = json.optString("explanation", "No Description");
        String imageUrl = json.optString("url", "");

        // Create output data and send to presenter
        APODOutputData outputData = new APODOutputData(title, description, imageUrl);
        System.out.println("Parsed Data - Title: " + title + ", URL: " + imageUrl);
        outputBoundary.presentAPOD(outputData);
    }
}
