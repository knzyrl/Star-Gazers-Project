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
     * Executes the geocode use case.
     * @param address The address that they want to geocode.
     * @throws IOException Exception
     */
    public void execute(String address) throws IOException {
        final GeocodingInputData geocodingInputData = new GeocodingInputData(address);

        geocodingInteractor.execute(geocodingInputData);
    }

    /**
     * Allows the user to go back during the geocode use case.
     * @throws IOException Exception
     */
    public void executeBack() throws IOException {

        geocodingPresenter.executeBack();
    }

    /**
     * Allows the user to go back to the home view.
     * @throws IOException Exception
     */
    public void executeHome() throws IOException {

        geocodingPresenter.executeHome();
    }

    /**
     * Allows the user to go to the name view.
     * @throws IOException Exception
     */
    public void executeName() throws IOException {

        geocodingPresenter.executeName();
    }
}
