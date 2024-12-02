package interface_adapter.near_earth_objects;

import java.util.List;

import entity.NearEarthObjectEntity;
import use_case.near_earth_objects.NearEarthObjectsOutputBoundary;
import view.DisplayNEOView;
import view.ViewManager;

/**
 * Presenter for the Near-Earth Objects (NEO) use case.
 */
public class NearEarthObjectsPresenter implements NearEarthObjectsOutputBoundary {
    private final ViewManager viewManager;
    private DisplayNEOView displayNearEarthObjectsView;

    public NearEarthObjectsPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setDisplayNearEarthObjectsView(DisplayNEOView displayNearEarthObjectsView) {
        this.displayNearEarthObjectsView = displayNearEarthObjectsView;
    }

    /**
     * Presents the Near-Earth Objects data to the user by updating the view and transitioning to the display view.
     *
     * @param neoEntities A list of {@link NearEarthObjectEntity} objects containing NEO data.
     */
    @Override
    public void presentNearEarthObjectData(List<NearEarthObjectEntity> neoEntities) {
        displayNearEarthObjectsView.displayNEOData(neoEntities);
        viewManager.getViews().add(displayNearEarthObjectsView, displayNearEarthObjectsView.getViewName());
        viewManager.show(displayNearEarthObjectsView.getViewName());
    }

    /**
     * Navigates back to the home view.
     */
    public void back() {
        viewManager.show("home");
    }

    /**
     * Handles the scenario where no Near-Earth Objects data is found and navigates back to the home view.
     */
    public void noDataFound() {
        System.out.println("No Near-Earth Objects data found.");
        viewManager.show("home");
    }
}
