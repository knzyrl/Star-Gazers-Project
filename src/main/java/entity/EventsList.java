package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import helper.NumberFormatChecker;

/**
 * Represents a list of astronomical events for a specified celestial body and date range.
 * Parses a JSON response to extract event details and encapsulates them in an {@link EventsList}.
 */
public class EventsList {
    private static final double MAX_LONGITUDE = 180.0;
    private static final double MIN_LONGITUDE = -180.0;
    private static final double MAX_LATITUDE = 90.0;
    private static final double MIN_LATITUDE = -90.0;

    private String longitude;
    private String latitude;
    private String dateStart;
    private String dateEnd;
    private String body;
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Constructor for creating an EventsList object.
     *
     * @param longitude Longitude of the event's location.
     * @param latitude Latitude of the event's location.
     * @param dateStart Start date of the event (YYYY-MM-DD).
     * @param dateEnd End date of the event (YYYY-MM-DD).
     * @param body Celestial body associated with the events.
     */
    public EventsList(String longitude, String latitude, String dateStart, String dateEnd, String body) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.body = body;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getBody() {
        return body;
    }

    /**
     * Validates the longitude format and range.
     *
     * @return {@code true} if longitude is valid, otherwise {@code false}.
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
     * Validates the latitude format and range.
     *
     * @return {@code true} if latitude is valid, otherwise {@code false}.
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
     * Validates the format of start and end dates.
     *
     * @return {@code true} if both dates are valid, otherwise {@code false}.
     */
    public boolean isValidDates() {
        boolean result = true;
        try {
            fmt.parse(this.dateStart);
            fmt.parse(this.dateEnd);
        }
        catch (ParseException parseException) {
            result = false;
        }
        return result;
    }
}
