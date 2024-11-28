package interface_adapter.APOD_date;

import use_case.apod_date.APODOutputBoundary;
import use_case.apod_date.APODOutputData;
import view.APODView;

import javax.swing.*;

public class APODPresenter implements APODOutputBoundary {
    private final APODView view;

    public APODPresenter(APODView view) {
        this.view = view;
    }

    @Override
    public void presentAPOD(APODOutputData outputData) {
        // Print the URL for debugging
        System.out.println("APOD URL: " + outputData.getUrl());

        SwingUtilities.invokeLater(() -> {
            String title = outputData.getTitle();
            String description = outputData.getDescription();
            String mediaType = outputData.getMediaType();
            String url = outputData.getUrl();
            String thumbnailUrl = outputData.getThumbnailUrl();

            view.displayAPOD(title, description, mediaType, url, thumbnailUrl);
        });
    }
}
