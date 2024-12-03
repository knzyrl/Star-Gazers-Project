package use_case.events;

/**
 * Interface (Abstraction) between the Business Rules/Entities layers and Interface Adapter layer.
 * Implemented by EventsPresenter.
 */
public interface EventsOutputBoundary {
    /**
     * Responsible for displaying the output of the use case to the user.
     * @param eventsOutputData received from the Interactor.
     */
    void displayEvents(EventsOutputData eventsOutputData);

    /**
     * Responsible for displaying the error message to the user when the user input is incorrect.
     * @param errorMessage is the error message to be displayed.
     */
    void prepareFailView(String errorMessage);

    /**
     * Responsible for reverting to HomeView.
     */
    void back();
}
