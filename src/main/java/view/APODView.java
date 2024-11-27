package view;

import interface_adapter.APOD_date.APODController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class APODView extends JPanel {
    private final JLabel titleLabel = new JLabel();
    private final JTextArea descriptionArea = new JTextArea();
    private final JLabel imageLabel = new JLabel();
    private final JButton fetchButton = new JButton("Fetch APOD");

    public APODView() {
        setLayout(new BorderLayout());

        // Title label
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Description area
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        // Image display
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.EAST);

        // Fetch button (action tied to controller)
        add(fetchButton, BorderLayout.SOUTH);
    }

    public String getViewName() {
        return "apod";
    }

    public void setController(APODController controller) {
        fetchButton.addActionListener(e -> {
            System.out.println("Fetch APOD button clicked!");
            controller.fetchAPOD();
        });

    }

    public void displayAPOD(String title, String description, String imageUrl) {
        SwingUtilities.invokeLater(() -> {
            titleLabel.setText(title);
            descriptionArea.setText(description);

            try {
                ImageIcon image = new ImageIcon(new URL(imageUrl)); // Load image from URL
                imageLabel.setIcon(image);
            } catch (Exception e) {
                e.printStackTrace();
                imageLabel.setText("Failed to load image.");
            }
        });
    }

}
