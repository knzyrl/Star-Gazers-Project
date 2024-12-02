package interface_adapter.geocoding;

import java.io.IOException;

import use_case.geocoding.GeocodingInputBoundary;
import use_case.geocoding.GeocodingInputData;

/**
 * Controller for the geocoding use case.
 */
public class GeocodingController {
    private GeocodingInputBoundary geocodingInteractor;
    private GeocodingPresenter geocodingPresenter;

    public GeocodingController(GeocodingInputBoundary geocodingInteractor, GeocodingPresenter geocodingPresenter) {
        this.geocodingInteractor = geocodingInteractor;
        this.geocodingPresenter = geocodingPresenter;
    }

    /**
     * Executes the geocoding use case with the provided address.
     * @param address The address to geocode.
     * @throws IOException If an I/O error occurs during execution.
     */
    public void execute(String address) throws IOException {
        final GeocodingInputData geocodingInputData = new GeocodingInputData(address);

        geocodingInteractor.execute(geocodingInputData);
    }

    /**
     * Allows the user to go back during the geocoding use case.
     * @throws IOException If an I/O error occurs during navigation.
     */
    public void executeBack() throws IOException {

        geocodingPresenter.executeBack();
    }

    /**
     * Navigates back to the home view.
     * @throws IOException If an I/O error occurs during navigation.
     */
    public void executeHome() throws IOException {

        geocodingPresenter.executeHome();
    }

    /**
     * Navigates to the name view.
     * @throws IOException If an I/O error occurs during navigation.
     */
    public void executeName() throws IOException {

        geocodingPresenter.executeName();
    }
}
