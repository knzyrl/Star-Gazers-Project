package interface_adapter.moon_phase;

import use_case.moon_phase.MoonPhaseOutputBoundary;
import use_case.moon_phase.MoonPhaseOutputData;
import view.ViewManager;

public class MoonPhasePresenter implements MoonPhaseOutputBoundary {
    private ViewManager viewManager;

    public MoonPhasePresenter(ViewManager viewManager){
        this.viewManager = viewManager;
    }

    public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) {
        viewManager.displayMoonPhase(moonPhaseOutputData);
    }

    public void back() {
        viewManager.show("home");
    }

    public void prepareFailView(String errorMessage) {
        viewManager.showFailView(errorMessage);
    }
}
