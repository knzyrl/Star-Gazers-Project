package use_case.geocoding;

import java.util.List;

import data_access.GeocoderDataAccessObject;
import entity.Location;

/**
 * Interactor for geocoding use case.
 * */
public class GeocodingInteractor implements GeocodingInputBoundary {

    private final GeocoderDataAccessObject geocoderDataAccessObject;
    private final GeocodingOutputBoundary geocodingPresenter;

    public GeocodingInteractor(GeocoderDataAccessObject geocoderDataAccessObject,
                               GeocodingOutputBoundary geocodingPresenter) {

        this.geocoderDataAccessObject = geocoderDataAccessObject;
        this.geocodingPresenter = geocodingPresenter;
    }

    /**
     * Executes the Geocoding use case with the provided input data.
     * Fetches latitude and longitude for the given address and processes the results.
     *
     * @param geocodingInputData The {@link GeocodingInputData} containing the address to geocode.
     */
    public void execute(GeocodingInputData geocodingInputData) {
        final String address = geocodingInputData.getAddress();

        final List<String> longlat = geocoderDataAccessObject.converter(address);

        // Occurs when no address is found
        if (longlat.get(0) == null) {
            geocodingPresenter.noAddressFound();
        }

        // Gives lat and long for an address found
        else {
            final Location location = new Location(longlat.get(2), longlat.get(0), longlat.get(1));

            geocodingPresenter.execute(location);
        }

    }
}
