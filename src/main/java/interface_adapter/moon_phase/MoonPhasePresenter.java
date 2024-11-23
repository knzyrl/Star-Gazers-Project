package interface_adapter.moon_phase;

import entity.MoonPhase;
import view.DisplayMoonPhaseView;
import view.ViewManager;

import java.io.IOException;

public class MoonPhasePresenter {
    private ViewManager viewManager;
    private DisplayMoonPhaseView displayMoonPhaseView;

    public MoonPhasePresenter(ViewManager viewManager){
        this.viewManager = viewManager;
    }

    public void setDisplayMoonPhaseView(DisplayMoonPhaseView displayMoonPhaseView){
        this.displayMoonPhaseView = displayMoonPhaseView;
    }

    public void displayMoonPhase(MoonPhase moonPhase) throws IOException {
        displayMoonPhaseView.setMoonPhase(moonPhase);
        displayMoonPhaseView.refresh();
        viewManager.getViews().add(displayMoonPhaseView, "display Moon Phase");
        viewManager.show("display Moon Phase");
    }

    public void back() {
        viewManager.show("home");
    }
}
