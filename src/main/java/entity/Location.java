package entity;

public class Location {
    private String address;
    private String longtitude;
    private String latitude;

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
