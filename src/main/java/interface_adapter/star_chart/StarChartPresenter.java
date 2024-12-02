package interface_adapter.star_chart;

import use_case.star_chart.StarChartOutputBoundary;
import use_case.star_chart.StarChartOutputData;
import view.DisplayStarChartView;
import view.ViewManager;

public class StarChartPresenter implements StarChartOutputBoundary {
    private ViewManager viewManager;

    public StarChartPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Override
    public void displayStarChart(StarChartOutputData starChartOutputData) {
//        displayStarChartView.refresh(starChartOutputData);
//        viewManager.show("display star chart");
        viewManager.displayStarChart(starChartOutputData);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.out.println("Star chart fail view triggered with error message: " + errorMessage);
    }

    public void back() {
        viewManager.show("home");
    }
}
