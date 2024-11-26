package interface_adapter.display_events;

public class DisplayEventsController {
    private DisplayEventsPresenter displayEventsPresenter;

    public DisplayEventsController(DisplayEventsPresenter displayEventsPresenter) {
        this.displayEventsPresenter = displayEventsPresenter;
    }

    public void execute() {
        displayEventsPresenter.execute();
    }
}
