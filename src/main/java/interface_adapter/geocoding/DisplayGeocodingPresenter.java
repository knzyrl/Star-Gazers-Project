package interface_adapter.geocoding;

import view.ViewManager;

/**
 * Presenter for managing the display of geocoding information.
 */
public class DisplayGeocodingPresenter {
    private ViewManager viewManager;

    public DisplayGeocodingPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Executes the display geocoding use case.
     * This method switches the current view to the "Geocoder view".
     */
    public void execute() {
        viewManager.show("Geocoder view");
    }
}
