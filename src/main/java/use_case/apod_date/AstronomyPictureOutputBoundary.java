package use_case.apod_date;

/**
 * Output boundary for the Astronomy Picture of the Day (APOD) use case.
 */
public interface AstronomyPictureOutputBoundary {

    /**
     * Presents the processed APOD data to the output layer.
     *
     * @param outputData The {@link AstronomyPictureOutputData} containing the APOD information
     *                   such as title, description, media type, and URLs.
     */
    void presentAstronomyPicture(AstronomyPictureOutputData outputData);
}
