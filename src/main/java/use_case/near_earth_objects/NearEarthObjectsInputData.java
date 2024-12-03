package use_case.near_earth_objects;

/**
 * NearEarthObjectsInputData.
 * @param startDate of the period for when the user wishes to see information on Near Earth Objects.
 * @param endDate of the period for when the user wishes to see information on Near Earth Objects.
 */
public record NearEarthObjectsInputData(String startDate, String endDate) {
}
