package use_case.apod_date;

/**
 * Interface (Abstraction) between the Business Rules/Entities layers and Interface Adapter layer.
 * Implemented by ApodPresenter.
 */
public interface AstronomicalPictureOutputBoundary {

    /**
     * Method to present the Astronomical Picture of the Day.
     * @param outputData given by the Interactor.
     */
    void presentAstronomicalPicture(APODOutputData outputData);
}
