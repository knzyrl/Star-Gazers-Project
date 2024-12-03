package interface_adapter.home;

import view.ViewManager;

/**
 * Class for the Presenter for Home.
 * Responsible for displaying a particular view to the user based on their input by communicating with the ViewManager.
 */
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
