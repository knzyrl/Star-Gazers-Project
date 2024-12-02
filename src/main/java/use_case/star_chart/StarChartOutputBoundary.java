package use_case.star_chart;

import entity.StarChart;

/**
 * Output boundary interface for the Star Chart use case.
 */
public interface StarChartOutputBoundary {

    /**
     * Displays the generated Star Chart to the user.
     *
     * @param starChart The {@link StarChart} object containing the chart data to be displayed.
     */
    void displayStarChart(StarChart starChart);

    /**
     * Navigates back to the previous view or home view.
     */
    void back();
}
