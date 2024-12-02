package view;

import interface_adapter.geocoding.GeocodingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NameGeocoderView extends JPanel{
    private GeocodingController geocodingController;
    private final String viewname = "Name Geocoder view";
    private final JTextField nameInput = new JTextField(20);
    private final JButton convert;
    private final JButton home;
    private final JComboBox addressNameSwitch = new JComboBox(new String[] {"Address", "Name"});

    public NameGeocoderView() {
        final JLabel title = new JLabel("Geocoder Converter");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel instruction = new JLabel("Enter the name of a monument, building, arena, etc. ");
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Textbox for user to enter info
        final LabelTextPanel nameInfo = new LabelTextPanel(new JLabel("Enter name"), nameInput);

        // Button to convert the address
        final JPanel convertPanel = new JPanel();
        convert = new JButton("Convert");
        convertPanel.add(convert);

        convert.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(convert)) {
                            String address = nameInput.getText();
                            try {
                                geocodingController.execute(address);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
        );

        // Button to go home
        final JPanel backPanel = new JPanel();
        home = new JButton("Home");
        backPanel.add(home);
        home.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(home)) {
                            try {
                                geocodingController.executeHome();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
        );
        final JPanel switchPanel = new JPanel();
        addressNameSwitch.setSelectedItem("Name");
        switchPanel.add(addressNameSwitch);
        addressNameSwitch.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(addressNameSwitch)) {
                            if (addressNameSwitch.getSelectedItem().equals("Address")) {
                                try {
                                    geocodingController.executeBack();
                                }
                                catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            else{
                                try {
                                    geocodingController.executeName();
                                }
                                catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }

                            }
                            addressNameSwitch.setSelectedItem("Name");
                        }
                    }
                }
        );


        this.add(title);
        this.add(instruction);
        this.add(nameInfo);
        this.add(switchPanel);
        this.add(convertPanel, BorderLayout.WEST);
        this.add(backPanel, BorderLayout.EAST);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void setGeoCodingController(GeocodingController geocodingController) {
        this.geocodingController = geocodingController;
    }

    public String getViewName() {
        return viewname;
    }
}
