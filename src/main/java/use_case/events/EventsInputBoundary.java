package use_case.events;

/**
 * Input boundary for the Events use case.
 */
public interface EventsInputBoundary {

    /**
     * Executes the Events use case with the provided input data.
     *
     * @param eventsInputData The {@link EventsInputData} containing the details
     *                        required to process the events query.
     */
    void execute(EventsInputData eventsInputData);
}
