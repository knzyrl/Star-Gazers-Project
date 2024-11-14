package use_case.moon_phase;

public class MoonPhaseInputData {
    private final String longitude;
    private final String latitude;
    private final String date;

    public MoonPhaseInputData(String longitude, String latitude, String date) {
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
