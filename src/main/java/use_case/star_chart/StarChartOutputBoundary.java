package use_case.star_chart;

import entity.StarChart;

public interface StarChartOutputBoundary {
    void displayStarChart(StarChart starChart);

    void back();
}
