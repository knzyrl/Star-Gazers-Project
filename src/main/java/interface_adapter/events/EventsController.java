package interface_adapter.events;

import use_case.events.EventsInputData;
import use_case.events.EventsInteractor;

public class EventsController {
    private final EventsInteractor eventsInteractor;

    public EventsController(EventsInteractor eventsInteractor) {
        this.eventsInteractor = eventsInteractor;
    }

    public void execute(String longitude, String latitude, String dateStart, String dateEnd, String body) {
        EventsInputData eventsInputData = new EventsInputData(longitude, latitude, dateStart, dateEnd, body);
        eventsInteractor.execute(eventsInputData);
    }

    public void execute() {
        eventsInteractor.execute();
    }
}
