package interface_adapter.display_moon_phase;

import interface_adapter.ViewManager;

/**
 * Class for DisplayMoonPhasePresenter.
 * Responsible for presenting the display of the use case.
 * Allows the user to revert to the home screen.
 */

public class DisplayMoonPhasePresenter {
    private ViewManager viewManager;

    public DisplayMoonPhasePresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Calls the ViewManager to return to the home view.
     */
    public void execute() {
        viewManager.show("home");
    }
}
