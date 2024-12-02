package use_case.events;

import entity.EventsList;

/**
 * Output boundary for the Events use case.
 */
public interface EventsOutputBoundary {

    /**
     * Displays the processed events data.
     *
     * @param eventsList The {@link EventsList} containing the events data to display.
     */
    void displayEvents(EventsList eventsList);

    /**
     * Navigates back to the previous view or home view.
     */
    void back();
}
