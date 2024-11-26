package use_case.apod_date;

public class APODInputData {
    private final String date;

    public APODInputData(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }
}
