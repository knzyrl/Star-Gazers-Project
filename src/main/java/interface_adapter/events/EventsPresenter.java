package interface_adapter.events;

import use_case.events.EventsOutputBoundary;
import use_case.events.EventsOutputData;
import view.ViewManager;

public class EventsPresenter implements EventsOutputBoundary {
    private ViewManager viewManager;

    public EventsPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Override
    public void displayEvents(EventsOutputData eventsOutputData) {
        viewManager.displayEvents(eventsOutputData);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.out.println("Events fail view triggered with error message: " + errorMessage);
        viewManager.showFailView(errorMessage);
    }

    @Override
    public void back() {
        viewManager.show("home");
    }
}
