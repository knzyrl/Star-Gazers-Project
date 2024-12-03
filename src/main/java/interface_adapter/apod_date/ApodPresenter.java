package interface_adapter.apod_date;

import use_case.apod_date.APODOutputData;
import use_case.apod_date.AstronomicalPictureOutputBoundary;
import view.APODView;

/**
 * Presenter for the APOD use case.
 * Formats and presents the Astronomy Picture of the Day (APOD) data to the view.
 */
public class ApodPresenter implements AstronomicalPictureOutputBoundary {
    private final APODView view;

    public ApodPresenter(APODView view) {
        this.view = view;
    }

    /**
     * Presents the APOD data to the view.
     *
     * @param outputData The APOD output data containing title, description, media type, and URLs.
     */
    @Override
    public void presentAstronomicalPicture(APODOutputData outputData) {
        view.displayAPOD(
                outputData.getTitle(),
                outputData.getDescription(),
                outputData.getMediaType(),
                outputData.getUrl(),
                outputData.getThumbnailUrl()
        );
    }
}
