package interface_adapter.geocoding;

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
