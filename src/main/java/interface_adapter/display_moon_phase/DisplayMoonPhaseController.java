package interface_adapter.display_moon_phase;

public class DisplayMoonPhaseController {
    private DisplayMoonPhasePresenter displayMoonPhasePresenter;

    public DisplayMoonPhaseController(DisplayMoonPhasePresenter displayMoonPhasePresenter) {
        this.displayMoonPhasePresenter = displayMoonPhasePresenter;
    }

    public void execute() {
        displayMoonPhasePresenter.execute();
    }
}
