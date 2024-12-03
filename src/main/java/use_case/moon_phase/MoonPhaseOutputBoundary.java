package use_case.moon_phase;

import java.io.IOException;

/**
 * Interface (Abstraction) between the Business Rules/Entities layers and Interface Adapter layer.
 * Implemented by MoonPhasePresenter.
 */
public interface MoonPhaseOutputBoundary {
    /**
     * Method responsible for displaying the Moon Phase based on user input.
     * @param moonPhaseOutputData received from the Interactor.
     * @throws IOException in case of invalid input or output.
     */
    void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) throws IOException;

    /**
     * Method responsible for reverting to HomeView.
     */
    void back();

    /**
     * Method responsible for displaying the view with the error message.
     * @param error is the error message to be displayed.
     */
    void prepareFailView(String error);
}
