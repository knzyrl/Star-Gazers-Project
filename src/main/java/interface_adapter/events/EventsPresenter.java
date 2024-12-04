package interface_adapter.events;

import interface_adapter.ViewManager;
import use_case.events.EventsOutputBoundary;
import use_case.events.EventsOutputData;

/**
 * Class for Presenter for the Events class.
 * Implements the EventsOutputBoundaryInterface.
 * Gets output data from EventsInteractor.
 * Updates the view displayed to the user by communicating with the ViewManager.
 */
public class EventsPresenter implements EventsOutputBoundary {
    private ViewManager viewManager;

    public EventsPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Override
    public void displayEvents(EventsOutputData eventsOutputData) {
        viewManager.displayEvents(eventsOutputData);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.out.println("Events fail view triggered with error message: " + errorMessage);
        viewManager.showFailView(errorMessage);
    }

    @Override
    public void back() {
        viewManager.show("home");
    }
}
