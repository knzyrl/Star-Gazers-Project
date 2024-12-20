package use_case.near_earth_objects;

import java.util.List;

import entity.NearEarthObjectEntity;

/**
 * Interface (Abstraction) between the Business Rules/Entities layers and Interface Adapter layer.
 * Implemented by NearEarthObjectsPresenter.
 */
public interface NearEarthObjectsOutputBoundary {

    /**
     * Presents a list of Near-Earth Object (NEO) data.
     *
     * @param neoData The list of {@link NearEarthObjectEntity} objects containing processed NEO data.
     */
    void presentNearEarthObjectsData(List<NearEarthObjectsOutputData> neoData);

    /**
     * Handles the case where no data is found for the requested dates.
     */
    void noDataFound();
}
