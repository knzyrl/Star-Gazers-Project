package entity;

import java.util.regex.Pattern;

import static java.lang.Float.parseFloat;

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

    public boolean validLatitude() {
        final float floatLatitude = parseFloat(latitude);
        return floatLatitude >= -90 && floatLatitude <= 90;
    }

    public boolean validLongitude() {
        final float floatLongitude = parseFloat(longitude);
        return floatLongitude >= -180 && floatLongitude <= 180;
    }

    public boolean validDate() {
        final String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return Pattern.matches(datePattern, date);
    }
}
