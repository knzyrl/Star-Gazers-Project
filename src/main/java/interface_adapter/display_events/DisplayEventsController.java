package interface_adapter.display_events;

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
