package view;

import interface_adapter.geocoding.GeocodingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GeocoderView extends JPanel {
    private GeocodingController geocodingController;
    private final String viewname = "Geocoder view";
    private final JTextField addressInput = new JTextField(20);
    private final JButton convert;
    //private final JButton back;

    public GeocoderView() {
        final JLabel title = new JLabel("Geocoder Converter");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel addressInfo = new LabelTextPanel(new JLabel("Enter address"), addressInput);

        final JPanel convertPanel = new JPanel();
        convert = new JButton("Convert");
        convertPanel.add(convert);

        convert.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(convert)) {
                            String address = addressInput.getText();
                            try {
                                geocodingController.execute(address);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
        );

        this.add(title, BorderLayout.NORTH);
        this.add(addressInfo, BorderLayout.CENTER);
        this.add(convertPanel, BorderLayout.SOUTH);
    }

    public void setGeoCodingController(GeocodingController geocodingController) {
        this.geocodingController = geocodingController;
    }

    public String getViewName() {
        return viewname;
    }
}




