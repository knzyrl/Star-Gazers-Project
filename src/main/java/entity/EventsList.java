package entity;

import helper.NumberFormatChecker;
import kong.unirest.core.json.JSONArray;
import kong.unirest.core.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventsList {
    private String longitude;
    private String latitude;
    private String dateStart;
    private String dateEnd;
    private String body;
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

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

    public boolean isValidLongitude() {
        if (!NumberFormatChecker.checkDouble(this.longitude)) {
            return false;
        } else {
            return (Double.parseDouble(this.longitude) >= -180.00) && Double.parseDouble(this.longitude) <= 180.00;
        }
    }

    public boolean isValidLatitude() {
        if (!NumberFormatChecker.checkDouble(this.latitude)) {
            return false;
        } else {
            return (Double.parseDouble(this.latitude) >= -180.00) && Double.parseDouble(this.latitude) <= 180.00;
        }
    }

    public boolean isValidDates() {
        try {
            fmt.parse(this.dateStart);
            fmt.parse(this.dateEnd);
        } catch (ParseException parseException) {
            return false;
        }
        return true;
    }
}
