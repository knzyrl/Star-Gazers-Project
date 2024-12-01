package interface_adapter.events;

import entity.EventsList;
import use_case.events.EventsOutputBoundary;
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
    public void displayEvents(EventsList eventsList) {
        displayEventsView.setEventsList(eventsList);
        displayEventsView.refresh();
        viewManager.show(displayEventsView.getViewName());
    }

    public void back() {
        viewManager.show("home");
    }
}
