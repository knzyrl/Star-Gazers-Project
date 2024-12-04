package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.geocoding.GeocodingController;
import use_case.geocoding.GeocodingOutputData;

/**
 * Display for the longtitude and latitude of the address.
 */
public class DisplayGeocoderView extends JPanel {
    private final String viewName = "display geocoded information";
    private GeocodingController geocodingController;
    private JButton home;

    public void setDisplayGeocodingController(GeocodingController geocodingController) {
        this.geocodingController = geocodingController;
    }

    /**
     * Method to display the longitude and latitude of the location.
     * @param geocodingOutputData output data for geocoding use case
     */
    public void displayLocation(GeocodingOutputData geocodingOutputData) {
        this.removeAll();

        final JLabel title = new JLabel(geocodingOutputData.getAddress());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label for the lat and long
        final JLabel latitude = new JLabel("The latitude for the address is " + geocodingOutputData.getLatitude());
        final JLabel longtitude = new JLabel("The longitude for the address is " + geocodingOutputData.getLongitude());

        latitude.setAlignmentX(Component.CENTER_ALIGNMENT);
        longtitude.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Home button panel
        final JPanel backPanel = new JPanel();
        home = new JButton("Home");
        backPanel.add(home);

        home.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent action) {
                if (action.getSource().equals(home)) {
                    try {
                        geocodingController.executeHome();
                    }
                    catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        this.add(title);
        this.add(latitude);
        this.add(longtitude);
        this.add(backPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public String getViewName() {
        return viewName;
    }
}
