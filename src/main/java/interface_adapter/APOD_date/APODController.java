package interface_adapter.APOD_date;

import interface_adapter.APOD_date.APODPresenter;
import use_case.apod_date.APODInteractor;

public class APODController {
    private APODInteractor displayAPODInteractor;

    public APODController(APODInteractor displayAPODInteractor) {
        this.displayAPODInteractor = displayAPODInteractor;
    }

    public void execute(){
        displayAPODInteractor.execute();
    }
}
