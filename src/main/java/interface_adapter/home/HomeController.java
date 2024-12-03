package interface_adapter.home;

/**
 * Class for Controller for Home.
 * Calls HomePresenter to switch to a particular view based on the user's input.
 */
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
