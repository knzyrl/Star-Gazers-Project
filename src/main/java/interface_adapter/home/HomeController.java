package interface_adapter.home;

public class HomeController {
    private final HomePresenter homePresenter;

    public HomeController(HomePresenter homePresenter) {
        this.homePresenter = homePresenter;
    }

    /**
     * Calls the presenter to switch to a given view.
     * @param viewName name of view to switch to
     */
    public void execute(String viewName) {
        homePresenter.execute(viewName);
    }
}
