package interface_adapter.moon_phase;

import use_case.moon_phase.MoonPhaseInputBoundary;
import use_case.moon_phase.MoonPhaseInputData;

import java.io.IOException;

public class MoonPhaseController {
    private final MoonPhaseInputBoundary moonPhaseInteractor;

    public MoonPhaseController(MoonPhaseInputBoundary moonPhaseInteractor) {
        this.moonPhaseInteractor = moonPhaseInteractor;
    }

    public void execute(String longitude, String latitude, String date) throws IOException {
        MoonPhaseInputData moonPhaseInputData = new MoonPhaseInputData(longitude, latitude, date);
        moonPhaseInteractor.execute(moonPhaseInputData);
    }

    public void execute() {
        moonPhaseInteractor.execute();
    }
}
