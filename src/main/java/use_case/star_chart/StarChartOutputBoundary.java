package use_case.star_chart;

public interface StarChartOutputBoundary {
    void displayStarChart(StarChartOutputData starChart);

    void prepareFailView(String errorMessage);

    void back();
}
