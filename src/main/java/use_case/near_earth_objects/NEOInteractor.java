package use_case.near_earth_objects;

import data_access.NasaNeoDataAccessObject;
import entity.NearEarthObjectEntity;
import helper.NEOJsonParser;

import java.util.List;

public class NEOInteractor implements NEOInputBoundary {
    private final NasaNeoDataAccessObject dao;
    private final NEOOutputBoundary outputBoundary;

    public NEOInteractor(NasaNeoDataAccessObject dao, NEOOutputBoundary outputBoundary) {
        this.dao = dao;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void fetchNEOData(NEOInputData inputData) {
        try {
            String rawJson = dao.fetchNearEarthObjects(inputData.startDate(), inputData.endDate());
            List<NearEarthObjectEntity> neoEntities = NEOJsonParser.parse(rawJson);
            outputBoundary.presentNEOData(neoEntities);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
