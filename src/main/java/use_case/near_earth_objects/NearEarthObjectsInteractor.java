package use_case.near_earth_objects;

import java.util.List;

import data_access.NasaNeoDataAccessObject;
import entity.NearEarthObjectEntity;
import helper.NearEarthObjectJsonParser;

/**
 * Interactor class for fetching and processing Near-Earth Objects (NEO) data.
 */
public class NearEarthObjectsInteractor implements NearEarthObjectsInputBoundary {
    private final NasaNeoDataAccessObject dao;
    private final NearEarthObjectsOutputBoundary outputBoundary;

    public NearEarthObjectsInteractor(NasaNeoDataAccessObject dao, NearEarthObjectsOutputBoundary outputBoundary) {
        this.dao = dao;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Fetches and processes Near-Earth Objects (NEO) data for the specified date range.
     *
     * @param inputData The input data containing the start and end dates for the query.
     * @throws IllegalArgumentException If the JSON response from the API is invalid or data parsing fails.
     */
    @Override
    public void fetchNearEarthobjectData(NearEarthObjectsInputData inputData) {
        try {
            final String rawJson = dao.fetchNearEarthObjects(inputData.startDate(), inputData.endDate());
            final List<NearEarthObjectEntity> neoEntities = NearEarthObjectJsonParser.parse(rawJson);
            outputBoundary.presentNearEarthObjectData(neoEntities);
        }
        catch (IllegalArgumentException exception) {
            System.err.println("Error: Invalid data or response - " + exception.getMessage());
        }
    }
}
