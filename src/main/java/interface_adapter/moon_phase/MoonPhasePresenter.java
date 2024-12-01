package interface_adapter.moon_phase;

import entity.MoonPhase;
import use_case.moon_phase.MoonPhaseOutputBoundary;
import use_case.moon_phase.MoonPhaseOutputData;
import view.DisplayMoonPhaseView;
import view.ViewManager;

import java.io.IOException;

public class MoonPhasePresenter implements MoonPhaseOutputBoundary {
    private ViewManager viewManager;
    private DisplayMoonPhaseView displayMoonPhaseView;

    public MoonPhasePresenter(ViewManager viewManager){
        this.viewManager = viewManager;
    }

    public void setDisplayMoonPhaseView(DisplayMoonPhaseView displayMoonPhaseView){
        this.displayMoonPhaseView = displayMoonPhaseView;
    }

    public void displayMoonPhase(MoonPhaseOutputData moonPhaseOutputData) throws IOException {

        displayMoonPhaseView.refresh(moonPhaseOutputData);
        viewManager.getViews().add(displayMoonPhaseView, "display Moon Phase");
        viewManager.show("display Moon Phase");
    }

    public void back() {
        viewManager.show("home");
    }

    public void prepareFailView(String errorMessage) {
        viewManager.show(errorMessage);
    }
}
