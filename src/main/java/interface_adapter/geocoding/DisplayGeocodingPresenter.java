package interface_adapter.geocoding;

import view.ViewManager;

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
