package interface_adapter.apod_date;

import javax.swing.SwingUtilities;

import use_case.apod_date.APODOutputBoundary;
import use_case.apod_date.APODOutputData;
import view.APODView;

/**
 * Class for Presenter for the Astronomical Picture of the Day use case.
 * Gets output data from the ApodInteractor.
 * Updates the view being displayed to the user by communicating with the ViewManager.
 */

public class ApodPresenter implements APODOutputBoundary {
    private final APODView view;

    public ApodPresenter(APODView view) {
        this.view = view;
    }

    @Override
    public void presentAPOD(APODOutputData outputData) {
        // Print the URL for debugging
        System.out.println("AstronomyPicture URL: " + outputData.getUrl());

        SwingUtilities.invokeLater(() -> {
            final String title = outputData.getTitle();
            final String description = outputData.getDescription();
            final String mediaType = outputData.getMediaType();
            final String url = outputData.getUrl();
            final String thumbnailUrl = outputData.getThumbnailUrl();

            view.displayAPOD(title, description, mediaType, url, thumbnailUrl);
        });
    }
}
