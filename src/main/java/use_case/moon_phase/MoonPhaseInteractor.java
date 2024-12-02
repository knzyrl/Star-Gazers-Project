package use_case.moon_phase;

import java.io.IOException;

import data_access.MoonPhaseDataAccessObject;
import entity.MoonPhase;
import helper.AstronomyCalculations;

/**
 * Interactor for the Moon Phase use case.
 */
public class MoonPhaseInteractor implements MoonPhaseInputBoundary {

    private final MoonPhaseDataAccessObject moonPhasedao;
    private final MoonPhaseOutputBoundary moonPhasePresenter;

    public MoonPhaseInteractor(MoonPhaseDataAccessObject moonPhasedao, MoonPhaseOutputBoundary moonPhasePresenter) {
        this.moonPhasedao = moonPhasedao;
        this.moonPhasePresenter = moonPhasePresenter;
    }

    /**
     * Executes the Moon Phase use case with the provided input data.
     * Validates the input and processes the response, handling any errors appropriately.
     *
     * @param moonPhaseInputData The {@link MoonPhaseInputData} containing the location and date details.
     * @throws IOException If an I/O error occurs during the query.
     */
    public void execute(MoonPhaseInputData moonPhaseInputData) throws IOException {
        final String latitude = moonPhaseInputData.getLatitude();
        final String longitude = moonPhaseInputData.getLongitude();
        final String date = moonPhaseInputData.getDate();
        final String ra = Double.toString(AstronomyCalculations.calcRa(longitude, date));
        final String declination = Double.toString(Math.round(Double.parseDouble(latitude)));

        final String query = String.format(
                "{\"style\":{\"moonStyle\":\"default\",\"backgroundStyle\":\"stars\","
                        + "\"backgroundColor\":\"#000000\",\"headingColor\":\"#ffffff\",\"textColor\":\"#ffffff\"},"
                        + "\"observer\":{\"latitude\":%s,\"longitude\":%s,\"date\":\"%s\"},"
                        + "\"view\":{\"type\":\"portrait-simple\",\"parameters\":{}}}",
                latitude, longitude, date
        );
        final String imageUrl = moonPhasedao.executeQuery(query);

        final MoonPhase moonPhase = new MoonPhase(latitude, longitude, date, imageUrl);

        if (!moonPhase.validLatitudeFormat() || !moonPhase.validLongitudeFormat() || !moonPhase.validDateFormat()) {
            moonPhasePresenter.prepareFailView(
                    "Invalid format. Please ensure that the date is in YYYY-MM-DD format, "
                            + "and that the latitude and longitude are both in decimal format."
            );
        }
        else if (!moonPhase.validLatitudeValue()) {
            moonPhasePresenter.prepareFailView(
                    "Incorrect value for latitude. Ensure that latitude is between -90.00 and 90.00."
            );
        }
        else if (!moonPhase.validLongitudeValue()) {
            moonPhasePresenter.prepareFailView(
                    "Incorrect value for longitude. Ensure that longitude is between -180.00 and 180.00."
            );
        }
        else {
            final MoonPhaseOutputData moonPhaseOutputData = new MoonPhaseOutputData(
                    moonPhase.getLatitude(), moonPhase.getLongitude(), moonPhase.getDate(),
                    moonPhase.getImgUrl(), false
            );
            moonPhasePresenter.displayMoonPhase(moonPhaseOutputData);
        }

    }

    /**
     * Executes a default action for the Moon Phase use case, typically navigating back.
     */
    public void execute() {
        moonPhasePresenter.back();
    }
}
