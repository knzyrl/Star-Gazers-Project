package use_case.apod_date;

/**
 * Data structure for transferring processed Astronomy Picture of the Day (APOD) information.
 * Contains details such as title, description, media type, and URLs for the APOD.
 */
public class AstronomyPictureOutputData {
    private final String title;
    private final String description;
    private final String mediaType;
    private final String url;
    private final String thumbnailUrl;

    public AstronomyPictureOutputData(String title, String description, String mediaType, String url,
                                      String thumbnailUrl) {
        this.title = title;
        this.description = description;
        this.mediaType = mediaType;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
