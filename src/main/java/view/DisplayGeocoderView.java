package view;

import entity.Location;
import entity.StarChart;
import interface_adapter.display_star_chart.DisplayStarChartController;
import interface_adapter.geocoding.DisplayGeocodingController;
import interface_adapter.geocoding.GeocodingController;
import use_case.geocoding.GeocodingOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Display for the longtitude and latitude of the address.
 */
public class DisplayGeocoderView extends JPanel {
    private final String viewName = "display geocoded information";
    private GeocodingOutputData geocodingOutputData;
    private GeocodingController geocodingController;
    private JButton home;
    private DisplayGeocodingController displayGeocodingController;

    public void setLocation(GeocodingOutputData geocodingOutputData) {
        this.geocodingOutputData = geocodingOutputData;
    }

    public void setDisplayStarChartController(GeocodingController geocodingController) {
        this.geocodingController = geocodingController;
    }

    public void displayLocation() {
        this.removeAll();

        JLabel title = new JLabel(geocodingOutputData.getAddress());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label for the lat and long
        JLabel latitude = new JLabel("The latitude for the address is " +geocodingOutputData.getLatitude());
        JLabel longtitude = new JLabel("The longtitude for the address is " +geocodingOutputData.getLongitude());

        latitude.setAlignmentX(Component.CENTER_ALIGNMENT);
        longtitude.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Home button panel
        JPanel backPanel = new JPanel();
        home = new JButton("Home");
        backPanel.add(home);

        home.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(home)) {
                    try {
                        geocodingController.executeHome();
                    } catch (IOException ex) {
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

