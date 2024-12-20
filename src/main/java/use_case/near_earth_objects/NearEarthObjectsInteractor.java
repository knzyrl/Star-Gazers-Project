package use_case.near_earth_objects;

import java.util.List;

import data_access.NasaNeoDataAccessObject;
import helper.NearEarthObjectsJsonParser;

/**
 * Interactor for the Near Earth Objects use case.
 * Implements the NearEarthObjectsIntputBoundary.
 * Receives input data from the controller, manipulates it according to the use case requirements and sends it to the
 * OutputBoundary.
 */
public class NearEarthObjectsInteractor implements NearEarthObjectsInputBoundary {
    private final NasaNeoDataAccessObject api;
    private final NearEarthObjectsOutputBoundary outputBoundary;

    public NearEarthObjectsInteractor(NasaNeoDataAccessObject api, NearEarthObjectsOutputBoundary outputBoundary) {
        this.api = api;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void fetchNearEarthObjectsData(NearEarthObjectsInputData inputData) {
        if (inputData.startDate() == null || inputData.startDate().isEmpty()
                || inputData.endDate() == null || inputData.endDate().isEmpty()) {
            throw new IllegalArgumentException("Start and end dates must not be null or empty.");
        }

        try {
            final String rawJson = api.fetchNearEarthObjects(inputData.startDate(), inputData.endDate());
            final List<NearEarthObjectsOutputData> neoOutputData = NearEarthObjectsJsonParser.parse(rawJson);

            if (neoOutputData.isEmpty()) {
                outputBoundary.noDataFound();
            }
            else {
                outputBoundary.presentNearEarthObjectsData(neoOutputData);
            }
        }
        catch (IllegalArgumentException exception) {
            outputBoundary.noDataFound();
        }
    }
}
