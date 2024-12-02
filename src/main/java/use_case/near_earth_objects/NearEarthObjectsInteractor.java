package use_case.near_earth_objects;

import java.util.List;

import data_access.NasaNeoDataAccessObject;
import entity.NearEarthObjectEntity;
import helper.NearEarthObjectsJsonParser;

public class NearEarthObjectsInteractor implements NearEarthObjectsInputBoundary {
    private final NasaNeoDataAccessObject api;
    private final NearEarthObjectsOutputBoundary outputBoundary;

    public NearEarthObjectsInteractor(NasaNeoDataAccessObject api, NearEarthObjectsOutputBoundary outputBoundary) {
        this.api = api;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void fetchNearEarthObjectsData(NearEarthObjectsInputData inputData) {
        try {
            final String rawJson = api.fetchNearEarthObjects(inputData.startDate(), inputData.endDate());
            final List<NearEarthObjectEntity> neoEntities = NearEarthObjectsJsonParser.parse(rawJson);
            outputBoundary.presentNearEarthObjectsData(neoEntities);
        }
        catch (IllegalArgumentException exception) {
            System.err.println("Error: Invalid data or response - " + exception.getMessage());
        }
    }
}
