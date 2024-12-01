package interface_adapter.APOD_date;

import use_case.apod_date.APODInputBoundary;

public class APODController {
    private final APODInputBoundary interactor;

    public APODController(APODInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void fetchAPOD() {
        System.out.println("APODController: fetchAPOD called");
        interactor.fetchAPOD();
    }

    public void fetchAPODByDate(String date) {
        System.out.println("APODController: fetchAPODByDate called for date " + date);
        interactor.fetchAPODByDate(date);
    }

    public void navigateToHome() {
        System.out.println("APODController: navigateToHome called");
        interactor.goBackToHome(); // Delegate navigation to interactor
    }
}