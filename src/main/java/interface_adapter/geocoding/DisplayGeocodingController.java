package interface_adapter.geocoding;

/**
 * Controller for managing user actions related to displaying geocoding information.
 */
public class DisplayGeocodingController {
    private DisplayGeocodingPresenter presenter;

    /**
     * Constructs a {@code DisplayGeocodingController} with the specified presenter.
     *
     * @param presenter The presenter responsible for geocoding-related logic.
     */
    public DisplayGeocodingController(DisplayGeocodingPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Executes the display geocoding use case by delegating the request to the presenter.
     */
    public void execute() {
        presenter.execute();
    }
}
