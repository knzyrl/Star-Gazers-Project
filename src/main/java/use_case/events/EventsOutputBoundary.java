package use_case.events;

import entity.EventsList;

public interface EventsOutputBoundary {
    void displayEvents(EventsList eventsList);

    void back();
}
