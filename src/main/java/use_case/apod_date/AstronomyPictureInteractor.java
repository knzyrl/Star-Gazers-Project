package use_case.apod_date;

import org.json.JSONObject;

import data_access.AstronomyPictureApiDataAccessObject;
import view.ViewManager;

/**
 * Interactor for the Astronomy Picture of the Day (APOD) use case.
 */
public class AstronomyPictureInteractor implements AstronomyPictureInputBoundary {
    private final AstronomyPictureOutputBoundary outputBoundary;
    private final AstronomyPictureApiDataAccessObject dataAccessObject;
    private final ViewManager viewManager;

    public AstronomyPictureInteractor(AstronomyPictureOutputBoundary outputBoundary,
                                      AstronomyPictureApiDataAccessObject dataAccessObject, ViewManager viewManager) {
        this.outputBoundary = outputBoundary;
        this.dataAccessObject = dataAccessObject;
        this.viewManager = viewManager;
    }

    /**
     * Fetches the Astronomy Picture of the Day (APOD) for the current date and processes the response.
     */
    @Override
    public void fetchAstronomyPicture() {
        final String jsonResponse = dataAccessObject.fetchAstronomyPicture();
        processResponse(jsonResponse);
    }

    /**
     * Fetches the Astronomy Picture of the Day (APOD) for a specific date and processes the response.
     *
     * @param date The date for which to fetch the APOD, in "YYYY-MM-DD" format.
     */
    public void fetchAstronomyPictureByDate(String date) {
        final String jsonResponse = dataAccessObject.fetchAstronomyPictureByDate(date);
        processResponse(jsonResponse);
    }

    /**
     * Navigates back to the home view.
     */
    public void goBackToHome() {
        viewManager.show("home");
    }

    /**
     * Processes the JSON response from the API and passes the formatted data to the output boundary.
     *
     * @param jsonResponse The raw JSON response from the API.
     */
    private void processResponse(String jsonResponse) {
        final JSONObject json = new JSONObject(jsonResponse);
        final String title = json.optString("title", "No Title");
        final String description = json.optString("explanation", "No Description");
        final String mediaType = json.optString("media_type", "image");
        final String url = json.optString("url", "");
        // Use thumbnail for videos
        final String thumbnailUrl = json.optString("thumbnail_url", url);

        final AstronomyPictureOutputData outputData = new AstronomyPictureOutputData(title, description,
                mediaType, url, thumbnailUrl);
        outputBoundary.presentAstronomyPicture(outputData);
    }
}
