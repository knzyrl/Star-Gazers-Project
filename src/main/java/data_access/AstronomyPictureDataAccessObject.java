package data_access;

/**
 * Interface for fetching Astronomy Picture of the Day (APOD) data.
 * Defines the contract for accessing APOD data from any source.
 */
public interface AstronomyPictureDataAccessObject {

    /**
     * Fetches the Astronomy Picture of the Day (APOD) data for the current date.
     *
     * @return A raw JSON response containing APOD data for the current date.
     */
    String fetchAstronomyPicture();

    /**
     * Fetches the Astronomy Picture of the Day (APOD) data for a specified date.
     *
     * @param date The date for which to fetch the APOD data, in the format "YYYY-MM-DD".
     * @return A raw JSON response containing APOD data for the specified date.
     */
    String fetchAstronomyPictureByDate(String date);
}
