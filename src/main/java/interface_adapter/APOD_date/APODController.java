package interface_adapter.APOD_date;

import use_case.apod_date.APODInputBoundary;

public class APODController {
    private final APODInputBoundary interactor;

    public APODController(APODInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void fetchAPOD() {
        System.out.println("APODController: fetchAPOD called");
        interactor.fetchAPOD();
    }
}
