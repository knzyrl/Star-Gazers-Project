package interface_adapter.display_moon_phase;

import view.ViewManager;

/**
 * Presenter for managing the display of moon phase information.
 */
public class DisplayMoonPhasePresenter {
    private ViewManager viewManager;

    public DisplayMoonPhasePresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Executes the display moon phase use case.
     * This method switches the current view to the "home" view.
     */
    public void execute() {
        viewManager.show("home");
    }
}
