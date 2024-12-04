package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.near_earth_objects.NearEarthObjectsController;

/**
 * Class for the view displayed to the user when they select the Near Earth Objects use case in the Home View.
 */
public class NearEarthObjectsView extends JPanel {
    private final String viewName = "NEO view";
    private final JTextField startDateInput = new JTextField(10);
    private final JTextField endDateInput = new JTextField(10);
    private final JButton fetchButton;
    private final JButton clearButton;
    private NearEarthObjectsController nearEarthObjectsController;

    public NearEarthObjectsView() {
        final JLabel title = new JLabel("Near-Earth Object Data Fetcher");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input fields for dates
        final JPanel startDatePanel = new JPanel();
        startDatePanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        startDatePanel.add(startDateInput);

        final JPanel endDatePanel = new JPanel();
        endDatePanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        endDatePanel.add(endDateInput);

        // Fetch button
        final JPanel buttonPanel = new JPanel();
        fetchButton = new JButton("Fetch Data");
        clearButton = new JButton("Clear");
        buttonPanel.add(fetchButton);
        buttonPanel.add(clearButton);

        // Add action listener to fetch button
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (nearEarthObjectsController != null) {
                    final String startDate = startDateInput.getText();
                    final String endDate = endDateInput.getText();

                    try {
                        if (startDate.isEmpty() || endDate.isEmpty()) {
                            throw new IllegalArgumentException("Start and End dates cannot be empty.");
                        }
                        nearEarthObjectsController.fetchNearEarthObjectsData(startDate, endDate);
                    }
                    catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(NearEarthObjectsView.this, ex.getMessage(),
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        clearButton.addActionListener(event -> {
            startDateInput.setText("");
            endDateInput.setText("");
        });

        // Layout setup
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(startDatePanel);
        this.add(endDatePanel);
        this.add(buttonPanel);
    }

    public void setNearEarthObjectsController(NearEarthObjectsController controller) {
        this.nearEarthObjectsController = controller;
    }

    public String getViewName() {
        return viewName;
    }
}
