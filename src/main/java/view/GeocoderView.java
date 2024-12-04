package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.geocoding.GeocodingController;

/**
 * View for the geocoder use case.
 */
public class GeocoderView extends JPanel {
    private GeocodingController geocodingController;
    private final String viewname = "Geocoder view";
    private final String addressText = "Address";
    private final JTextField addressInput = new JTextField(20);
    private final JButton convert;
    private final JButton home;
    private final JComboBox addressNameSwitch = new JComboBox(new String[] {addressText, "Name"});

    public GeocoderView() {
        final JLabel title = new JLabel("Geocoder Converter");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Textbox for user to enter info
        final LabelTextPanel addressInfo = new LabelTextPanel(new JLabel("Enter address"), addressInput);

        // Button to convert the address
        final JPanel convertPanel = new JPanel();
        convert = new JButton("Convert");
        convertPanel.add(convert);

        convert.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(convert)) {
                            final String address = addressInput.getText();
                            try {
                                geocodingController.execute(address);
                            }
                            catch (IOException ex) {
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
                            }
                            catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
        );
        final JPanel switchPanel = new JPanel();
        switchPanel.add(addressNameSwitch);
        addressNameSwitch.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(addressNameSwitch)) {
                            if (addressNameSwitch.getSelectedItem().equals(addressText)) {
                                try {
                                    geocodingController.executeBack();
                                }
                                catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            else {
                                try {
                                    geocodingController.executeName();

                                }
                                catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }

                            }
                            addressNameSwitch.setSelectedItem(addressText);
                        }
                    }
                }
        );

        this.add(title);
        this.add(addressInfo);
        this.add(switchPanel);
        this.add(convertPanel, BorderLayout.WEST);
        this.add(backPanel, BorderLayout.EAST);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void setGeoCodingController(GeocodingController controller) {
        this.geocodingController = controller;
    }

    public String getViewName() {
        return viewname;
    }
}
