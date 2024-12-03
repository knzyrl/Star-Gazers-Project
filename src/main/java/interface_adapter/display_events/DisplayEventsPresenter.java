package interface_adapter.display_events;

import view.ViewManager;

public class DisplayEventsPresenter {
    private ViewManager viewManager;

    public DisplayEventsPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Calls the ViewManager to return to the home view.
     */
    public void execute() {
        viewManager.show("home");
    }
}
