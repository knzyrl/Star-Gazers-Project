package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import helper.NumberFormatChecker;

/**
 * Represents a list of astronomical events for a specified celestial body and date range.
 * Parses a JSON response to extract event details and encapsulates them in an {@link EventsList}.
 */
public class EventsList {
    private static final double MIN_LONGITUDE_LATITUDE = -180.00;
    private static final double MAX_LONGITUDE_LATITUDE = 180.00;

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
        return NumberFormatChecker.checkDouble(this.longitude) && Double.parseDouble(this.longitude)
                >= MIN_LONGITUDE_LATITUDE && Double.parseDouble(this.longitude) <= MAX_LONGITUDE_LATITUDE;
    }

    /**
     * Validates the latitude format and range.
     *
     * @return {@code true} if latitude is valid, otherwise {@code false}.
     */
    public boolean isValidLatitude() {
        return NumberFormatChecker.checkDouble(this.latitude) && Double.parseDouble(this.latitude)
                >= MIN_LONGITUDE_LATITUDE && Double.parseDouble(this.latitude) <= MAX_LONGITUDE_LATITUDE;
    }

    /**
     * Validates the format of start and end dates.
     *
     * @return {@code true} if both dates are valid, otherwise {@code false}.
     */
    public boolean isValidDates() {
        try {
            fmt.parse(this.dateStart);
            fmt.parse(this.dateEnd);
        }
        catch (ParseException parseException) {
            return false;
        }
        return true;
    }
}
