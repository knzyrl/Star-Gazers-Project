package interface_adapter.display_star_chart;

/**
 * Controller for managing user actions related to displaying star charts.
 */
public class DisplayStarChartController {
    private DisplayStarChartPresenter displayStarChartPresenter;

    /**
     * Constructs a {@code DisplayStarChartController} with the specified presenter.
     *
     * @param displayStarChartPresenter The presenter responsible for star chart display logic.
     */
    public DisplayStarChartController(DisplayStarChartPresenter displayStarChartPresenter) {
        this.displayStarChartPresenter = displayStarChartPresenter;
    }

    /**
     * Executes the display star chart use case by delegating the request to the presenter.
     */
    public void execute() {
        displayStarChartPresenter.execute();
    }
}
