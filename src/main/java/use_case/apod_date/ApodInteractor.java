package use_case.apod_date;

import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import data_access.AstronomyPictureDataAccessObject;
import view.ViewManager;
/**
 * Interactor for fetching Astronomy Picture of the Day (APOD) data.
 * Implements the input boundary to handle APOD-related use cases.
 */

public class ApodInteractor implements ApodInputBoundary {

    private static final Logger LOGGER = Logger.getLogger(ApodInteractor.class.getName());

    private final ApodOutputBoundary outputBoundary;
    private final AstronomyPictureDataAccessObject dataAccessObject;
    private final ViewManager viewManager;

    /**
     * Constructs an ApodInteractor instance.
     *
     * @param outputBoundary   The output boundary for presenting data.
     * @param dataAccessObject The data access object for fetching APOD data.
     * @param viewManager      The manager for controlling application views.
     */
    public ApodInteractor(
            ApodOutputBoundary outputBoundary,
            AstronomyPictureDataAccessObject dataAccessObject,
            ViewManager viewManager
    ) {
        this.outputBoundary = outputBoundary;
        this.dataAccessObject = dataAccessObject;
        this.viewManager = viewManager;
    }

    /**
     * Fetches the Astronomy Picture of the Day for today.
     */
    @Override
    public void fetchApod() {
        final String astronomyPictureJsonResponse = dataAccessObject.fetchAstronomyPicture();
        processResponse(astronomyPictureJsonResponse);
    }

    /**
     * Fetches the Astronomy Picture of the Day for a specific date.
     *
     * @param date The date for which the APOD should be fetched, formatted as YYYY-MM-DD.
     */
    public void fetchApodByDate(String date) {
        final String astronomyPictureJsonResponse = dataAccessObject.fetchAstronomyPictureByDate(date);
        processResponse(astronomyPictureJsonResponse);
    }

    /**
     * Navigates back to the home view.
     * This method changes the view to the "home" screen of the application.
     */
    public void goBackToHome() {
        viewManager.show("home");
    }

    /**
     * Processes the JSON response from the data access object and extracts relevant APOD data.
     *
     * @param astronomyPictureJsonResponse The JSON response containing APOD data.
     */
    private void processResponse(String astronomyPictureJsonResponse) {
        try {
            final JSONObject json = new JSONObject(astronomyPictureJsonResponse);
            final String title = json.optString("title", "No Title");
            final String description = json.optString("explanation", "No Description");
            final String mediaType = json.optString("media_type", "image");
            final String url = json.optString("url", "");
            final String thumbnailUrl = json.optString("thumbnail_url", url);

            final ApodOutputData outputData = new ApodOutputData(title, description, mediaType, url, thumbnailUrl);
            outputBoundary.presentApod(outputData);
        }
        catch (JSONException jsonException) {
            LOGGER.severe("Failed to process response: Invalid JSON format");
        }
    }
}
