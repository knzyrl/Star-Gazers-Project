package interface_adapter.near_earth_objects;

import use_case.near_earth_objects.NEOInputBoundary;
import use_case.near_earth_objects.NEOInputData;

public class NEOController {
    private final NEOInputBoundary interactor;

    public NEOController(NEOInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void fetchNEOData(String startDate, String endDate) {
        if (startDate == null || endDate == null || startDate.isEmpty() || endDate.isEmpty()) {
            throw new IllegalArgumentException("Start and end dates must not be null or empty.");
        }

        // Create input data and call the interactor
        NEOInputData inputData = new NEOInputData(startDate, endDate);
        interactor.fetchNEOData(inputData);
    }

}
