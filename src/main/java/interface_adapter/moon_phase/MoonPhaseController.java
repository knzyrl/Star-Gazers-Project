package interface_adapter.moon_phase;

import java.io.IOException;

import use_case.moon_phase.MoonPhaseInputBoundary;
import use_case.moon_phase.MoonPhaseInputData;

/**
 * Controller for the Moon Phase use case.
 */
public class MoonPhaseController {
    private final MoonPhaseInputBoundary moonPhaseInteractor;

    public MoonPhaseController(MoonPhaseInputBoundary moonPhaseInteractor) {
        this.moonPhaseInteractor = moonPhaseInteractor;
    }

    /**
     * Executes the Moon Phase use case with the provided input data.
     *
     * @param longitude The longitude of the location.
     * @param latitude  The latitude of the location.
     * @param date      The date for which to calculate the Moon Phase, in "YYYY-MM-DD" format.
     * @throws IOException If an I/O error occurs during execution.
     */
    public void execute(String longitude, String latitude, String date) throws IOException {
        final MoonPhaseInputData moonPhaseInputData = new MoonPhaseInputData(longitude, latitude, date);
        moonPhaseInteractor.execute(moonPhaseInputData);
    }

    /**
     * Executes the default Moon Phase use case without additional input data.
     */
    public void execute() {
        moonPhaseInteractor.execute();
    }
}
