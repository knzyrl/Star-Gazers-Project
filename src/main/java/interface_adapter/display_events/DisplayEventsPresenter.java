package interface_adapter.display_events;

import view.ViewManager;

/**
 * Class for Presenter for Display Events.
 * Responsible for presenting the display of the use case.
 * Allows the user to revert to the home screen.
 */

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
