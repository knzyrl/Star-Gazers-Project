package interface_adapter.display_moon_phase;

import view.ViewManager;

public class DisplayMoonPhasePresenter {
    private ViewManager viewManager;

    public DisplayMoonPhasePresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void execute() {
        viewManager.show("home");
    }
}
