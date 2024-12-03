package interface_adapter.apod_date;

import use_case.apod_date.ApodInputBoundary;

/**
 * Class for controller for the Astronomical Picture of the Day (APOD) use case.
 * Gets input from the APODView, packages it, and sends it to the APODInteractor.
 */
public class ApodController {
    private final ApodInputBoundary interactor;

    public ApodController(ApodInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Calls the interactor to fetch the APOD.
     */
    public void fetchApod() {
        System.out.println("APODController: fetchAPOD called");
        interactor.fetchApod();
    }

    /**
     * Calls the interactor to fetch the APOD on a specific date.
     *
     * @param date The target date to fetch the APOD for.
     */
    public void fetchApodByDate(String date) {
        System.out.println("APODController: fetchAPODByDate called for date " + date);
        interactor.fetchApodByDate(date);
    }

    /**
     * Calls the interactor to return to the home view.
     */
    public void navigateToHome() {
        System.out.println("APODController: navigateToHome called");
        interactor.goBackToHome();
    }
}
