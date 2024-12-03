package interface_adapter.star_chart;

import use_case.star_chart.StarChartOutputBoundary;
import use_case.star_chart.StarChartOutputData;
import view.ViewManager;

/**
 * Class for the Presenter for the Star Chart use case.
 * Implements the StarChartOutputBoundary interface.
 * Receives out put data from StarChartInetractor.
 * Updates the view displayed to the user by communicating with the ViewManager.
 */

public class StarChartPresenter implements StarChartOutputBoundary {
    private ViewManager viewManager;

    public StarChartPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Override
    public void displayStarChart(StarChartOutputData starChartOutputData) {
        viewManager.displayStarChart(starChartOutputData);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.out.println("Star chart fail view triggered with error message: " + errorMessage);
        viewManager.showFailView(errorMessage);
    }

    @Override
    public void back() {
        viewManager.show("home");
    }
}
