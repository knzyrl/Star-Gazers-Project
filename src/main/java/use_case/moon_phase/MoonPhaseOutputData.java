package use_case.moon_phase;

public class MoonPhaseOutputData {
    private final String latitude;
    private final String longitude;
    private final String date;
    private final String imageURL;
    private final boolean useCaseFailed;

    public MoonPhaseOutputData(String latitude, String longitude, String date, String imageURL, boolean useCaseFailed) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.imageURL = imageURL;
        this.useCaseFailed = useCaseFailed;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getDate() {
        return date;
    }

    public String getImageURL() {
        return imageURL;
    }
}
