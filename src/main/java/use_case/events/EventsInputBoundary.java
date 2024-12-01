package use_case.events;

public interface EventsInputBoundary {
    void execute(EventsInputData eventsInputData);

    void execute();
}
