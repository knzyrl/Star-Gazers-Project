package use_case.apod_date;

import data_access.APODdateAPIDataAccessObject;
import interface_adapter.APOD_date.APODPresenter;
import org.json.JSONObject;

public class APODInteractor implements APODInputBoundary {
    private final APODPresenter outputBoundary;
    private final APODdateAPIDataAccessObject dataAccessObject;

    public APODInteractor(APODPresenter outputBoundary, APODdateAPIDataAccessObject dataAccessObject) {
        this.outputBoundary = outputBoundary;
        this.dataAccessObject = dataAccessObject;
    }

    //interactor execute

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
            outputBoundary.execute();
        } catch (Exception e) {
            throw new RuntimeException("Failed to process APOD data: " + e.getMessage(), e);
        }
    }
}


