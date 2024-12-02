package entity;

/**
 * Represents an astronomical event with details about the celestial body, event type, and date.
 * This class encapsulates the information associated with an event in the astronomy domain.
 */
public class Event {
    private String body;
    private String type;
    private String date;

    public Event(String body, String type, String date) {
        this.body = body;
        this.type = type;
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
