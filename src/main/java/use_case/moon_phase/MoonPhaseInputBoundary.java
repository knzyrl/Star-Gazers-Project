package use_case.moon_phase;

import java.io.IOException;

/**
 * Interface (Abstraction) between the Interface Adapter and Business Rules/Entities layers.
 * Implemented by MoonPhaseInteractor.
 */
public interface MoonPhaseInputBoundary {

    /**
     * Method responsible for working on the input data as required by the use case.
     * @param moonPhaseInputData received from MoonPhaseController.
     * @throws IOException thrown when given invalid Input.
     */
    void execute(MoonPhaseInputData moonPhaseInputData) throws IOException;

    /**
     * Method responsible for reverting to HomeView.
     */
    void execute();
}
