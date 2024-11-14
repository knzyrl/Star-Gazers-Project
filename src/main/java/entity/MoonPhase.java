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
}
