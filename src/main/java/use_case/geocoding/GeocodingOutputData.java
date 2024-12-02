package use_case.geocoding;

/**
 * Output data for geocoder use case.
 */
public class GeocodingOutputData {
    private final String address;
    private final String latitude;
    private final String longitude;
    private final boolean useCaseFailed;

    public GeocodingOutputData(String address, String longitude, String latitude, boolean useCaseFailed) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.useCaseFailed = useCaseFailed;
    }

    public String getAddress() {
        return address;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
