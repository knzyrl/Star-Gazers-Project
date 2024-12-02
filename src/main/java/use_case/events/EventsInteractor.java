package use_case.events;

import data_access.EventsDataAccessObject;
import entity.EventsList;
import kong.unirest.core.json.JSONObject;

/**
 * Interactor for the Events use case.
 */
public class EventsInteractor implements EventsInputBoundary {
    private final EventsDataAccessObject eventsdao;
    private final EventsOutputBoundary eventsPresenter;

    public EventsInteractor(EventsDataAccessObject eventsdao, EventsOutputBoundary eventsPresenter) {
        this.eventsdao = eventsdao;
        this.eventsPresenter = eventsPresenter;
    }

    @Override
    public void execute(EventsInputData eventsInputData) {
        final String longitude = eventsInputData.getLongitude();
        final String latitude = eventsInputData.getLatitude();
        final String dateStart = eventsInputData.getDateStart();
        final String dateEnd = eventsInputData.getDateEnd();
        final String body = eventsInputData.getBody();

        final String query = String.format(
                "https://api.astronomyapi.com/api/v2/bodies/events/moon?longitude=%s&latitude=%s&elevation=1"
                        + "&from_date=%s&to_date=%s&time=%s",
                longitude, latitude, dateStart, dateEnd, "00%3A00%3A00"
        );
        final JSONObject response = eventsdao.executeQuery(query);

        final EventsList eventsList = new EventsList(longitude, latitude, dateStart, dateEnd, body, response);
        eventsPresenter.displayEvents(eventsList);
    }

    /**
     * Executes a default action for the Events use case, typically navigating back.
     */
    public void execute() {
        eventsPresenter.back();
    }
}
