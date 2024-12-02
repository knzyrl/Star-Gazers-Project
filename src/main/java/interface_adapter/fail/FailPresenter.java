package interface_adapter.fail;

import view.ViewManager;

public class FailPresenter {
    private ViewManager viewManager;

    public FailPresenter(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void execute() {
        viewManager.show("home");
    }
}
