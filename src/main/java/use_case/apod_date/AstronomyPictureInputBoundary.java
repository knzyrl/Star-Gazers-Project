package use_case.apod_date;

/**
 * Input boundary for the Astronomy Picture of the Day (APOD) use case.
 */
public interface AstronomyPictureInputBoundary {

    /**
     * Fetches the Astronomy Picture of the Day (APOD) for the current date.
     */
    void fetchAstronomyPicture();

    /**
     * Fetches the Astronomy Picture of the Day (APOD) for a specified date.
     *
     * @param date The date for which to fetch the APOD, in "YYYY-MM-DD" format.
     */
    void fetchAstronomyPictureByDate(String date);

    /**
     * Navigates back to the home view.
     */
    void goBackToHome();
}
