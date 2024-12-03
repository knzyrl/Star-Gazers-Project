package interface_adapter.geocoding;

import use_case.geocoding.GeocodingOutputBoundary;
import use_case.geocoding.GeocodingOutputData;
import view.DisplayGeocoderView;
import view.ViewManager;

/**
 * Presenter for the geocoding use case.
 */
public class GeocodingPresenter implements GeocodingOutputBoundary {
    private ViewManager viewManager;
    private DisplayGeocoderView displayGeocoderView;

    public GeocodingPresenter(ViewManager viewManager, DisplayGeocoderView displayGeocoderView) {
        this.viewManager = viewManager;
        this.displayGeocoderView = displayGeocoderView;
    }

    /**
     * Calls the ViewManager to display the result.
     * @param geocodingOutputData the data from the interactor
     */
    public void execute(GeocodingOutputData geocodingOutputData) {
        displayGeocoderView.setLocation(geocodingOutputData);

        displayGeocoderView.displayLocation();

        viewManager.getViews().add(displayGeocoderView, "display geocoder");
        viewManager.show("display geocoder");
    }

    /**
     * Calls the ViewManager to return to the previous view.
     */
    public void executeBack() {
        viewManager.show("Geocoder view");
    }

    /**
     * Calls the ViewManager to switch to the "no address found" view.
     */
    public void noAddressFound() {
        viewManager.show("No address found");
    }

    /**
     * Calls the ViewManager to return to the home view.
     */
    public void executeHome() {
        viewManager.show("home");
    }

    /**
     * Calls the ViewManager to swtich to the name geocoder view.
     */
    public void executeName() {
        viewManager.show("Name Geocoder view");
    }
}
