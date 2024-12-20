package interface_adapter.near_earth_objects;

import use_case.near_earth_objects.NearEarthObjectsInputBoundary;
import use_case.near_earth_objects.NearEarthObjectsInputData;

/**
 * Class for the Controller for the Near Earth Objects use case.
 * Receives user input from the view, packages it, and sends it to NearEarthObjectsInteractor.
 */
public class NearEarthObjectsController {
    private final NearEarthObjectsInputBoundary interactor;

    public NearEarthObjectsController(NearEarthObjectsInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Fetches Near-Earth Objects data for the specified date range.
     * Validates the input dates and delegates the request to the interactor.
     *
     * @param startDate The start date for the data range in "YYYY-MM-DD" format.
     * @param endDate   The end date for the data range in "YYYY-MM-DD" format.
     * @throws IllegalArgumentException If either start date or end date is null or empty.
     */
    public void fetchNearEarthObjectsData(String startDate, String endDate) {
        if (startDate == null || endDate == null || startDate.isEmpty() || endDate.isEmpty()) {
            throw new IllegalArgumentException("Start and end dates must not be null or empty.");
        }

        final NearEarthObjectsInputData inputData = new NearEarthObjectsInputData(startDate, endDate);
        interactor.fetchNearEarthObjectsData(inputData);
    }

}
