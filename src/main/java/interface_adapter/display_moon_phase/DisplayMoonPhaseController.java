package interface_adapter.display_moon_phase;

public class DisplayMoonPhaseController {
    private DisplayMoonPhasePresenter displayMoonPhasePresenter;

    public DisplayMoonPhaseController(DisplayMoonPhasePresenter displayMoonPhasePresenter) {
        this.displayMoonPhasePresenter = displayMoonPhasePresenter;
    }

    /**
     * Calls the presenter to execute the return to home.
     */
    public void execute() {
        displayMoonPhasePresenter.execute();
    }
}
