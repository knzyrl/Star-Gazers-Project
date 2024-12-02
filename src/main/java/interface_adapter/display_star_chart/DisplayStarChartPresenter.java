package interface_adapter.display_star_chart;

import view.ViewManager;

/**
 * Presenter for managing the display of star chart information.
 */
public class DisplayStarChartPresenter {
    private ViewManager viewManager;

    public DisplayStarChartPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Executes the display star chart use case.
     * This method switches the current view to the "home" view.
     */
    public void execute() {
        viewManager.show("home");
    }
}
