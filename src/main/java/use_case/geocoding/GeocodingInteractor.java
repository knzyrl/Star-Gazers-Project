package use_case.geocoding;

import java.util.List;

import data_access.GeocoderApiDataAccessObject;
import data_access.GeocoderDataAccessObject;
import entity.Location;

/**
 * Interactor for geocoding use case.
 */
public class GeocodingInteractor implements GeocodingInputBoundary {

    private final GeocoderApiDataAccessObject geocoderDataAccessObject;
    private final GeocodingOutputBoundary geocodingPresenter;

    public GeocodingInteractor(GeocoderDataAccessObject geocoderDataAccessObject,
                               GeocodingOutputBoundary geocodingPresenter) {
        this.geocoderDataAccessObject = geocoderDataAccessObject;
        this.geocodingPresenter = geocodingPresenter;
    }

    /**
     * Concrete implementation of the execute method in GeocodingInputBoundary.
     * @param geocodingInputData input data for this use case.
     */
    public void execute(GeocodingInputData geocodingInputData) {
        final String address = geocodingInputData.getAddress();

        final List<String> longlat = geocoderDataAccessObject.converter(address);

        final Location location = new Location(longlat.get(2), longlat.get(0), longlat.get(1));

        // Occurs when no address is found
        if (location.getLatitude() == null) {
            geocodingPresenter.noAddressFound();
        }

        // Gives lat and long for an address found
        else {
            final GeocodingOutputData geocodingOutputData = new GeocodingOutputData(location.getAddress(),
                    location.getLongitude(), location.getLatitude(), false);

            geocodingPresenter.execute(geocodingOutputData);
        }

    }
}
