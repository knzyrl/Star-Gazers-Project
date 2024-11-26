package interface_adapter.star_chart;

import entity.StarChart;
import view.DisplayStarChartView;
import view.ViewManager;

import java.io.IOException;

public class StarChartPresenter {
    private ViewManager viewManager;
    private DisplayStarChartView displayStarChartView;

    public StarChartPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setDisplayStarChartView(DisplayStarChartView displayStarChartView) {
        this.displayStarChartView = displayStarChartView;
    }

    public void displayStarChart(StarChart starChart) throws IOException {
        displayStarChartView.setStarChart(starChart);
        displayStarChartView.refresh();
        viewManager.show("display star chart");
    }

    public void back() {
        viewManager.show("home");
    }
}
