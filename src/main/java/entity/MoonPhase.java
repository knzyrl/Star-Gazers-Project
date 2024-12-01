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

    public boolean validLatitudeFormat() {
        final String latitudePattern = "[-+]?[0-9]*\\.?[0-9]+";
        return latitude.matches(latitudePattern);
    }

    public boolean validLongitudeFormat() {
        final String longitudePattern = "[-+]?[0-9]*\\.?[0-9]+";
        return longitude.matches(longitudePattern);
    }

    public boolean validDateFormat() {
        final String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return Pattern.matches(datePattern, date);
    }

    public boolean validLatitudeValue() {
        float floatLatitude = parseFloat(latitude);
        return floatLatitude >= -90.00 && floatLatitude <= 90.00;
    }

    public boolean validLongitudeValue() {
        float floatLongitude = parseFloat(longitude);
        return floatLongitude >= -180.0 && floatLongitude <= 180.0;
    }
}
