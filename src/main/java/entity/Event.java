package entity;

/**
 * Represents an astronomical event with details about the celestial body, event type, and date.
 * This class encapsulates the information associated with an event in the astronomy domain.
 */
public class Event {
    private String body;
    private String type;
    private String date;

    /**
     * Constructs an {@code Event} object with the specified celestial body, event type, and date.
     *
     * @param body The celestial body associated with the event.
     * @param type The type of the event.
     * @param date The date of the event in the format "YYYY-MM-DD".
     */
    public Event(String body, String type, String date) {
        this.body = body;
        this.type = type;
        this.date = date;
    }

    /**
     * Retrieves the celestial body associated with the event.
     *
     * @return The name of the celestial body.
     */
    public String getBody() {
        return body;
    }

    /**
     * Retrieves the type of the event.
     *
     * @return The event type as a {@link String}.
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the date of the event.
     *
     * @return The date of the event in the format "YYYY-MM-DD".
     */
    public String getDate() {
        return date;
    }
}
