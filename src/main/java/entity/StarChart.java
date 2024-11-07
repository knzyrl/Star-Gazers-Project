package entity;

public class StarChart {
    private String longitude;
    private String latitude;
    private String date;
    private String imgURL;

    public StarChart(String longitude, String latitude, String date, String imgURL) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        this.imgURL = imgURL;
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
        return imgURL;
    }
}
