package interface_adapter.display_star_chart;

import view.ViewManager;

public class DisplayStarChartPresenter {
    private ViewManager viewManager;

    public DisplayStarChartPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void execute() {
        viewManager.show("home");
    }
}
