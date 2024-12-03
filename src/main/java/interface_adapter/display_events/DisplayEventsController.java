package interface_adapter.display_events;

/**
 * Class for controller for Display Events.
 * Calls the presenter to allow the user to revert to the home view.
 */

public class DisplayEventsController {
    private DisplayEventsPresenter displayEventsPresenter;

    public DisplayEventsController(DisplayEventsPresenter displayEventsPresenter) {
        this.displayEventsPresenter = displayEventsPresenter;
    }

    /**
     * Calls the presenter to execute the return to home.
     */
    public void execute() {
        displayEventsPresenter.execute();
    }
}
