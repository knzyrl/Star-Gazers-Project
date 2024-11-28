package use_case.apod_date;

public class APODOutputData {
    private final String title;
    private final String description;
    private final String imageUrl;

    public APODOutputData(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}