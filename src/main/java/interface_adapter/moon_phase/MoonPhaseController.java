package interface_adapter.moon_phase;

import java.io.IOException;

import use_case.moon_phase.MoonPhaseInputBoundary;
import use_case.moon_phase.MoonPhaseInputData;

public class MoonPhaseController {
    private final MoonPhaseInputBoundary moonPhaseInteractor;

    public MoonPhaseController(MoonPhaseInputBoundary moonPhaseInteractor) {
        this.moonPhaseInteractor = moonPhaseInteractor;
    }

    /**
     * Calls the interactor to execute the moon phase use case.
     * @param longitude the longitude input by the user
     * @param latitude the latitude input by the user
     * @param date the date input by the user
     * @throws IOException Exception
     */
    public void execute(String longitude, String latitude, String date) throws IOException {
        final MoonPhaseInputData moonPhaseInputData = new MoonPhaseInputData(longitude, latitude, date);
        moonPhaseInteractor.execute(moonPhaseInputData);
    }

    /**
     * Calls the interactor to return to the home view.
     */
    public void execute() {
        moonPhaseInteractor.execute();
    }
}
