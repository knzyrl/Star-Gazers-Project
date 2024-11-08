package interface_adapter.display_star_chart;

public class DisplayStarChartController {
    private DisplayStarChartPresenter displayStarChartPresenter;

    public DisplayStarChartController(DisplayStarChartPresenter displayStarChartPresenter) {
        this.displayStarChartPresenter = displayStarChartPresenter;
    }

    public void execute() {
        displayStarChartPresenter.execute();
    }
}
