package use_case.near_earth_objects;

import java.util.List;

import entity.NearEarthObjectEntity;

/**
 * Output boundary interface for handling the presentation of Near-Earth Objects (NEO) data.
 */
public interface NearEarthObjectsOutputBoundary {

    /**
     * Presents a list of Near-Earth Object (NEO) data.
     *
     * @param neoData The list of {@link NearEarthObjectEntity} objects containing processed NEO data.
     */
    void presentNearEarthObjectData(List<NearEarthObjectEntity> neoData);
}
