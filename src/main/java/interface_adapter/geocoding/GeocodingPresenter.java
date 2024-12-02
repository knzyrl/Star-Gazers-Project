package interface_adapter.geocoding;

import entity.Location;
import use_case.geocoding.GeocodingOutputBoundary;
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
     * Displays the location details in the geocoder view and transitions to the geocoder display view.
     *
     * @param location The {@link Location} object containing geocoding details.
     */
    public void execute(Location location) {
        displayGeocoderView.setLocation(location);

        displayGeocoderView.displayLocation();

        viewManager.getViews().add(displayGeocoderView, "display geocoder");
        viewManager.show("display geocoder");
    }

    /**
     * Navigates back to the Geocoder view.
     */
    public void executeBack() {
        viewManager.show("Geocoder view");
    }

    /**
     * Handles the case when no address is found and transitions to the "No address found" view.
     */
    public void noAddressFound() {
        viewManager.show("No address found");
    }

    /**
     * Navigates back to the home view.
     */
    public void executeHome() {
        viewManager.show("home");
    }

    /**
     * Navigates to the "Name Geocoder" view.
     */
    public void executeName() {
        viewManager.show("Name Geocoder view");
    }
}
