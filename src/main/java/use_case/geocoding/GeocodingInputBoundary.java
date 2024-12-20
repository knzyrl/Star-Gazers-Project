package use_case.geocoding;

/**
 * Use case for geocoder.
 */
public interface GeocodingInputBoundary {

    /**
     * Execute the geocoding use case.
     * @param geocodingInputData input data for this use case.
     */
    void execute(GeocodingInputData geocodingInputData);
}
