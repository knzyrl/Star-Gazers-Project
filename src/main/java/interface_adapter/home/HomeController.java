package interface_adapter.home;

public class HomeController {
    private final HomePresenter homePresenter;

    public HomeController(HomePresenter homePresenter) {
        this.homePresenter = homePresenter;
    }

    public void execute(String viewName) {
        homePresenter.execute(viewName);
    }
}
