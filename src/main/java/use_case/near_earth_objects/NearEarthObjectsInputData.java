package use_case.near_earth_objects;

/**
 * Record for transferring input data for the Near-Earth Objects (NEO) use case.
 * Contains the start and end dates for the NEO data query.
 *
 * @param startDate The start date for the NEO query, in "YYYY-MM-DD" format.
 * @param endDate   The end date for the NEO query, in "YYYY-MM-DD" format.
 */
public record NearEarthObjectsInputData(String startDate, String endDate) {
}
