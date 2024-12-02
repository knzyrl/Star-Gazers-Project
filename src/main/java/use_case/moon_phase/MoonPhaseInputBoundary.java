package use_case.moon_phase;

import java.io.IOException;

/**
 * Input boundary for the Moon Phase use case.
 */
public interface MoonPhaseInputBoundary {

    /**
     * Executes the Moon Phase use case with the provided input data.
     *
     * @param moonPhaseInputData The {@link MoonPhaseInputData} containing location and date details.
     * @throws IOException If an I/O error occurs during execution.
     */
    void execute(MoonPhaseInputData moonPhaseInputData) throws IOException;

    /**
     * Executes the default Moon Phase use case without additional input data.
     */
    void execute();
}
