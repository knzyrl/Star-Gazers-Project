package interface_adapter.geocoding;

import interface_adapter.ViewManager;

/**
 * Class for Presenter for DisplayGeocoding.
 * Responsible for presenting the display of the use case.
 * Allows the user to revert to the home screen.
 */
public class DisplayGeocodingPresenter {
    private ViewManager viewManager;

    public DisplayGeocodingPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Calls the ViewManager to return to the home view.
     */
    public void execute() {
        viewManager.show("Geocoder view");
    }
}
