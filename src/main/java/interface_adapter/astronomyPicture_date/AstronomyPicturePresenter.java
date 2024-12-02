package interface_adapter.astronomyPicture_date;

import javax.swing.SwingUtilities;

import use_case.apod_date.AstronomyPictureOutputBoundary;
import use_case.apod_date.AstronomyPictureOutputData;
import view.APODView;

/**
 * Presenter for the Astronomy Picture of the Day (APOD) use case.
 */
public class AstronomyPicturePresenter implements AstronomyPictureOutputBoundary {
    private final APODView view;

    public AstronomyPicturePresenter(APODView view) {
        this.view = view;
    }

    /**
     * Formats and displays the APOD data in the view.
     *
     * @param outputData The output data containing APOD information.
     */
    @Override
    public void presentAstronomyPicture(AstronomyPictureOutputData outputData) {
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
