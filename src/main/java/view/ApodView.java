package view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import interface_adapter.apod_date.ApodController;

/**
 * Class for the View displayed to the user when they select the Astronomical Picture of the Day use case from the
 * Home View.
 */
public class ApodView extends JPanel {

    private static final Logger LOGGER = Logger.getLogger(ApodView.class.getName());

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
    private final JButton fetchButton = new JButton("Fetch Astronomy Picture");
    private final JButton backButton = new JButton("Back");
    private final JButton fetchByDateButton = new JButton("Fetch by Date");
    private final JTextField dateInputField = new JTextField("YYYY-MM-DD", 10);
    private final JPanel persistentButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton playButton;
    private String currentImageUrl;

    public ApodView() {
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
     * Sets the controller for handling user interactions with the APOD view.
     * Connects button actions to the respective controller methods.
     *
     * @param controller The controller for the APOD use case, responsible for handling actions.
     */
    public void setController(ApodController controller) {
        fetchByDateButton.addActionListener(action -> {
            final String date = dateInputField.getText();
            controller.fetchApodByDate(date);
        });

        fetchButton.addActionListener(action -> controller.fetchApod());
        backButton.addActionListener(action -> controller.navigateToHome());
    }

    /**
     * Displays the Astronomy Picture of the Day (APOD) to the user.
     * Updates the UI with the provided details, including title, description, media type, and URLs.
     *
     * @param title        The title of the picture or video.
     * @param description  The description or explanation of the media.
     * @param mediaType    The type of media (e.g., "image" or "video").
     * @param url          The URL of the media (e.g., image or video link).
     * @param thumbnailUrl The URL of the thumbnail for the video, if applicable.
     */
    public void displayApod(String title, String description, String mediaType, String url, String thumbnailUrl) {
        SwingUtilities.invokeLater(() -> {
            updateUserInterfaceWithMedia(title, description, mediaType, url, thumbnailUrl);
        });
    }

    private void updateUserInterfaceWithMedia(String title,
                                              String description, String mediaType, String url, String thumbnailUrl) {
        titleLabel.setText(title);
        descriptionArea.setText(description);

        if ("image".equals(mediaType)) {
            currentImageUrl = url;
            try {
                final ImageIcon image = new ImageIcon(new URL(url));
                imageLabel.setIcon(image);

                if (playButton != null) {
                    persistentButtonPanel.remove(playButton);
                    playButton = null;
                }
            }
            catch (MalformedURLException ex) {
                LOGGER.severe("Invalid URL for image: " + ex.getMessage());
                imageLabel.setText("Invalid image URL.");
            }
            catch (IOException ex) {
                LOGGER.severe("Error loading image: " + ex.getMessage());
                imageLabel.setText("Failed to load image.");
            }
        }
        else if ("video".equals(mediaType)) {
            currentImageUrl = thumbnailUrl;
            try {
                final ImageIcon thumbnail = new ImageIcon(new URL(thumbnailUrl));
                imageLabel.setIcon(thumbnail);

                if (playButton == null) {
                    playButton = new JButton("Play Video");
                    playButton.setPreferredSize(new Dimension(PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT));
                    playButton.addActionListener(action -> openVideo(url));
                    persistentButtonPanel.add(playButton);
                }
            }
            catch (MalformedURLException ex) {
                LOGGER.severe("Invalid URL for video thumbnail: " + ex.getMessage());
                imageLabel.setText("Invalid video thumbnail URL.");
            }
            catch (IOException ex) {
                LOGGER.severe("Error loading video thumbnail: " + ex.getMessage());
                imageLabel.setText("Failed to load video thumbnail.");
            }
        }

        persistentButtonPanel.revalidate();
        persistentButtonPanel.repaint();
    }

    private void openVideo(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        }
        catch (MalformedURLException ex) {
            LOGGER.severe("Invalid URL for video: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Invalid video URL.", "There is a problem", JOptionPane.ERROR_MESSAGE);
        }
        catch (URISyntaxException ex) {
            LOGGER.severe("Invalid URI for video: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Invalid video URI.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex) {
            LOGGER.severe("Error opening video: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Failed to open video.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
