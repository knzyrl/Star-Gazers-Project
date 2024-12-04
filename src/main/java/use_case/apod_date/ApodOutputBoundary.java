package use_case.apod_date;

/**
 * Output boundary interface for presenting the Astronomy Picture of the Day (APOD) data.
 * Implementations of this interface define how the APOD data is displayed or processed.
 */
public interface ApodOutputBoundary {

    /**
     * Presents the Astronomy Picture of the Day (APOD) data.
     *
     * @param outputData The data object containing the APOD details to be presented.
     */
    void presentApod(ApodOutputData outputData);
}
