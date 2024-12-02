package entity;

import java.util.regex.Pattern;

/**
 * Represents the moon phase data for a specific geographical location and date.
 * Includes functionality to validate the format and value of latitude, longitude, and date.
 */
public class MoonPhase {
    private String latitude;
    private String longitude;
    private String date;
    private String imageUrl;

    /**
     * Constructs a {@code MoonPhase} object with the specified longitude, latitude, date, and image URL.
     *
     * @param longitude The longitude of the observer's location.
     * @param latitude  The latitude of the observer's location.
     * @param date      The date of the observation in the format "YYYY-MM-DD".
     * @param imageUrl  The URL of the moon phase image.
     */
    public MoonPhase(String longitude, String latitude, String date, String imageUrl) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        this.imageUrl = imageUrl;
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

    public String getImgUrl() {
        return imageUrl;
    }

    /**
     * Validates the format of the latitude string.
     * The format should match a decimal number, optionally with a sign.
     *
     * @return {@code true} if the latitude format is valid, otherwise {@code false}.
     */
    public boolean validLatitudeFormat() {
        final String latitudePattern = "[-+]?[0-9]*\\.?[0-9]+";
        return latitude.matches(latitudePattern);
    }

    /**
     * Validates the format of the longitude string.
     * The format should match a decimal number, optionally with a sign.
     *
     * @return {@code true} if the longitude format is valid, otherwise {@code false}.
     */
    public boolean validLongitudeFormat() {
        final String longitudePattern = "[-+]?[0-9]*\\.?[0-9]+";
        return longitude.matches(longitudePattern);
    }

    /**
     * Validates the format of the date string.
     * The format should match "YYYY-MM-DD".
     *
     * @return {@code true} if the date format is valid, otherwise {@code false}.
     */
    public boolean validDateFormat() {
        final String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return Pattern.matches(datePattern, date);
    }

    /**
     * Validates the value of the latitude.
     * The latitude should be in the range [-90, 90].
     *
     * @return {@code true} if the latitude value is valid, otherwise {@code false}.
     */
    public boolean validLatitudeValue() {
        final float floatLatitude = Float.parseFloat(latitude);
        return floatLatitude >= -90.00 && floatLatitude <= 90.00;
    }

    /**
     * Validates the value of the longitude.
     * The longitude should be in the range [-180, 180].
     *
     * @return {@code true} if the longitude value is valid, otherwise {@code false}.
     */
    public boolean validLongitudeValue() {
        final float floatLongitude = Float.parseFloat(longitude);
        return floatLongitude >= -180.0 && floatLongitude <= 180.0;
    }
}
