package use_case;

import data_access.GeocoderDataAccessObject;
import entity.Location;
import org.junit.jupiter.api.Test;
import use_case.geocoding.GeocodingInputData;
import use_case.geocoding.GeocodingInteractor;
import use_case.geocoding.GeocodingOutputBoundary;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeocodingInteractorTest {

    private Location setLocation;
    @Test
    public void successTest() {
        GeocodingInputData geocodingInputData = new GeocodingInputData("40 Bay St., Toronto");
        GeocoderDataAccessObject geocoderDataAccessObject = new GeocoderDataAccessObject();
        GeocodingOutputBoundary geocodingPresenter = new GeocodingOutputBoundary() {

            @Override
            public void execute(Location location) {
                setLocation = location;
            }

            @Override
            public void executeBack() {

            }

            @Override
            public void noAddressFound() {

            }

            @Override
            public void executeName() {

            }
        };

        GeocodingInteractor geocodingInteractor = new GeocodingInteractor(geocoderDataAccessObject, geocodingPresenter);

        geocodingInteractor.execute(geocodingInputData);

        assertEquals("-79.37907772483726", setLocation.getLongtitude());
        assertEquals("43.64343375", setLocation.getLatitude());

    }

    @Test
    public void failureTest() {
        GeocodingInputData geocodingInputData = new GeocodingInputData("What is this address??");
        GeocoderDataAccessObject geocoderDataAccessObject = new GeocoderDataAccessObject();
        GeocodingOutputBoundary geocodingPresenter = new GeocodingOutputBoundary() {

            @Override
            public void execute(Location location) {
                setLocation = location;
            }

            @Override
            public void executeBack() {

            }

            @Override
            public void noAddressFound() {

            }

            @Override
            public void executeName() {

            }
        };

        GeocodingInteractor geocodingInteractor = new GeocodingInteractor(geocoderDataAccessObject, geocodingPresenter);

        geocodingInteractor.execute(geocodingInputData);

        assertNull(setLocation);
    }
}
