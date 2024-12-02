package data_access;

/**
 * Interface for fetching Near-Earth Object (NEO) data.
 * Defines the contract for accessing NEO data from any source.
 */
public interface NasaNeoDataAccessObject {

    /**
     * Fetches Near-Earth Objects data for a specified date range.
     *
     * @param startDate The start date for the data range (YYYY-MM-DD).
     * @param endDate The end date for the data range (YYYY-MM-DD).
     * @return A raw JSON response containing NEO data.
     */
    String fetchNearEarthObjects(String startDate, String endDate);
}
