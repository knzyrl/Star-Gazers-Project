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
        if (inputData.startDate() == null || inputData.startDate().isEmpty()
                || inputData.endDate() == null || inputData.endDate().isEmpty()) {
            throw new IllegalArgumentException("Start and end dates must not be null or empty.");
        }

        try {
            final String rawJson = api.fetchNearEarthObjects(inputData.startDate(), inputData.endDate());
            final List<NearEarthObjectEntity> neoEntities = NearEarthObjectsJsonParser.parse(rawJson);

            if (neoEntities.isEmpty()) {
                outputBoundary.noDataFound();
            }
            else {
                outputBoundary.presentNearEarthObjectsData(neoEntities);
            }
        }
        catch (IllegalArgumentException exception) {
            outputBoundary.noDataFound();
        }
    }
}
