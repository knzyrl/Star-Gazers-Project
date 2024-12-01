package use_case.events;

public interface EventsOutputBoundary {
    void displayEvents(EventsOutputData eventsOutputData);

    void prepareFailView(String errorMessage);

    void back();
}
