package interface_adapter.events;

import entity.EventsList;
import use_case.events.EventsOutputBoundary;
import view.DisplayEventsView;
import view.ViewManager;

/**
 * Presenter for managing the display of astronomical events.
 */
public class EventsPresenter implements EventsOutputBoundary {
    private ViewManager viewManager;
    private DisplayEventsView displayEventsView;

    public EventsPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setDisplayEventsView(DisplayEventsView displayEventsView) {
        this.displayEventsView = displayEventsView;
    }

    /**
     * Displays the list of events by updating the view with the provided events list
     * and refreshing the view to reflect the changes.
     *
     * @param eventsList The {@link EventsList} containing the events data to display.
     */
    @Override
    public void displayEvents(EventsList eventsList) {
        displayEventsView.setEventsList(eventsList);
        displayEventsView.refresh();
        viewManager.show(displayEventsView.getViewName());
    }

    /**
     * Navigates back to the home view.
     */
    public void back() {
        viewManager.show("home");
    }
}
