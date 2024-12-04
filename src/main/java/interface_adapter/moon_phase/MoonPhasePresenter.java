package interface_adapter.moon_phase;

import interface_adapter.ViewManager;
import use_case.moon_phase.MoonPhaseOutputBoundary;
import use_case.moon_phase.MoonPhaseOutputData;

/**
 * Class for Presenter for the Moon Phase use case.
 * Implements the MoonPhaseOutputBoundary interface.
 * Receives output data from MoonPhaseInteractor.
 * Updates the view displayed to the user by communicating with the ViewManager.
 */
public class MoonPhasePresenter implements MoonPhaseOutputBoundary {
    private ViewManager viewManager;

    public MoonPhasePresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Calls the ViewManager to display the ouput.
     * @param moonPhaseOutputData output data from the interactor.
     */
    public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) {
        viewManager.displayMoonPhase(moonPhaseOutputData);
    }

    /**
     * Calls the ViewManager to return to the home view.
     */
    public void back() {
        viewManager.show("home");
    }

    /**
     * Calls the ViewManager to show the fail view with a given error message.
     * @param error message to be shown on the fail view.
     */
    public void prepareFailView(String error) {
        viewManager.showFailView(error);
    }
}
