package interface_adapter.near_earth_objects;

import entity.NearEarthObjectEntity;
import use_case.near_earth_objects.NEOOutputBoundary;
import view.DisplayNEOView;
import view.ViewManager;

import java.util.List;

public class NEOPresenter implements NEOOutputBoundary {
    private final ViewManager viewManager;
    private DisplayNEOView displayNEOView;

    public NEOPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setDisplayNEOView(DisplayNEOView displayNEOView) {
        this.displayNEOView = displayNEOView;
    }

    @Override
    public void presentNEOData(List<NearEarthObjectEntity> neoEntities) {
        displayNEOView.displayNEOData(neoEntities);
        viewManager.getViews().add(displayNEOView, displayNEOView.getViewName());
        viewManager.show(displayNEOView.getViewName());
    }

    /**
     * Navigates back to the NEO input view.
     */
    public void back() {
        viewManager.show("NEO view"); // Navigate back to the input view for Near-Earth Objects
    }

    /**
     * Handles the case where no data is found for the requested dates.
     */
    public void noDataFound() {
        System.out.println("No Near-Earth Objects data found.");
        viewManager.show("NEO view"); // Navigate back to the input view
    }
}
