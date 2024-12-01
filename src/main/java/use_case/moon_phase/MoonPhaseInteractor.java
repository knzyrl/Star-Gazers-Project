package use_case.moon_phase;

import data_access.MoonPhaseDataAccessObject;
import entity.MoonPhase;
import helper.AstronomyCalculations;

import java.io.IOException;

public class MoonPhaseInteractor implements MoonPhaseInputBoundary {

    private final MoonPhaseDataAccessObject moonPhaseDAO;
    private final MoonPhaseOutputBoundary moonPhasePresenter;

    public MoonPhaseInteractor(MoonPhaseDataAccessObject moonPhaseDAO, MoonPhaseOutputBoundary moonPhasePresenter) {
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

        if (!(moonPhase.validLatitude()) && (moonPhase.validLongitude())) {
            moonPhasePresenter.prepareFailView("Invalid Latitude. Please try again.");
        } else if (!(moonPhase.validLongitude()) && (moonPhase.validLatitude())) {
            moonPhasePresenter.prepareFailView("Invalid Longitude. Please try again.");
        } else if (!(moonPhase.validLatitude()) && !(moonPhase.validLongitude()) && moonPhase.validDate()) {
            moonPhasePresenter.prepareFailView("Invalid Latitude and Longitude. Please try again.");
        } else if (!(moonPhase.validDate())) {
            moonPhasePresenter.prepareFailView("Invalid Date. Please try again.");
        }
        else {
            MoonPhaseOutputData moonPhaseOutputData = new MoonPhaseOutputData(moonPhase.getLatitude(), moonPhase.getLongitude(), moonPhase.getDate(), moonPhase.getImgURL(), false);
            moonPhasePresenter.displayMoonPhase(moonPhaseOutputData);
        }

    }

    public void execute() {
        moonPhasePresenter.back();
    }
}
