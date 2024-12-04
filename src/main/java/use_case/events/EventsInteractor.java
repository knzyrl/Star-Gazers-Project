package use_case.events;

import data_access.AstronomyApiDataAccessObject;
import entity.EventsList;
import kong.unirest.core.json.JSONObject;

/**
 * Class for the Interactor for the Events use case.
 * Implements the EventsInputBoundary interface.
 * Receives input data from the Controller and process it with the help of the corresponding API and Entity.
 * Sends the output data to the OutputBoundary for display.
 */
public class EventsInteractor implements EventsInputBoundary {
    private final AstronomyApiDataAccessObject eventsDataAccessObject;
    private final EventsOutputBoundary eventsPresenter;

    public EventsInteractor(AstronomyApiDataAccessObject eventsDataAccessObject, EventsOutputBoundary eventsPresenter) {
        this.eventsDataAccessObject = eventsDataAccessObject;
        this.eventsPresenter = eventsPresenter;
    }

    @Override
    public void execute(EventsInputData eventsInputData) {
        final EventsList eventsList = new EventsList(eventsInputData.getLongitude(), eventsInputData.getLatitude(),
                eventsInputData.getDateStart(), eventsInputData.getDateEnd(), eventsInputData.getBody());

        if (!eventsList.isValidLongitude()) {
            eventsPresenter.prepareFailView("Longitude value invalid. Please ensure the input is a "
                    + "decimal between -180.00 and 180.00.");
            return;
        }
        else if (!eventsList.isValidLatitude()) {
            eventsPresenter.prepareFailView("Latitude value invalid. Please ensure the input is a "
                    + "decimal between -90.00 and 90.00.");
            return;
        }
        else if (!eventsList.isValidDates()) {
            eventsPresenter.prepareFailView("Dates invalid. Please ensure the inputs are valid dates "
                    + "in YYYY-MM-DD format.");
            return;
        }

        final String query = String.format("https://api.astronomyapi.com/api/v2/bodies/events/%s?longitude=%s"
                        + "&latitude=%s&elevation=1&from_date=%s&to_date=%s&time=%s", eventsList.getBody(),
                eventsList.getLongitude(), eventsList.getLatitude(), eventsList.getDateStart(),
                eventsList.getDateEnd(), "00%3A00%3A00");
        final JSONObject response = eventsDataAccessObject.executeQuery(query);

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
