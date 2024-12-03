package use_case.star_chart;

/**
 * Interface (Abstraction) between the Business Rules/Entities layers and Interface Adapter layer.
 * Implemented by StarChartPresenter.
 */
public interface StarChartOutputBoundary {
    /**
     * Method responsible for displaying the Star Chart to the user.
     * @param starChart received from StarChartInteractor.
     */
    void displayStarChart(StarChartOutputData starChart);

    /**
     * Method responsible for displaying the appropriate error message to the user.
     * @param errorMessage is the error message to be displayed.
     */
    void prepareFailView(String errorMessage);

    /**
     * Method responsible for reverting to HomeView.
     */
    void back();
}
