package interface_adapter.display_star_chart;

import view.ViewManager;

/**
 * Class for DisplayStarChartPresenter.
 * Responsible for presenting the display of the use case.
 * Allows the user to revert to the home screen.
 */

public class DisplayStarChartPresenter {
    private ViewManager viewManager;

    public DisplayStarChartPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Calls the ViewManager to return to the home view.
     */
    public void execute() {
        viewManager.show("home");
    }
}
