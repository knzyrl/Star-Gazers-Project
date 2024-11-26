package use_case.apod_date;

public class APODOutputData {
    private final String imageUrl;
    private final String description;
    private final String title;

    public APODOutputData(String imageUrl, String description, String title) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
