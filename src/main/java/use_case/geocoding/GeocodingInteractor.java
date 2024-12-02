package use_case.geocoding;

import data_access.GeocoderApiDataAccessObject;
import data_access.GeocoderDataAccessObject;
import entity.Location;

import java.util.List;

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

    public void execute(GeocodingInputData geocodingInputData) {
        final String address = geocodingInputData.getAddress();

        final List<String> longlat = geocoderDataAccessObject.converter(address);

        Location location = new Location(longlat.get(2), longlat.get(0), longlat.get(1));

        // Occurs when no address is found
        if (location.getLatitude() == null) {
            geocodingPresenter.noAddressFound();
        }

        // Gives lat and long for an address found
        else{
            GeocodingOutputData geocodingOutputData = new GeocodingOutputData(location.getAddress(),
                    location.getLongitude(), location.getLatitude(), false);

            geocodingPresenter.execute(geocodingOutputData);
        }


    }
}