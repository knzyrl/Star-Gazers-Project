package use_case.geocoding;

import data_access.GeocoderDataAccessObject;
import entity.Location;
import interface_adapter.geocoding.GeocodingPresenter;

import java.util.List;

public class GeocodingInteractor {

    //private final GeocodingOutputBoundary geocodingOutputBoundary;
    private final GeocoderDataAccessObject geocoderDataAccessObject;
    private final GeocodingOutputBoundary geocodingPresenter;

    public GeocodingInteractor(GeocoderDataAccessObject geocoderDataAccessObject,
                               GeocodingOutputBoundary geocodingPresenter) {


        this.geocoderDataAccessObject = geocoderDataAccessObject;
        this.geocodingPresenter = geocodingPresenter;
    }

    public void execute(GeocodingInputData geocodingInputData) {
        final String address = geocodingInputData.getAddress();

        final List<String> longlat = geocoderDataAccessObject.converter(address);

        if (longlat.get(0) == null) {
            geocodingPresenter.noAddressFound();
        }
        else{
            Location location = new Location(address, longlat.get(0), longlat.get(1));

            geocodingPresenter.execute(location);
        }


    }
}
