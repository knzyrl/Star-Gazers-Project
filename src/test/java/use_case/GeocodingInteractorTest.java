package use_case;

import data_access.GeocoderDataAccessObject;
import org.junit.jupiter.api.Test;
import use_case.geocoding.GeocodingInputData;
import use_case.geocoding.GeocodingInteractor;
import use_case.geocoding.GeocodingOutputBoundary;
import use_case.geocoding.GeocodingOutputData;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeocodingInteractorTest {

    private GeocodingOutputData setData;
    @Test
    public void successTest() {
        GeocodingInputData geocodingInputData = new GeocodingInputData("40 Bay St., Toronto");
        GeocoderDataAccessObject geocoderDataAccessObject = new GeocoderDataAccessObject();
        GeocodingOutputBoundary geocodingPresenter = new GeocodingOutputBoundary() {

            @Override
            public void execute(GeocodingOutputData geocodingOutputData) {
                setData = geocodingOutputData;
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

        assertEquals("-79.37907772483726", setData.getLongitude());
        assertEquals("43.64343375", setData.getLatitude());

    }

    @Test
    public void failureTest() {
        GeocodingInputData geocodingInputData = new GeocodingInputData("What is this address??");
        GeocoderDataAccessObject geocoderDataAccessObject = new GeocoderDataAccessObject();
        GeocodingOutputBoundary geocodingPresenter = new GeocodingOutputBoundary() {

            @Override
            public void execute(GeocodingOutputData geocodingOutputData) {
                setData = geocodingOutputData;
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

        assertNull(setData);
    }
}
