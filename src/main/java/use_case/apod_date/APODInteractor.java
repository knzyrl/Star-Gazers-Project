package use_case.apod_date;

import data_access.APODdateAPIDataAccessObject;
import org.json.JSONObject;
import view.ViewManager;

public class APODInteractor implements APODInputBoundary {
    private final APODOutputBoundary outputBoundary;
    private final APODdateAPIDataAccessObject dataAccessObject;
    private final ViewManager viewManager;

    public APODInteractor(APODOutputBoundary outputBoundary, APODdateAPIDataAccessObject dataAccessObject, ViewManager viewManager) {
        this.outputBoundary = outputBoundary;
        this.dataAccessObject = dataAccessObject;
        this.viewManager = viewManager;
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

    public void goBackToHome(){
        System.out.println("APODInteractor: navigating back to home");
        viewManager.show("home"); // Navigate to the home view
    }
}
