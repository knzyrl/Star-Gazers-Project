package use_case.apod_date;

import data_access.APODDateAPIDataAccessObject;
import org.json.JSONObject;

public class APODInteractor implements APODInputBoundary {
    private final APODOutputBoundary outputBoundary;
    private final APODDateAPIDataAccessObject dataAccessObject;

    public APODInteractor(APODOutputBoundary outputBoundary, APODDateAPIDataAccessObject dataAccessObject) {
        this.outputBoundary = outputBoundary;
        this.dataAccessObject = dataAccessObject;
    }

    @Override
    public void fetchRandomAPOD() {
        try {
            // Delegate API call to the Data Access Object
            String response = dataAccessObject.fetchRandomAPOD();

            // Parse the JSON response
            JSONObject json = new JSONObject(response);
            String imageUrl = json.optString("url", "No Image Available");
            String description = json.optString("explanation", "No Description Available");
            String title = json.optString("title", "Untitled");

            // Create the output data and pass it to the output boundary
            APODOutputData outputData = new APODOutputData(imageUrl, description, title);
            outputBoundary.presentAPOD(outputData);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process APOD data: " + e.getMessage(), e);
        }
    }
}
