package entity;

public class MoonPhase {
    private String latitude;
    private String longitude;
    private String date;
    private String imageURL;

    public MoonPhase(String longitude, String latitude, String date, String imageURL) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        this.imageURL = imageURL;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getDate() {
        return date;
    }

    public String getImgURL() {
        return imageURL;
    }
}
