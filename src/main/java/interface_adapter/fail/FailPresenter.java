package interface_adapter.fail;

import view.ViewManager;

public class FailPresenter {
    private ViewManager viewManager;

    public FailPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Calls the ViewManager to return to the home view.
     */
    public void execute() {
        viewManager.show("home");
    }
}
