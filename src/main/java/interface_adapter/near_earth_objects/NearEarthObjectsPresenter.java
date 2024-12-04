package interface_adapter.near_earth_objects;

import java.util.List;

import interface_adapter.ViewManager;
import use_case.near_earth_objects.NearEarthObjectsOutputBoundary;
import use_case.near_earth_objects.NearEarthObjectsOutputData;
import view.DisplayNearEarthObjectsView;

/**
 * Class for the Presenter for the Near Earth Objects use case.
 * Implements the NearEarthObjectsOutputBoundaryInterface.
 * Receives output data to be presented from NearEarthObjectsInteractor.
 * Updates the view displayed to the user by communicating with the ViewManager.
 */
public class NearEarthObjectsPresenter implements NearEarthObjectsOutputBoundary {
    private final ViewManager viewManager;
    private DisplayNearEarthObjectsView displayNearEarthObjectsView;

    public NearEarthObjectsPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setDisplayNeoView(DisplayNearEarthObjectsView view) {
        this.displayNearEarthObjectsView = view;
    }

    @Override
    public void presentNearEarthObjectsData(List<NearEarthObjectsOutputData> neoOutputData) {
        viewManager.displayNearEarthObjectsData(neoOutputData);
        viewManager.getViews().add(displayNearEarthObjectsView, displayNearEarthObjectsView.getViewName());
        viewManager.show(displayNearEarthObjectsView.getViewName());
    }

    /**
     * Navigates back to the NEO input view.
     */
    public void back() {
        viewManager.show("home");
    }

    /**
     * Handles the case where no data is found for the requested dates.
     */
    public void noDataFound() {
        System.out.println("No Near-Earth Objects data found.");
        viewManager.show("NEO view");
    }
}
