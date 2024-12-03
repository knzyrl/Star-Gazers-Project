package interface_adapter.geocoding;

/**
 * Class for the controller for Display Geocoding.
 * Calls DisplayGeocodingPresenter when the user clicks the Back button.
 */
public class DisplayGeocodingController {
    private DisplayGeocodingPresenter presenter;

    public DisplayGeocodingController(DisplayGeocodingPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Calls the presenter to execute the return to home.
     */
    public void execute() {
        presenter.execute();
    }
}
