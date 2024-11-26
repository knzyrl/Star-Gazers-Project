package use_case.events;

import data_access.EventsDataAccessObject;
import entity.EventsList;
import interface_adapter.events.EventsPresenter;
import kong.unirest.core.json.JSONArray;
import kong.unirest.core.json.JSONObject;

public class EventsInteractor {
    private final EventsDataAccessObject eventsDAO;
    private final EventsPresenter eventsPresenter;

    public EventsInteractor(EventsDataAccessObject eventsDAO,EventsPresenter eventsPresenter) {
        this.eventsDAO = eventsDAO;
        this.eventsPresenter = eventsPresenter;
    }

    public void execute(EventsInputData eventsInputData) {
        final String longitude = eventsInputData.getLongitude();
        final String latitude = eventsInputData.getLatitude();
        final String dateStart = eventsInputData.getDateStart();
        final String dateEnd = eventsInputData.getDateEnd();
        final String body = eventsInputData.getBody();

        final String query = String.format("https://api.astronomyapi.com/api/v2/bodies/events/moon?longitude=%s&latitude=%s&elevation=1&from_date=%s&to_date=%s&time=%s", longitude, latitude, dateStart, dateEnd, "00%3A00%3A00");
        final JSONObject response = eventsDAO.executeQuery(query);

        final EventsList eventsList = new EventsList(longitude, latitude, dateStart, dateEnd, body, response);
        eventsPresenter.displayEvents(eventsList);
    }

    public void execute() {
        eventsPresenter.back();
    }
}
