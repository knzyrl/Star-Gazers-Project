package interface_adapter.home;

/**
 * Controller for the home view.
 */
public class HomeController {
    private final HomePresenter homePresenter;

    public HomeController(HomePresenter homePresenter) {
        this.homePresenter = homePresenter;
    }

    /**
     * Executes the navigation logic by delegating the request to the home presenter.
     *
     * @param viewName The name of the view to navigate to.
     */
    public void execute(String viewName) {
        homePresenter.execute(viewName);
    }
}
