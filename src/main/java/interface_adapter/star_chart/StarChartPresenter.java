package interface_adapter.star_chart;

import entity.StarChart;
import use_case.star_chart.StarChartOutputBoundary;
import view.DisplayStarChartView;
import view.ViewManager;

import java.io.IOException;

public class StarChartPresenter implements StarChartOutputBoundary {
    private ViewManager viewManager;
    private DisplayStarChartView displayStarChartView;

    public StarChartPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setDisplayStarChartView(DisplayStarChartView displayStarChartView) {
        this.displayStarChartView = displayStarChartView;
    }

    @Override
    public void displayStarChart(StarChart starChart) {
        displayStarChartView.setStarChart(starChart);
        displayStarChartView.refresh();
        viewManager.show("display star chart");
    }

    public void back() {
        viewManager.show("home");
    }
}
