package use_case.apod_date;

/**
 * Represents the input boundary for the APOD use case.
 * Provides methods to fetch Astronomy Picture of the Day (APOD) data
 * and navigate back to the home interface.
 */
public interface ApodInputBoundary {

    /**
     * Fetches the Astronomy Picture of the Day for today.
     */
    void fetchApod();

    /**
     * Fetches the Astronomy Picture of the Day for a specific date.
     *
     * @param date the date for which the APOD should be fetched, formatted as YYYY-MM-DD.
     */
    void fetchApodByDate(String date);

    /**
     * Navigates back to the home interface.
     */
    void goBackToHome();
}
