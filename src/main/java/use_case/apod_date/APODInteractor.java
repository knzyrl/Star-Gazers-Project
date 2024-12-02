package use_case.apod_date;

import data_access.AstronomyPictureApiDataAccessObject;
import org.json.JSONObject;
import org.json.JSONException;
import view.ViewManager;

public class APODInteractor implements APODInputBoundary {
    private final APODOutputBoundary outputBoundary;
    private final AstronomyPictureApiDataAccessObject dataAccessObject;
    private final ViewManager viewManager;

    public APODInteractor(APODOutputBoundary outputBoundary, AstronomyPictureApiDataAccessObject dataAccessObject, ViewManager viewManager) {
        this.outputBoundary = outputBoundary;
        this.dataAccessObject = dataAccessObject;
        this.viewManager = viewManager;
    }

    @Override
    public void fetchAPOD() {
        String jsonResponse = dataAccessObject.fetchAstronomyPicture();
        processResponse(jsonResponse);
    }

    public void fetchAPODByDate(String date) {
        String jsonResponse = dataAccessObject.fetchAstronomyPictureByDate(date);
        processResponse(jsonResponse);
    }

    public void goBackToHome() {
        viewManager.show("home");
    }

    private void processResponse(String jsonResponse) {
        try {
            JSONObject json = new JSONObject(jsonResponse);
            String title = json.optString("title", "No Title");
            String description = json.optString("explanation", "No Description");
            String mediaType = json.optString("media_type", "image");
            String url = json.optString("url", "");
            String thumbnailUrl = json.optString("thumbnail_url", url); // Use thumbnail for videos

            APODOutputData outputData = new APODOutputData(title, description, mediaType, url, thumbnailUrl);
            outputBoundary.presentAPOD(outputData);
        } catch (JSONException e) {
            // Handle invalid JSON gracefully (log, fail silently, etc.)
            System.err.println("Failed to process response: Invalid JSON format");
        }
    }

}
