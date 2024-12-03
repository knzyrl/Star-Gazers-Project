package interface_adapter.apod_date;

import use_case.apod_date.APODOutputBoundary;
import use_case.apod_date.APODOutputData;
import view.APODView;

/**
 * Presenter for the APOD use case.
 * Formats and presents the Astronomy Picture of the Day (APOD) data to the view.
 */
public class APODPresenter implements APODOutputBoundary {
    private final APODView view;

    public APODPresenter(APODView view) {
        this.view = view;
    }

    /**
     * Presents the APOD data to the view.
     *
     * @param outputData The APOD output data containing title, description, media type, and URLs.
     */
    @Override
    public void presentAPOD(APODOutputData outputData) {
        view.displayAPOD(
                outputData.getTitle(),
                outputData.getDescription(),
                outputData.getMediaType(),
                outputData.getUrl(),
                outputData.getThumbnailUrl()
        );
    }
}
