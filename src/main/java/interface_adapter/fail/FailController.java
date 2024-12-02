package interface_adapter.fail;

public class FailController {
    private FailPresenter failPresenter;

    public FailController(FailPresenter failPresenter) {
        this.failPresenter = failPresenter;
    }

    public void execute() {
        failPresenter.execute();
    }
}
