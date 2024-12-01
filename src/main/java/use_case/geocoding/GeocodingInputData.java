package use_case.geocoding;

/**
 * Input data for geocoding use case.
 */
public class GeocodingInputData {
    private final String address;

    public GeocodingInputData(String address) {
        this.address = address;
    }

    String getAddress() {
        return address;
    }
}
