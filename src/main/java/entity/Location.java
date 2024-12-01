package entity;

public class Location {
    private String address;
    private String longitude;
    private String latitude;

    public Location(String address, String longitude, String latitude) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }
    public String getLongitude() {
        return longitude;
    }
    public String getLatitude() {
        return latitude;
    }
}
