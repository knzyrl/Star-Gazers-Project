package interface_adapter.APOD_date;

import view.ViewManager;

public class APODPresenter {
    private final ViewManager viewManager;

    public APODPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void execute() {
        // Navigate to the APOD view using the ViewManager
        viewManager.show("apod"); // Ensure "apod" is registered in your ViewManager
    }
}
