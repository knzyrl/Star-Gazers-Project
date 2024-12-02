package use_case.events;

import data_access.AstronomyApiDataAccessObject;
import entity.EventsList;
import kong.unirest.core.json.JSONObject;

public class EventsInteractor implements EventsInputBoundary {
    private final AstronomyApiDataAccessObject eventsDAO;
    private final EventsOutputBoundary eventsPresenter;

    public EventsInteractor(AstronomyApiDataAccessObject eventsDAO, EventsOutputBoundary eventsPresenter) {
        this.eventsDAO = eventsDAO;
        this.eventsPresenter = eventsPresenter;
    }

    @Override
    public void execute(EventsInputData eventsInputData) {
        EventsList eventsList = new EventsList(eventsInputData.getLongitude(), eventsInputData.getLatitude(), eventsInputData.getDateStart(), eventsInputData.getDateEnd(), eventsInputData.getBody());

        if (!eventsList.isValidLongitude()) {
            eventsPresenter.prepareFailView("Longitude value invalid. Please ensure the input is a " +
                    "decimal between -180.00 and 180.00.");
            return;
        } else if (!eventsList.isValidLatitude()) {
            eventsPresenter.prepareFailView("Latitude value invalid. Please ensure the input is a " +
                    "decimal between -90.00 and 90.00.");
            return;
        } else if (!eventsList.isValidDates()) {
            eventsPresenter.prepareFailView("Dates invalid. Please ensure the inputs are valid dates " +
                    "in YYYY-MM-DD format.");
            return;
        }

        final String query = String.format("https://api.astronomyapi.com/api/v2/bodies/events/%s?longitude=%s" +
                        "&latitude=%s&elevation=1&from_date=%s&to_date=%s&time=%s", eventsList.getBody(),
                eventsList.getLongitude(), eventsList.getLatitude(), eventsList.getDateStart(),
                eventsList.getDateEnd(), "00%3A00%3A00");
        final JSONObject response = eventsDAO.executeQuery(query);

        final EventsOutputData eventsOutputData = new EventsOutputData(eventsList.getLongitude(),
                eventsList.getLatitude(), eventsList.getDateStart(), eventsList.getDateEnd(), eventsList.getBody(),
                response);
        eventsPresenter.displayEvents(eventsOutputData);
    }

    @Override
    public void execute() {
        eventsPresenter.back();
    }
}
