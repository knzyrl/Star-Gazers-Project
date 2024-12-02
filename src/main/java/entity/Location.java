package entity;

/**
 * Represents a geographical location with an address, longitude, and latitude.
 */
public class Location {
    private String address;
    private String longtitude;
    private String latitude;

    /**
     * Constructs a {@code Location} object with the specified address, longitude, and latitude.
     *
     * @param address   The address of the location.
     * @param longtitude The longitude of the location.
     * @param latitude  The latitude of the location.
     */
    public Location(String address, String longtitude, String latitude) {
        this.address = address;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
