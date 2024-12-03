package use_case.apod_date;

/**
 * Interface (Abstraction) between the Interface Adapter and Business Rules/Entities layers.
 * This particular Interface is implemented the APODInteractor.
 */
public interface AstronomicalPictureInputBoundary {
    /**
     * Abstract method to fetch the Astronomy Picture of the current day.
     */
    void fetchAstronomicalPictureOfTheDay();

    /**
     * Abstract method to fetch the Astronomy Picture of a specified day.
     * @param date the date specified by the user for which they wish to see the Astronomy Picture.
     */
    void fetchAstronomicalPictureByDate(String date);

    /**
     * Abstarct method to return to the Home View.
     */
    void goBackToHome();
}
