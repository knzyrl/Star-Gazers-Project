package interface_adapter.display_moon_phase;

/**
 * Controller for managing user actions related to displaying moon phases.
 */
public class DisplayMoonPhaseController {
    private DisplayMoonPhasePresenter displayMoonPhasePresenter;

    /**
     * Constructs a {@code DisplayMoonPhaseController} with the specified presenter.
     *
     * @param displayMoonPhasePresenter The presenter responsible for moon phase display logic.
     */
    public DisplayMoonPhaseController(DisplayMoonPhasePresenter displayMoonPhasePresenter) {
        this.displayMoonPhasePresenter = displayMoonPhasePresenter;
    }

    /**
     * Executes the display moon phase use case by delegating the request to the presenter.
     */
    public void execute() {
        displayMoonPhasePresenter.execute();
    }
}
