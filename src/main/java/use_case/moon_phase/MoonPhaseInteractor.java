package use_case.moon_phase;

import data_access.MoonPhaseDataAccessObject;
import entity.MoonPhase;
import helper.AstronomyCalculations;
import interface_adapter.moon_phase.MoonPhasePresenter;

import java.io.IOException;

public class MoonPhaseInteractor {

    private final MoonPhaseDataAccessObject moonPhaseDAO;
    private final MoonPhasePresenter moonPhasePresenter;

    public MoonPhaseInteractor(MoonPhaseDataAccessObject moonPhaseDAO, MoonPhasePresenter moonPhasePresenter) {
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
        moonPhasePresenter.displayMoonPhase(moonPhase);
    }

    public void execute() {
        moonPhasePresenter.back();
    }
}
