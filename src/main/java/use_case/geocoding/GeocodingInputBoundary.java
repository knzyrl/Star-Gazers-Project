package use_case.geocoding;

/**
 * Input boundary for geocoder use case.
 */
public interface GeocodingInputBoundary {

    /**
     * Execute the geocoding use case.
     * @param geocodingInputData input data for this use case.
     */
    void execute(GeocodingInputData geocodingInputData);
}
