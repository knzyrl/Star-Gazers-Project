//package view;
//
//import entity.Location;
//import entity.StarChart;
//import interface_adapter.display_star_chart.DisplayStarChartController;
//import interface_adapter.geocoding.DisplayGeocodingController;
//import interface_adapter.geocoding.GeocodingController;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
//public class DisplayGeocoderView extends JPanel {
//    private final String viewName = "display geocoded information";
//    private Location location;
//    private GeocodingController geocodingController;
//    private JButton back;
//    private DisplayGeocodingController displayGeocodingController;
//
//    public void setLocation(Location location) {
//        this.location = location;
//    }
//
//    public void setDisplayStarChartController(GeocodingController geocodingController) {
//        this.geocodingController = geocodingController;
//    }
//
//    public void displayLocation() {
//        this.removeAll();
//
//        JLabel longtitude = new JLabel("Longitude for " +location.getLongtitude());
//        JLabel lattitude = new JLabel("Latitude for " +location.getLatitude());
//
//        JPanel backPanel = new JPanel();
//        back = new JButton("Back");
//        backPanel.add(back);
//
//        back.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource().equals(back)) {
//                    try {
//                        geocodingController.executeBack();
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                }
//            }
//        });
//
//
//        this.add(longtitude);
//        this.add(lattitude);
//        this.add(backPanel);
//    }
//
//    public String getViewName() {
//        return viewName;
//    }
//}
//
