package entity;

/**
 * Represents an Astronomy Picture of the Day (APOD) entity.
 * Contains details such as the title, description, and image URL of the astronomy picture.
 */
public class AstronomyPicture {
    private final String title;
    private final String description;
    private final String imageUrl;

    public AstronomyPicture(String title, String description, String imageUrl) {
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
