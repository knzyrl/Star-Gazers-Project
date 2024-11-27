package view;

import interface_adapter.APOD_date.APODController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class APODView extends JPanel {
    private String title;
    private String description;
    private String imgURL;
    private final String viewName = "APOD";
    private APODController displayAPODController;
    private JButton back;

    public void setAPODData(String title, String description, String imgURL) {
        this.title = title;
        this.description = description;
        this.imgURL = imgURL;
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setDisplayAPODController(APODController displayAPODController) {
        this.displayAPODController = displayAPODController;
    }

    public void refresh() throws IOException {
        this.removeAll();

        // Title label
        final JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Description label
        final JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Fetch image from URL and display it
        URL url = new URL(imgURL);
        BufferedImage image = ImageIO.read(url);
        JLabel apodImage = new JLabel(new ImageIcon(image));

        // Back button panel
        final JPanel backButtonPanel = new JPanel();
        back = new JButton("Back");
        backButtonPanel.add(back);
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            displayAPODController.execute();
                        }
                    }
                }
        );

        // Add components to the panel
        this.add(titleLabel);
        this.add(apodImage);
        this.add(new JScrollPane(descriptionArea)); // Add scroll pane for long descriptions
        this.add(backButtonPanel);

        // Set layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}