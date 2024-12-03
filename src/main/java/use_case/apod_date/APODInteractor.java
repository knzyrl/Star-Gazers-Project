package use_case.apod_date;

import data_access.AstronomyPictureDataAccessObject;
import org.json.JSONObject;
import org.json.JSONException;
import view.ViewManager;

public class APODInteractor implements APODInputBoundary {
    private final APODOutputBoundary outputBoundary;
    private final AstronomyPictureDataAccessObject dataAccessObject;
    private final ViewManager viewManager;

    public APODInteractor(APODOutputBoundary outputBoundary, AstronomyPictureDataAccessObject dataAccessObject, ViewManager viewManager) {
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
            final JSONObject json = new JSONObject(jsonResponse);
            final String title = json.optString("title", "No Title");
            final String description = json.optString("explanation", "No Description");
            final String mediaType = json.optString("media_type", "image");
            final String url = json.optString("url", "");
            final String thumbnailUrl = json.optString("thumbnail_url", url);

            final APODOutputData outputData = new APODOutputData(title, description, mediaType, url, thumbnailUrl);
            outputBoundary.presentAPOD(outputData);
        }
        catch (JSONException e) {
            System.err.println("Failed to process response: Invalid JSON format");
        }
    }
}
