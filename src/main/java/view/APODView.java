package view;

import interface_adapter.apod_date.ApodController; //rename

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class APODView extends JPanel {
    private final JLabel titleLabel = new JLabel();
    private final JTextArea descriptionArea = new JTextArea();
    private final JLabel imageLabel = new JLabel();
    private final JButton fetchButton = new JButton("Fetch AstronomyPicture");
    private final JButton backButton = new JButton("Back");
    private final JButton fetchByDateButton = new JButton("Fetch by Date");
    private final JTextField dateInputField = new JTextField("YYYY-MM-DD", 10);
    private final JPanel persistentButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Persistent Buttons
    private JButton playButton = null;
    private String currentImageUrl;

    public APODView() {
        setLayout(new BorderLayout());

        // Title label
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Description area
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        // Image display
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.EAST);

        // Persistent Buttons
        fetchButton.setPreferredSize(new Dimension(200, 30));
        fetchByDateButton.setPreferredSize(new Dimension(150, 30));
        backButton.setPreferredSize(new Dimension(100, 30));
        dateInputField.setPreferredSize(new Dimension(100, 30));

        persistentButtonPanel.add(fetchButton);
        persistentButtonPanel.add(dateInputField);
        persistentButtonPanel.add(fetchByDateButton);
        persistentButtonPanel.add(backButton);

        add(persistentButtonPanel, BorderLayout.SOUTH);
    }

    public String getViewName() {
        return "apod";
    }

    public void setController(ApodController controller) {
        // Fetch by Date Button Action
        fetchByDateButton.addActionListener(e -> {
            String date = dateInputField.getText();
            System.out.println("Fetching AstronomyPicture for date: " + date);
            controller.fetchAPODByDate(date);
        });

        // Fetch Button Action
        fetchButton.addActionListener(e -> {
            System.out.println("Fetch AstronomyPicture button clicked!");
            controller.fetchAPOD();
        });

        // Back Button Action
        backButton.addActionListener(e -> controller.navigateToHome());
    }

    public void displayAPOD(String title, String description, String mediaType, String url, String thumbnailUrl) {
        SwingUtilities.invokeLater(() -> {
            titleLabel.setText(title);
            descriptionArea.setText(description);

            // Store the current URL for saving or playing
            currentImageUrl = mediaType.equals("image") ? url : thumbnailUrl;

            try {
                if (mediaType.equals("image")) {
                    // Display the image
                    ImageIcon image = new ImageIcon(new URL(url));
                    imageLabel.setIcon(image);

                    // Remove the Play Video button if it exists
                    if (playButton != null) {
                        persistentButtonPanel.remove(playButton);
                        playButton = null;
                    }
                } else if (mediaType.equals("video")) {
                    // Display the video thumbnail
                    ImageIcon thumbnail = new ImageIcon(new URL(thumbnailUrl));
                    imageLabel.setIcon(thumbnail);

                    // Add a "Play Video" button for video playback
                    if (playButton == null) {
                        playButton = new JButton("Play Video");
                        playButton.setPreferredSize(new Dimension(150, 30)); // Match other button sizes
                        playButton.addActionListener(e -> {
                            try {
                                Desktop.getDesktop().browse(new URL(url).toURI());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Failed to open video.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                        persistentButtonPanel.add(playButton);
                    }
                }

                // Refresh the persistent button panel
                persistentButtonPanel.revalidate();
                persistentButtonPanel.repaint();

                revalidate(); // Refresh the overall layout
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
                imageLabel.setText("Failed to load media.");
            }
        });
    }
}
