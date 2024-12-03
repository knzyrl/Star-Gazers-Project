package interface_adapter.apod_date;

import use_case.apod_date.APODInputBoundary;

public class ApodController {
    private final APODInputBoundary interactor;

    public ApodController(APODInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Calls the interactor to fetch the APOD.
     */
    public void fetchApod() {
        System.out.println("APODController: fetchAstronomyPicture called");
        interactor.fetchAPOD();
    }

    /**
     * Calls the interactor to fetch the APOD on a given date.
     * @param date The target date to fetch the APOD for.
     */
    public void fetchApodByDate(String date) {
        System.out.println("APODController: fetchAstronomyPictureByDate called for date " + date);
        interactor.fetchAPODByDate(date);
    }

    /**
     * Calls the interactor to return to the home view.
     */
    public void navigateToHome() {
        System.out.println("APODController: navigateToHome called");
        // Delegate navigation to interactor
        interactor.goBackToHome();
    }
}
