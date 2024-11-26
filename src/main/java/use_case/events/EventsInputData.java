package use_case.events;

public class EventsInputData {
    private String longitude;
    private String latitude;
    private String dateStart;
    private String dateEnd;
    private String body;

    public EventsInputData(String longitude, String latitude, String dateStart, String dateEnd, String body) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.body = body;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getBody() {
        return body;
    }
}
