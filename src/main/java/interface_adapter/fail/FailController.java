package interface_adapter.fail;

public class FailController {
    private FailPresenter failPresenter;

    public FailController(FailPresenter failPresenter) {
        this.failPresenter = failPresenter;
    }

    /**
     * Calls the presenter to execute the return to home.
     */
    public void execute() {
        failPresenter.execute();
    }
}
