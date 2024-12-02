package interface_adapter.home;

import view.ViewManager;

/**
 * Presenter for the home view.
 */
public class HomePresenter {
    private final ViewManager viewManager;

    public HomePresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Navigates to the specified view.
     *
     * @param viewName The name of the view to navigate to.
     */
    public void execute(String viewName) {
        viewManager.show(viewName);
    }
}
