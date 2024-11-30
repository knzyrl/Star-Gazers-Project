package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import interface_adapter.geocoding.GeocodingController;
import interface_adapter.geocoding.GeocodingPresenter;

/**
 * Display when the address cannot be found.
 */
public class NoAddressFoundView extends JPanel {

    private final JButton retry;
    private GeocodingController geocodingController;
    private final String viewname = "No address found";

    public NoAddressFoundView() {
        final JLabel title = new JLabel("No Address Found");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button to retry the use case
        final JPanel retryPanel = new JPanel();
        retry = new JButton("Retry");
        retryPanel.add(retry);

        retry.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(retry)) {
                            try {
                                geocodingController.executeBack();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
        );
        this.add(title);
        this.add(retryPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void setGeocodingController(GeocodingController geocodingController) {
        this.geocodingController = geocodingController;
    }

    public String getViewName() {
        return viewname;
    }


}
