package use_case.moon_phase;

import data_access.AstronomyApiDataAccessObject;
import entity.MoonPhase;
import helper.AstronomyCalculations;

import java.io.IOException;

public class MoonPhaseInteractor implements MoonPhaseInputBoundary {

    private final AstronomyApiDataAccessObject moonPhaseDAO;
    private final MoonPhaseOutputBoundary moonPhasePresenter;

    public MoonPhaseInteractor(AstronomyApiDataAccessObject moonPhaseDAO, MoonPhaseOutputBoundary moonPhasePresenter) {
        this.moonPhaseDAO = moonPhaseDAO;
        this.moonPhasePresenter = moonPhasePresenter;
    }

    public void execute(MoonPhaseInputData moonPhaseInputData) throws IOException {
        final String latitude = moonPhaseInputData.getLatitude();
        final String longitude = moonPhaseInputData.getLongitude();
        final String date = moonPhaseInputData.getDate();
        final String ra = Double.toString(AstronomyCalculations.calcRA(longitude, date));
        final String declination = Double.toString(Math.round(Double.parseDouble(latitude)));

        final String query = String.format("{\"style\":{\"moonStyle\":\"default\",\"backgroundStyle\":\"stars\",\"backgroundColor\":\"#000000\",\"headingColor\":\"#ffffff\",\"textColor\":\"#ffffff\"},\"observer\":{\"latitude\":%s,\"longitude\":%s,\"date\":\"%s\"},\"view\":{\"type\":\"portrait-simple\",\"parameters\":{}}}", latitude, longitude, date);
        final String imageURL = moonPhaseDAO.executeQuery(query);

        final MoonPhase moonPhase = new MoonPhase(latitude, longitude, date, imageURL);

        if (!(moonPhase.validLatitudeFormat()) || !(moonPhase.validLongitudeFormat()) || !(moonPhase.validDateFormat())) {
            moonPhasePresenter.prepareFailView("Invalid format. Please ensure that the date is in YYYY-MM-DD format, and that the latitude and longitude are both in decimal format.");
        } else if (!(moonPhase.validLatitudeValue())) {
            moonPhasePresenter.prepareFailView("Incorrect value for latitude. Ensure that latitude is between -90.00 and 90.00.");
        } else if (!(moonPhase.validLongitudeValue())) {
            moonPhasePresenter.prepareFailView("Incorrect value for longitude. Ensure that longitude is between -180.00 and 180.00.");
        } else {
            MoonPhaseOutputData moonPhaseOutputData = new MoonPhaseOutputData(moonPhase.getLatitude(), moonPhase.getLongitude(), moonPhase.getDate(), moonPhase.getImgURL(), false);
            moonPhasePresenter.displayMoonPhase(moonPhaseOutputData);
        }

    }

    public void execute() {
        moonPhasePresenter.back();
    }
}
