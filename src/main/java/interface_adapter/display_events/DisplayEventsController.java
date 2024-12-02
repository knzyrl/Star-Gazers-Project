package interface_adapter.display_events;

/**
 * Controller for handling user actions related to displaying events.
 */
public class DisplayEventsController {
    private DisplayEventsPresenter displayEventsPresenter;

    /**
     * Constructs a {@code DisplayEventsController} with the specified presenter.
     *
     * @param displayEventsPresenter The presenter responsible for event display logic.
     */
    public DisplayEventsController(DisplayEventsPresenter displayEventsPresenter) {
        this.displayEventsPresenter = displayEventsPresenter;
    }

    /**
     * Executes the display events use case by delegating the request to the presenter.
     */
    public void execute() {
        displayEventsPresenter.execute();
    }
}
