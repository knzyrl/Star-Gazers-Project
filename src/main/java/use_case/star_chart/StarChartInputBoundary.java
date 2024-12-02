package use_case.star_chart;

/**
 * Input boundary for the Star Chart use case.
 */
public interface StarChartInputBoundary {

    /**
     * Executes the Star Chart use case with the provided input data.
     *
     * @param starChartInputData The input data required for generating the star chart,
     *                           encapsulated in a {@link StarChartInputData} object.
     */
    void execute(StarChartInputData starChartInputData);
}
