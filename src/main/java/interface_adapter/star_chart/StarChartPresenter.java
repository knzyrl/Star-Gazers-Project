package interface_adapter.star_chart;

import entity.StarChart;
import use_case.star_chart.StarChartOutputBoundary;
import view.DisplayStarChartView;
import view.ViewManager;

/**
 * Presenter for the Star Chart use case.
 */
public class StarChartPresenter implements StarChartOutputBoundary {
    private ViewManager viewManager;
    private DisplayStarChartView displayStarChartView;

    public StarChartPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setDisplayStarChartView(DisplayStarChartView displayStarChartView) {
        this.displayStarChartView = displayStarChartView;
    }

    /**
     * Displays the Star Chart data by updating the view and transitioning to the Star Chart display view.
     *
     * @param starChart The {@link StarChart} object containing the Star Chart data.
     */
    @Override
    public void displayStarChart(StarChart starChart) {
        displayStarChartView.setStarChart(starChart);
        displayStarChartView.refresh();
        viewManager.show("display star chart");
    }

    /**
     * Navigates back to the home view.
     */
    public void back() {
        viewManager.show("home");
    }
}
