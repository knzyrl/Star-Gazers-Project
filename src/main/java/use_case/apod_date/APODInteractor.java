package use_case.apod_date;

import data_access.APODdateAPIDataAccessObject;
import org.json.JSONObject;
import view.ViewManager;

public class APODInteractor implements APODInputBoundary {
    private final APODOutputBoundary outputBoundary;
    private final APODdateAPIDataAccessObject dataAccessObject;
    private final ViewManager viewManager;

    public APODInteractor(APODOutputBoundary outputBoundary, APODdateAPIDataAccessObject dataAccessObject, ViewManager viewManager) {
        this.outputBoundary = outputBoundary;
        this.dataAccessObject = dataAccessObject;
        this.viewManager = viewManager;
    }

    @Override
    public void fetchAPOD() {
        String jsonResponse = dataAccessObject.fetchAPOD();
        processResponse(jsonResponse);
    }

    public void fetchAPODByDate(String date) {
        String jsonResponse = dataAccessObject.fetchAPODByDate(date);
        processResponse(jsonResponse);
    }

    public void goBackToHome() {
        viewManager.show("home");
    }

    private void processResponse(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        String title = json.optString("title", "No Title");
        String description = json.optString("explanation", "No Description");
        String imageUrl = json.optString("url", "");

        APODOutputData outputData = new APODOutputData(title, description, imageUrl);
        outputBoundary.presentAPOD(outputData);
    }
}
