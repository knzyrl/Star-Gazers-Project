package view;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

// rename
import interface_adapter.apod_date.ApodController;

/**
 * Class for the View displayed to the user when they select the Astronomical Picture of the Day use case from the
 * Home View.
 */
public class APODView extends JPanel {
    private static final int FETCH_BUTTON_WIDTH = 200;
    private static final int FETCH_BUTTON_HEIGHT = 30;
    private static final int FETCH_BY_DATE_BUTTON_WIDTH = 150;
    private static final int FETCH_BY_DATE_BUTTON_HEIGHT = 30;
    private static final int BACK_BUTTON_WIDTH = 100;
    private static final int BACK_BUTTON_HEIGHT = 30;
    private static final int DATE_INPUT_FIELD_WIDTH = 100;
    private static final int DATE_INPUT_FIELD_HEIGHT = 30;
    private static final int PLAY_BUTTON_WIDTH = 150;
    private static final int PLAY_BUTTON_HEIGHT = 30;
    private static final int TITLE_LABEL_SIZE = 18;
    private final JLabel titleLabel = new JLabel();
    private final JTextArea descriptionArea = new JTextArea();
    private final JLabel imageLabel = new JLabel();
    private final JButton fetchButton = new JButton("Fetch AstronomyPicture");
    private final JButton backButton = new JButton("Back");
    private final JButton fetchByDateButton = new JButton("Fetch by Date");
    private final JTextField dateInputField = new JTextField("YYYY-MM-DD", 10);
    // Persistent Buttons
    private final JPanel persistentButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton playButton;
    private String currentImageUrl;

    public APODView() {
        setLayout(new BorderLayout());

        // Title label
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_LABEL_SIZE));
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
        fetchButton.setPreferredSize(new Dimension(FETCH_BUTTON_WIDTH, FETCH_BUTTON_HEIGHT));
        fetchByDateButton.setPreferredSize(new Dimension(FETCH_BY_DATE_BUTTON_WIDTH, FETCH_BY_DATE_BUTTON_HEIGHT));
        backButton.setPreferredSize(new Dimension(BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT));
        dateInputField.setPreferredSize(new Dimension(DATE_INPUT_FIELD_WIDTH, DATE_INPUT_FIELD_HEIGHT));

        persistentButtonPanel.add(fetchButton);
        persistentButtonPanel.add(dateInputField);
        persistentButtonPanel.add(fetchByDateButton);
        persistentButtonPanel.add(backButton);

        add(persistentButtonPanel, BorderLayout.SOUTH);
    }

    public String getViewName() {
        return "apod";
    }

    /**
     * Method to set controller for the APOD use case.
     * @param controller controller for the APOD use case.
     */
    public void setController(ApodController controller) {
        // Fetch by Date Button Action
        fetchByDateButton.addActionListener(action -> {
            final String date = dateInputField.getText();
            System.out.println("Fetching AstronomyPicture for date: " + date);
            controller.fetchApodByDate(date);
        });

        // Fetch Button Action
        fetchButton.addActionListener(action -> {
            System.out.println("Fetch AstronomyPicture button clicked!");
            controller.fetchApod();
        });

        // Back Button Action
        backButton.addActionListener(action -> controller.navigateToHome());
    }

    /**
     * Method to display the Astronomical Picture of the Day to the user.
     * @param title of the picture/video which becomes the title of the frame.
     * @param description of the image.
     * @param mediaType picture or video.
     * @param url of the picture.
     * @param thumbnailUrl of the video.
     */
    public void displayAPOD(String title, String description, String mediaType, String url, String thumbnailUrl) {
        SwingUtilities.invokeLater(() -> {
            titleLabel.setText(title);
            descriptionArea.setText(description);

            // Store the current URL for saving or playing
            currentImageUrl = "image".equals(mediaType) ? url : thumbnailUrl;

            try {
                if ("image".equals(mediaType)) {
                    // Display the image
                    final ImageIcon image = new ImageIcon(new URL(url));
                    imageLabel.setIcon(image);

                    // Remove the Play Video button if it exists
                    if (playButton != null) {
                        persistentButtonPanel.remove(playButton);
                        playButton = null;
                    }
                }
                else if ("video".equals(mediaType)) {
                    // Display the video thumbnail
                    final ImageIcon thumbnail = new ImageIcon(new URL(thumbnailUrl));
                    imageLabel.setIcon(thumbnail);

                    // Add a "Play Video" button for video playback
                    if (playButton == null) {
                        playButton = new JButton("Play Video");
                        // Match other button sizes
                        playButton.setPreferredSize(new Dimension(PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT));
                        playButton.addActionListener(action -> {
                            try {
                                Desktop.getDesktop().browse(new URL(url).toURI());
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Failed to open video.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                        persistentButtonPanel.add(playButton);
                    }
                }

                // Refresh the persistent button panel
                persistentButtonPanel.revalidate();
                persistentButtonPanel.repaint();

                // Refresh the overall layout
                revalidate();
                repaint();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                imageLabel.setText("Failed to load media.");
            }
        });
    }
}
