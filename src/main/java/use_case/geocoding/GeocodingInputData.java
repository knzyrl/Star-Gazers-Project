package use_case.geocoding;

public class GeocodingInputData {
    private final String address;

    public GeocodingInputData(String address) {
        this.address = address;
    }

    String getAddress() {
        return address;
    }
}
