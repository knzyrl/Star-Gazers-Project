package use_case.moon_phase;

import java.io.IOException;

public interface MoonPhaseInputBoundary {

    void execute(MoonPhaseInputData moonPhaseInputData) throws IOException;

    void execute();
}
