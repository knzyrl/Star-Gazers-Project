package entity;

/**
 * Represents a star chart image for a specific geographical location and date.
 * Encapsulates the location (latitude and longitude), the observation date, and the image URL.
 */
public class StarChart {

    private String longitude;
    private String latitude;
    private String date;
    private String imgUrl;

    /**
     * Constructs a {@code StarChart} object with the specified longitude, latitude, date, and image URL.
     *
     * @param longitude The longitude of the observer's location.
     * @param latitude  The latitude of the observer's location.
     * @param date      The date of the observation in the format "YYYY-MM-DD".
     * @param imgUrl    The URL of the star chart image.
     */
    public StarChart(String longitude, String latitude, String date, String imgUrl) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        this.imgUrl = imgUrl;
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
        return imgUrl;
    }

}
