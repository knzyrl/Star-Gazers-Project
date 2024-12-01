package use_case.moon_phase;

import entity.MoonPhase;

import java.io.IOException;

public interface MoonPhaseOutputBoundary {
    void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) throws IOException;

    void back();

    void prepareFailView(String s);
}
