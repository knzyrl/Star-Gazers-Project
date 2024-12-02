package interface_adapter.display_events;

import view.ViewManager;

/**
 * Presenter for managing the display of events.
 */
public class DisplayEventsPresenter {
    private ViewManager viewManager;

    public DisplayEventsPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Executes the display events use case.
     * This method switches the current view to the "home" view.
     */
    public void execute() {
        viewManager.show("home");
    }
}

