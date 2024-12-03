package interface_adapter.display_star_chart;

import view.ViewManager;

/**
 *
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
