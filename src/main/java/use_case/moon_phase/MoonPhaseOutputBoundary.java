package use_case.moon_phase;

import java.io.IOException;

/**
 * Output boundary for the Moon Phase use case.
 */
public interface MoonPhaseOutputBoundary {

    /**
     * Displays the processed Moon Phase data.
     *
     * @param moonPhaseOutputData The {@link MoonPhaseOutputData} containing the moon phase details to display.
     * @throws IOException If an I/O error occurs during the presentation of data.
     */
    void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) throws IOException;

    /**
     * Navigates back to the previous view or home view.
     */
    void back();

    /**
     * Prepares the view to display an error message when input validation or processing fails.
     *
     * @param input The error message to display.
     */
    void prepareFailView(String input);
}
