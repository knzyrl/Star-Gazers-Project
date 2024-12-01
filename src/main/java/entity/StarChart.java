package entity;

import helper.AstronomyCalculations;
import helper.NumberFormatChecker;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StarChart {

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

    public boolean isValidDate() {
        try {
            fmt.parse(this.date);
        } catch (ParseException parseException) {
            return false;
        }
        return true;
    }

    public String calcRA() {
        return Double.toString(AstronomyCalculations.calcRA(this.longitude, this.date));
    }

    public String calcDEC() {
        return Double.toString(Math.round(Double.parseDouble(this.latitude)));
    }
}
