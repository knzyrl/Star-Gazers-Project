package use_case.apod_date;

import org.json.JSONException;
import org.json.JSONObject;

import data_access.AstronomyPictureApiDataAccessObject;
import view.ViewManager;

/**
 * Class for Interactor for the Astronomical Picture of the Day use case.
 * Implements AstronomicalPictureInputBoundary.
 * Receives input data from the Controller and process it with the help of the corresponding API and Entity.
 * Sends the output data to the OutputBoundary for display.
 */

public class APODInteractor implements AstronomicalPictureInputBoundary {
    private final AstronomicalPictureOutputBoundary outputBoundary;
    private final AstronomyPictureApiDataAccessObject dataAccessObject;
    private final ViewManager viewManager;

    public APODInteractor(AstronomicalPictureOutputBoundary outputBoundary,
                          AstronomyPictureApiDataAccessObject dataAccessObject,
                          ViewManager viewManager) {
        this.outputBoundary = outputBoundary;
        this.dataAccessObject = dataAccessObject;
        this.viewManager = viewManager;
    }

    @Override
    public void fetchAstronomicalPictureOfTheDay() {
        final String jsonResponse = dataAccessObject.fetchAstronomyPicture();
        processResponse(jsonResponse);
    }

    /**
     * Concrete implementation of the fetchAstronomicalPictureByDate method in AstronomicalPictureInputBoundary.
     * @param date the date specified by the user for which they wish to see the Astronomy Picture.
     */
    public void fetchAstronomicalPictureByDate(String date) {
        final String jsonResponse = dataAccessObject.fetchAstronomyPictureByDate(date);
        processResponse(jsonResponse);
    }

    /**
     * Concrete implementation of the goBackToHome method in AstronomicalPictureInputBoundary.
     */
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
            outputBoundary.presentAstronomicalPicture(outputData);
        }
        catch (JSONException error) {
            System.err.println("Failed to process response: Invalid JSON format");
        }
    }
}
