package use_case.apod_date;

public interface APODInputBoundary {
    void fetchAPOD();
    void fetchAPODByDate(String date);
    void goBackToHome();
}