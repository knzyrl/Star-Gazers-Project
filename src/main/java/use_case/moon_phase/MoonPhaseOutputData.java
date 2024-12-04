package use_case.moon_phase;

/**
 * Class for output data for the Moon Phase use case.
 */
public class MoonPhaseOutputData {
    private final String latitude;
    private final String longitude;
    private final String date;
    private final String imageUrl;
    private final boolean useCaseFailed;

    public MoonPhaseOutputData(String latitude, String longitude, String date, String imageUrl, boolean useCaseFailed) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }
}
