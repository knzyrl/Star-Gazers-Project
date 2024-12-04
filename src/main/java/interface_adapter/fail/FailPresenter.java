package interface_adapter.fail;

import interface_adapter.ViewManager;

/**
 * Class for Presenter for the Fail View.
 * Responsible for presenting the error display.
 * Allows the user to revert to the home screen.
 */
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
