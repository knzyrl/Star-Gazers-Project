package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import helper.AstronomyCalculations;
import helper.NumberFormatChecker;

/**
 * Represents a star chart image for a specific geographical location and date.
 * Encapsulates the location (latitude and longitude), the observation date, and the image URL.
 */
public class StarChart {
    private static final double MAX_LONGITUDE = 180.0;
    private static final double MIN_LONGITUDE = -180.0;
    private static final double MAX_LATITUDE = 90.0;
    private static final double MIN_LATITUDE = -90.0;

    private String longitude;
    private String latitude;
    private String date;
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public StarChart(String longitude, String latitude, String date) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
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

    /**
     * Validates whether the longitude is in a valid range [-180.00, 180.00] and correctly formatted.
     *
     * @return {@code true} if the longitude is valid, {@code false} otherwise.
     */
    public boolean isValidLongitude() {
        boolean result = true;
        if (!NumberFormatChecker.checkDouble(this.longitude)) {
            result = false;
        }
        else if (Double.parseDouble(this.longitude) < MIN_LONGITUDE || Double.parseDouble(this.longitude)
                > MAX_LONGITUDE) {
            result = false;
        }
        return result;
    }

    /**
     * Validates whether the latitude is in a valid range [-90.00, 90.00] and correctly formatted.
     *
     * @return {@code true} if the latitude is valid, {@code false} otherwise.
     */
    public boolean isValidLatitude() {
        boolean result = true;
        if (!NumberFormatChecker.checkDouble(this.latitude)) {
            result = false;
        }
        else if (Double.parseDouble(this.latitude) < MIN_LATITUDE || Double.parseDouble(this.latitude)
                > MAX_LATITUDE) {
            result = false;
        }
        return result;
    }

    /**
     * Validates whether the observation date is in the correct format "YYYY-MM-DD".
     *
     * @return {@code true} if the date format is valid, {@code false} otherwise.
     */
    public boolean isValidDate() {
        boolean result = true;
        try {
            fmt.parse(this.date);
        }
        catch (ParseException parseException) {
            result = false;
        }
        return result;
    }

    /**
     * Calculates the right ascension (RA) for the specified longitude and date.
     *
     * @return The right ascension as a {@code String}.
     */
    public String calcRa() {
        return Double.toString(AstronomyCalculations.calcRa(this.longitude, this.date));
    }

    /**
     * Calculates the declination (DEC) for the specified latitude.
     *
     * @return The declination as a {@code String}.
     */
    public String calcdec() {
        return Double.toString(Math.round(Double.parseDouble(this.latitude)));
    }
}
