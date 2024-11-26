package interface_adapter.display_events;

import view.ViewManager;

public class DisplayEventsPresenter {
    private ViewManager viewManager;

    public DisplayEventsPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void execute() {
        viewManager.show("home");
    }
}
