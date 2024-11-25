package entity;

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