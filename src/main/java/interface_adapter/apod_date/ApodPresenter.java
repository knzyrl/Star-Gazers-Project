package interface_adapter.apod_date;

import interface_adapter.ViewManager;
import use_case.apod_date.ApodOutputBoundary;
import use_case.apod_date.ApodOutputData;

/**
 * Presenter for the APOD use case.
 * Formats and presents the Astronomy Picture of the Day (APOD) data to the view.
 */
public class ApodPresenter implements ApodOutputBoundary {
    private final ViewManager viewManager;

    public ApodPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Presents the APOD data to the view.
     *
     * @param outputData The APOD output data containing title, description, media type, and URLs.
     */
    @Override
    public void presentApod(ApodOutputData outputData) {
        viewManager.displayApod(outputData);
    }
}
