package view;

import interface_adapter.home.HomeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JPanel {
    private String viewName = "home";
    private HomeController homeController;
    private JButton starChart;
    private JButton moonPhase;
    private JButton events;
    private JButton neo;
    private JButton apod;
    private JButton geocoder;

    public HomeView() {
        final JLabel title = new JLabel("Home Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Star Chart Button
//        final JPanel starChartButtonPanel = new JPanel();
//        starChart = new JButton("Generate Star Chart");
//        starChartButtonPanel.add(starChart);
//        starChart.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(starChart)) {
//                            System.out.println("star chart pressed");
//                            homeController.execute("star chart");
//                        }
//                    }
//                }
//        );

//        final JPanel moonPhaseButtonPanel = new JPanel();
//        moonPhase = new JButton("Generate Moon Phase");
//        moonPhaseButtonPanel.add(moonPhase);
//        moonPhase.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(moonPhase)) {
//                            System.out.println("moon phase pressed");
//                            homeController.execute("moon phase");
//                        }
//                    }
//                }
//        );

        // Events Button
//        final JPanel eventsButtonPanel = new JPanel();
//        events = new JButton("Show Events");
//        eventsButtonPanel.add(events);
//        events.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(events)) {
//                            System.out.println("events pressed");
//                            homeController.execute("events");
//                        }
//                    }
//                }
//        );

        // APOD Button
//        final JPanel apodButtonPanel = new JPanel();
//        apod = new JButton("View Astronomy Picture of the Day");
//        apodButtonPanel.add(apod);
//        apod.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(apod)) {
//                            System.out.println("apod pressed");
//                            homeController.execute("apod");
//                        }
//                    }
//                }
//        );

        // Add Components
//        this.add(title);
//        this.add(starChartButtonPanel);
//        this.add(eventsButtonPanel);


//        final JPanel geocoderButtonPanel = new JPanel();
//        geocoder = new JButton("Access Geocoder");
//        geocoderButtonPanel.add(geocoder);
//        geocoder.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(geocoder)) {
//                            System.out.println("Geocoder pressed");
//                            homeController.execute("Geocoder view");
//                        }
//                    }
//                }
//        );



        this.add(title);
        this.add(createStarChartButtonPanel());
        this.add(createMoonPhaseButtonPanel());
        this.add(createEventsButtonPanel());
        this.add(createNEOButtonPanel());
        this.add(createAPODButtonPanel());
        this.add(createGeocoderButtonPanel());

        // Layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    private JPanel createStarChartButtonPanel() {
        final JPanel result = new JPanel();
        starChart = new JButton("Generate Star Chart");
        result.add(starChart);
        starChart.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(starChart)) {
                            System.out.println("star chart pressed");
                            homeController.execute("star chart");
                        }
                    }
                }
        );
        return result;
    }

    private JPanel createMoonPhaseButtonPanel() {
        final JPanel result = new JPanel();
        moonPhase = new JButton("Generate Moon Phase");
        result.add(moonPhase);
        moonPhase.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(moonPhase)) {
                            System.out.println("moon phase pressed");
                            homeController.execute("moon phase");
                        }
                    }
                }
        );
        return result;
    }

    private JPanel createEventsButtonPanel() {
        final JPanel result = new JPanel();
        events = new JButton("Show Events");
        result.add(events);
        events.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(events)) {
                            System.out.println("events pressed");
                            homeController.execute("events");
                        }
                    }
                }
        );
        return result;
    }

    private JPanel createNEOButtonPanel() {
        final JPanel result = new JPanel();
        neo = new JButton("Show Near-Earth Objects");
        result.add(neo);
        neo.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(neo)) {
                            System.out.println("neo pressed");
                            homeController.execute("NEO view");
                        }
                    }
                }
        );
        return result;
    }

    private JPanel createAPODButtonPanel() {
        final JPanel result = new JPanel();
        apod = new JButton("View Astronomy Picture of the Day");
        result.add(apod);
        apod.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(apod)) {
                            System.out.println("apod pressed");
                            homeController.execute("apod");
                        }
                    }
                }
        );
        return result;
    }

    private JPanel createGeocoderButtonPanel() {
        final JPanel result = new JPanel();
        geocoder = new JButton("Access Geocoder");
        result.add(geocoder);
        geocoder.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(geocoder)) {
                            System.out.println("Geocoder pressed");
                            homeController.execute("Geocoder view");
                        }
                    }
                }
        );
        return result;
    }

}
