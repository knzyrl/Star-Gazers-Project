package use_case.near_earth_objects;

/**
 * Input boundary for the Near-Earth Objects (NEO) use case.
 */
public interface NearEarthObjectsInputBoundary {
    // Accepts NearEarthObjectsInputData
    /**
     * Fetches data for Near-Earth Objects (NEOs) based on the provided input data.
     *
     * @param inputData The {@link NearEarthObjectsInputData} containing the parameters
     *                  for fetching NEO data, such as start and end dates.
     */
    void fetchNearEarthobjectData(NearEarthObjectsInputData inputData);
}
