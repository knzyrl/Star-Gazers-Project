package interface_adapter.geocoding;

public class DisplayGeocodingController {
    private DisplayGeocodingPresenter presenter;

    public DisplayGeocodingController(DisplayGeocodingPresenter presenter) {
        this.presenter = presenter;
    }

    public void execute() {
        presenter.execute();
    }
}
