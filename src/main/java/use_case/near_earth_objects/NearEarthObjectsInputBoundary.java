package use_case.near_earth_objects;

public interface NearEarthObjectsInputBoundary {
    /**
     * Fetches data for Near-Earth Objects (NEOs) based on the provided input data.
     *
     * @param inputData The {@link NearEarthObjectsInputData} containing the parameters
     *                  for fetching NEO data, such as start and end dates.
     */
    void fetchNearEarthObjectsData(NearEarthObjectsInputData inputData);
}
