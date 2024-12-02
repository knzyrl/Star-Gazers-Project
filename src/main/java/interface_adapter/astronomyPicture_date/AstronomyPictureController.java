package interface_adapter.astronomyPicture_date;

import use_case.apod_date.AstronomyPictureInputBoundary;

/**
 * Controller for managing Astronomy Picture of the Day (APOD) interactions.
 */
public class AstronomyPictureController {
    private final AstronomyPictureInputBoundary interactor;

    public AstronomyPictureController(AstronomyPictureInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Fetches the Astronomy Picture of the Day (APOD) for the current date.
     * Delegates the request to the interactor.
     */
    public void fetchAstronomyPicture() {
        System.out.println("AstronomyPictureController: fetchAPOD called");
        interactor.fetchAstronomyPicture();
    }

    /**
     * Fetches the Astronomy Picture of the Day (APOD) for a specific date.
     * Delegates the request to the interactor.
     *
     * @param date The date for which to fetch the APOD, in the format "YYYY-MM-DD".
     */
    public void fetchAstronomyPictureByDate(String date) {
        System.out.println("AstronomyPictureController: fetchAstronomyPictureByDate called for date " + date);
        interactor.fetchAstronomyPictureByDate(date);
    }

    /**
     * Navigates back to the home view.
     * Delegates the navigation request to the interactor.
     */
    public void navigateToHome() {
        System.out.println("AstronomyPictureController: navigateToHome called");
        // Delegate navigation to interactor
        interactor.goBackToHome();
    }
}
