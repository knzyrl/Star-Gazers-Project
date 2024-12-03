package interface_adapter.events;

import use_case.events.EventsInputData;
import use_case.events.EventsInteractor;

/**
 * Class for Controller for Events use case.
 * Gets input data from the user through the View, packages it and passes it to EventsInteractor.
 */

public class EventsController {
    private final EventsInteractor eventsInteractor;

    public EventsController(EventsInteractor eventsInteractor) {
        this.eventsInteractor = eventsInteractor;
    }

    /**
     * Calls the interactor to execute the events use case.
     * @param longitude The longitude input by the user
     * @param latitude The latitude input by the user
     * @param dateStart The start date input by the user
     * @param dateEnd The end date input by the user
     * @param body The astronomical body selected by the user
     */
    public void execute(String longitude, String latitude, String dateStart, String dateEnd, String body) {
        final EventsInputData eventsInputData = new EventsInputData(longitude, latitude, dateStart, dateEnd, body);
        eventsInteractor.execute(eventsInputData);
    }

    /**
     * Calls the interactor to execute the return to home.
     */
    public void execute() {
        eventsInteractor.execute();
    }
}
