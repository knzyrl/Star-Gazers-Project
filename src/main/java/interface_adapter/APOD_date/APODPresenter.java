package interface_adapter.APOD_date;

import use_case.apod_date.APODOutputBoundary;
import use_case.apod_date.APODOutputData;
import view.APODView;

public class APODPresenter implements APODOutputBoundary {
    private final APODView view;

    public APODPresenter(APODView view) {
        this.view = view;
    }

    @Override
    public void presentAPOD(APODOutputData outputData) {
        // Print the URL for debugging
        System.out.println("APOD URL: " + outputData.getImageUrl());

        // Update the view
        view.displayAPOD(outputData.getTitle(), outputData.getDescription(), outputData.getImageUrl());
    }
}
