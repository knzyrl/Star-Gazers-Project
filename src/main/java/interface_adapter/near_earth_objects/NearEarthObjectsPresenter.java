package interface_adapter.near_earth_objects;

import java.util.List;

import entity.NearEarthObjectEntity;
import use_case.near_earth_objects.NearEarthObjectsOutputBoundary;
import view.DisplayNearEarthObjectsView;
import view.ViewManager;

public class NearEarthObjectsPresenter implements NearEarthObjectsOutputBoundary {
    private final ViewManager viewManager;
    private DisplayNearEarthObjectsView displayNearEarthObjectsView;

    public NearEarthObjectsPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setDisplayNeoView(DisplayNearEarthObjectsView displayNearEarthObjectsView) {
        this.displayNearEarthObjectsView = displayNearEarthObjectsView;
    }

    @Override
    public void presentNearEarthObjectsData(List<NearEarthObjectEntity> neoEntities) {
        displayNearEarthObjectsView.displayNearEarthObjectsData(neoEntities);
        viewManager.getViews().add(displayNearEarthObjectsView, displayNearEarthObjectsView.getViewName());
        viewManager.show(displayNearEarthObjectsView.getViewName());
    }

    /**
     * Navigates back to the NEO input view.
     */
    public void back() {
        viewManager.show("NEO view");
    }

    /**
     * Handles the case where no data is found for the requested dates.
     */
    public void noDataFound() {
        System.out.println("No Near-Earth Objects data found.");
        viewManager.show("NEO view");
    }
}
