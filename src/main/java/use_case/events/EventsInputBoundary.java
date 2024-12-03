package use_case.events;

/**
 * Interface (Abstraction) between the Interface Adapter and Business Rules/Entities layers.
 * This particular interface is implemented by EventsInteractor.
 */
public interface EventsInputBoundary {
    /**
     * Method to work with the input data as required by the use case.
     * @param eventsInputData received from EventsController.
     */
    void execute(EventsInputData eventsInputData);

    /**
     * Method to revert to HomeView.
     */
    void execute();
}
