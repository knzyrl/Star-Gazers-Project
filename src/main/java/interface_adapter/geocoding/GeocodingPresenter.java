package interface_adapter.geocoding;

import entity.Location;
import use_case.geocoding.GeocodingOutputBoundary;
import view.DisplayGeocoderView;
import view.ViewManager;

public class GeocodingPresenter implements GeocodingOutputBoundary {
    private ViewManager viewManager;
    private DisplayGeocoderView displayGeocoderView;

    public GeocodingPresenter(ViewManager viewManager, DisplayGeocoderView displayGeocoderView) {
        this.viewManager = viewManager;
        this.displayGeocoderView = displayGeocoderView;
    }

    public void execute(Location location) {
        displayGeocoderView.setLocation(location);

        displayGeocoderView.displayLocation();

        viewManager.getViews().add(displayGeocoderView, "display geocoder");
        viewManager.show("display geocoder");
    }

    public void executeBack() {
        viewManager.show("home");
    }

    public void noAddressFound() {
        viewManager.show("No address found");
    }
}