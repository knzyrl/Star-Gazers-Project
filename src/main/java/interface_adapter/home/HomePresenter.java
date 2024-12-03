package interface_adapter.home;

import view.ViewManager;

public class HomePresenter {
    private final ViewManager viewManager;

    public HomePresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Calls the ViewManager to switch to the specified view.
     * @param viewName name of view to switch to
     */
    public void execute(String viewName) {
        viewManager.show(viewName);
    }
}
