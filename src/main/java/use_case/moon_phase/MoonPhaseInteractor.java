package use_case.moon_phase;

import java.io.IOException;

import data_access.AstronomyApiDataAccessObject;
import entity.MoonPhase;

/**
 * Class for the Interactor for the Moon Phase use case.
 * Implements MoonPhaseInputBoundary.
 * Receives input data from the Controller and process it with the help of the corresponding API and Entity.
 * Sends the output data to the OutputBoundary for display.
 */
public class MoonPhaseInteractor implements MoonPhaseInputBoundary {

    private final AstronomyApiDataAccessObject moonPhaseDataAccessObject;
    private final MoonPhaseOutputBoundary moonPhasePresenter;

    public MoonPhaseInteractor(AstronomyApiDataAccessObject moonPhaseDataAccessObject,
                               MoonPhaseOutputBoundary moonPhasePresenter) {
        this.moonPhaseDataAccessObject = moonPhaseDataAccessObject;
        this.moonPhasePresenter = moonPhasePresenter;
    }

    /**
     * Concrete implementation of the method from MoonPhaseInputBoundary responsible for executing the use case.
     * @param moonPhaseInputData received from MoonPhaseController.
     * @throws IOException thrown when invalid input is given.
     */
    public void execute(MoonPhaseInputData moonPhaseInputData) throws IOException {
        final String latitude = moonPhaseInputData.getLatitude();
        final String longitude = moonPhaseInputData.getLongitude();
        final String date = moonPhaseInputData.getDate();
        // final String ra = Double.toString(AstronomyCalculations.calcRA(longitude, date));
        // final String declination = Double.toString(Math.round(Double.parseDouble(latitude)));

        final String query = String.format("{\"style\":{\"moonStyle\":\"default\",\"backgroundStyle\":\"stars\","
                + "\"backgroundColor\":\"#000000\",\"headingColor\":\"#ffffff\",\"textColor\":\"#ffffff\"},"
                + "\"observer\":{\"latitude\":%s,\"longitude\":%s,\"date\":\"%s\"},"
                + "\"view\":{\"type\":\"portrait-simple\",\"parameters\":{}}}", latitude, longitude, date);
        final String imageUrl = moonPhaseDataAccessObject.executeQuery(query);

        final MoonPhase moonPhase = new MoonPhase(longitude, latitude, date, imageUrl);

        if (!(moonPhase.validLatitudeFormat()) || !(moonPhase.validLongitudeFormat())
                || !(moonPhase.validDateFormat())) {
            moonPhasePresenter.prepareFailView("Invalid format. Please ensure that the date is in YYYY-MM-DD format,"
                    + " and that the latitude and longitude are both in decimal format.");
        }
        else if (!(moonPhase.validLatitudeValue()) || !(moonPhase.validLongitudeValue())) {
            moonPhasePresenter.prepareFailView("Invalid value. Ensure that latitude is between -90.00 and 90.00 and "
                    + "longitude is between -180.00 and 180.00.");
        }
        else {
            final MoonPhaseOutputData moonPhaseOutputData = new MoonPhaseOutputData(moonPhase.getLatitude(),
                    moonPhase.getLongitude(), moonPhase.getDate(), moonPhase.getImgUrl(), false);
            moonPhasePresenter.displayMoonPhase(moonPhaseOutputData);
        }

    }

    /**
     * Concrete implementation of the method from MoonPhaseInputBoundary responsible for reverting to HomeView.
     */
    public void execute() {
        moonPhasePresenter.back();
    }
}
