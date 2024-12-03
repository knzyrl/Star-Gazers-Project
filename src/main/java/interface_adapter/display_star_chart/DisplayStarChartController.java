package interface_adapter.display_star_chart;

/**
 * Class for the controller for Display Star Chart.
 * Calls the DisplayStarChartPresenter.
 */

public class DisplayStarChartController {
    private DisplayStarChartPresenter displayStarChartPresenter;

    public DisplayStarChartController(DisplayStarChartPresenter displayStarChartPresenter) {
        this.displayStarChartPresenter = displayStarChartPresenter;
    }

    /**
     * Calls the presenter to execute the return to home.
     */
    public void execute() {
        displayStarChartPresenter.execute();
    }
}
