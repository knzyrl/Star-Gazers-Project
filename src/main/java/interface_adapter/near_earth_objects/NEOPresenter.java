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

    public void back() {
        viewManager.show("home");
    }

    public void noDataFound() {
        System.out.println("No Near-Earth Objects data found.");
        viewManager.show("home");
    }
}
