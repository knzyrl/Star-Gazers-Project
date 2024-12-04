package use_case.star_chart;

/**
 * Interface (Abstraction) between the Interface Adapter and Business Rules/Entities layers.
 * Implemented by StarChartInteractor.
 */
public interface StarChartInputBoundary {
    /**
     * Method to execute the use case.
     * @param starChartInputData received from the Controller.
     */
    void execute(StarChartInputData starChartInputData);

    /**
     * Method to revert to HomeView.
     */
    void execute();
}
