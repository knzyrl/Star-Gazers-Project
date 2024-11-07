package interface_adapter.home;

import view.ViewManager;

public class HomePresenter {
    private final ViewManager viewManager;

    public HomePresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void execute(String viewName) {
        viewManager.show(viewName);
    }
}
