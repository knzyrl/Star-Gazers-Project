package interface_adapter.geocoding;

import use_case.geocoding.GeocodingInputData;
import use_case.geocoding.GeocodingInteractor;

import java.io.IOException;

public class GeocodingController {
    private final GeocodingInteractor geocodingInteractor;
    private GeocodingPresenter geocodingPresenter;

    public GeocodingController(GeocodingInteractor geocodingInteractor, GeocodingPresenter geocodingPresenter) {
        this.geocodingInteractor = geocodingInteractor;
        this.geocodingPresenter = geocodingPresenter;
    }

    public void execute(String address) throws IOException {
        GeocodingInputData geocodingInputData = new GeocodingInputData(address);

        geocodingInteractor.execute(geocodingInputData);
    }

    public void executeBack() throws IOException {

        geocodingPresenter.executeBack();
    }
}

