package interface_adapter.fail;

/**
 * Class for Controller for the Fail View.
 * Calls the FailPresenter when the user clicks the Back button.
 */
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
