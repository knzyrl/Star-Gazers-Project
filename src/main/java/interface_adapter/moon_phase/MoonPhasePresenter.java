package interface_adapter.moon_phase;

import java.io.IOException;

import use_case.moon_phase.MoonPhaseOutputBoundary;
import use_case.moon_phase.MoonPhaseOutputData;
import view.DisplayMoonPhaseView;
import view.ViewManager;

/**
 * Presenter for the Moon Phase use case.
 */
public class MoonPhasePresenter implements MoonPhaseOutputBoundary {
    private ViewManager viewManager;
    private DisplayMoonPhaseView displayMoonPhaseView;

    public MoonPhasePresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Sets the view for displaying Moon Phase information.
     *
     * @param displayMoonPhaseView The view responsible for displaying Moon Phase data.
     */
    public void setDisplayMoonPhaseView(DisplayMoonPhaseView displayMoonPhaseView) {
        this.displayMoonPhaseView = displayMoonPhaseView;
    }

    /**
     * Displays Moon Phase data by refreshing the view with the provided output data
     * and transitioning to the display view.
     *
     * @param moonPhaseOutputData The output data containing Moon Phase information.
     * @throws IOException If an I/O error occurs during view update.
     */
    public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) throws IOException {

        displayMoonPhaseView.refresh(moonPhaseOutputData);
        viewManager.getViews().add(displayMoonPhaseView, "display Moon Phase");
        viewManager.show("display Moon Phase");
    }

    /**
     * Navigates back to the home view.
     */
    public void back() {
        viewManager.show("home");
    }

    /**
     * Prepares the view to display an error message if the Moon Phase use case fails.
     *
     * @param input The error message to display.
     */
    public void prepareFailView(String input) {
        viewManager.show(input);
    }
}
