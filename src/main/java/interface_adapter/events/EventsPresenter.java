package interface_adapter.events;

import entity.EventsList;
import use_case.events.EventsOutputBoundary;
import use_case.events.EventsOutputData;
import view.DisplayEventsView;
import view.ViewManager;

public class EventsPresenter implements EventsOutputBoundary {
    private ViewManager viewManager;
    private DisplayEventsView displayEventsView;

    public EventsPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setDisplayEventsView(DisplayEventsView displayEventsView) {
        this.displayEventsView = displayEventsView;
    }

    @Override
    public void displayEvents(EventsOutputData eventsOutputData) {
        displayEventsView.refresh(eventsOutputData);
        viewManager.show(displayEventsView.getViewName());
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.out.println("Events fail view triggered with error message: " + errorMessage);
    }

    @Override
    public void back() {
        viewManager.show("home");
    }
}
