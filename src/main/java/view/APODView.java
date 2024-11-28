package view;

import interface_adapter.APOD_date.APODController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class APODView extends JPanel {
    private final JLabel titleLabel = new JLabel();
    private final JTextArea descriptionArea = new JTextArea();
    private final JLabel imageLabel = new JLabel();
    private final JButton fetchButton = new JButton("Fetch APOD");
    private final JButton backButton = new JButton("Back");
    private final JButton fetchByDateButton = new JButton("Fetch by Date");
    private final JTextField dateInputField = new JTextField("YYYY-MM-DD", 10);

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

        // Fetch button (action tied to controller)
        add(fetchButton, BorderLayout.SOUTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        fetchButton.setPreferredSize(new Dimension(150, 30));
        fetchByDateButton.setPreferredSize(new Dimension(150, 30));
        backButton.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(fetchButton);
        buttonPanel.add(dateInputField);
        buttonPanel.add(fetchByDateButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getViewName() {
        return "apod";
    }

    public void setController(APODController controller) {;
        // Fetch by Date Button Action
        fetchByDateButton.addActionListener(e -> {
            String date = dateInputField.getText();
            System.out.println("Fetching APOD for date: " + date);
            controller.fetchAPODByDate(date);
        });

        fetchButton.addActionListener(e -> {
            System.out.println("Fetch APOD button clicked!");
            controller.fetchAPOD();
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.navigateToHome();
            }
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
