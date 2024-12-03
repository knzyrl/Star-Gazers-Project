package interface_adapter.apod_date;

import use_case.apod_date.AstronomicalPictureInputBoundary;

/**
 * Class for controller for the Astronomical Picture of the Day (APOD) use case.
 * Gets input from the APODView, packages it, and sends it to the APODInteractor.
 */
public class ApodController {
    private final AstronomicalPictureInputBoundary interactor;

    public ApodController(AstronomicalPictureInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Calls the interactor to fetch the APOD.
     */
    public void fetchAPOD() {
        System.out.println("APODController: fetchAPOD called");
        interactor.fetchAstronomicalPictureOfTheDay();
    }

    /**
     * Calls the interactor to fetch the APOD on a specific date.
     *
     * @param date The target date to fetch the APOD for.
     */
    public void fetchAPODByDate(String date) {
        System.out.println("APODController: fetchAPODByDate called for date " + date);
        interactor.fetchAstronomicalPictureByDate(date);
    }

    /**
     * Calls the interactor to return to the home view.
     */
    public void navigateToHome() {
        System.out.println("APODController: navigateToHome called");
        interactor.goBackToHome();
    }
}
