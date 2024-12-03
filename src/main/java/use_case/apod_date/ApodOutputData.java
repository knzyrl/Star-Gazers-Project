package use_case.apod_date;

public class ApodOutputData {
    private final String title;
    private final String description;
    private final String mediaType;
    private final String url;
    private final String thumbnailUrl;

    public ApodOutputData(String title, String description, String mediaType, String url, String thumbnailUrl) {
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
