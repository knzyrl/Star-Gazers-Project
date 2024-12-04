package use_case.star_chart;

/**
 * Class for input data for the Star Char use case.
 */
public class StarChartInputData {
    private final String longitude;
    private final String latitude;
    private final String date;

    public StarChartInputData(String longitude, String latitude, String date) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public String getDate() {
        return this.date;
    }
}
