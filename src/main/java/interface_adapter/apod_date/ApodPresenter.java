package interface_adapter.apod_date;

import use_case.apod_date.ApodOutputBoundary;
import use_case.apod_date.ApodOutputData;
import view.ApodView;

/**
 * Presenter for the APOD use case.
 * Formats and presents the Astronomy Picture of the Day (APOD) data to the view.
 */
public class ApodPresenter implements ApodOutputBoundary {
    private final ApodView view;

    public ApodPresenter(ApodView view) {
        this.view = view;
    }

    /**
     * Presents the APOD data to the view.
     *
     * @param outputData The APOD output data containing title, description, media type, and URLs.
     */
    @Override
    public void presentApod(ApodOutputData outputData) {
        view.displayApod(
                outputData.getTitle(),
                outputData.getDescription(),
                outputData.getMediaType(),
                outputData.getUrl(),
                outputData.getThumbnailUrl()
        );
    }
}
