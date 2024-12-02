package interface_adapter.events;

import use_case.events.EventsInputData;
import use_case.events.EventsInteractor;

/**
 * Controller for managing user actions related to astronomical events.
 */
public class EventsController {
    private final EventsInteractor eventsInteractor;

    /**
     * Constructs an {@code EventsController} with the specified interactor.
     *
     * @param eventsInteractor The interactor responsible for handling event-related logic.
     */
    public EventsController(EventsInteractor eventsInteractor) {
        this.eventsInteractor = eventsInteractor;
    }

    /**
     * Executes the events use case with the specified input data.
     *
     * @param longitude The longitude of the location for the events query.
     * @param latitude  The latitude of the location for the events query.
     * @param dateStart The start date for the events query, in the format "YYYY-MM-DD".
     * @param dateEnd   The end date for the events query, in the format "YYYY-MM-DD".
     * @param body      The celestial body for the events query (e.g., "moon").
     */
    public void execute(String longitude, String latitude, String dateStart, String dateEnd, String body) {
        final EventsInputData eventsInputData = new EventsInputData(longitude, latitude, dateStart, dateEnd, body);
        eventsInteractor.execute(eventsInputData);
    }

    /**
     * Executes the default events use case with no additional input data.
     */
    public void execute() {
        eventsInteractor.execute();
    }
}
