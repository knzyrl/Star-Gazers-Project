package view;

import interface_adapter.near_earth_objects.NearEarthObjectsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NEOView extends JPanel {
    private final String viewName = "NEO view";
    private final JTextField startDateInput = new JTextField(10);
    private final JTextField endDateInput = new JTextField(10);
    private final JButton fetchButton;
    private NearEarthObjectsController nearEarthObjectsController;

    public NEOView() {
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
        buttonPanel.add(fetchButton);

        // Add action listener to fetch button
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nearEarthObjectsController != null) {
                    String startDate = startDateInput.getText();
                    String endDate = endDateInput.getText();

                    try {
                        nearEarthObjectsController.fetchNearEarthObjectsData(startDate, endDate);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(NEOView.this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Layout setup
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(startDatePanel);
        this.add(endDatePanel);
        this.add(buttonPanel);
    }

    public void setNEOController(NearEarthObjectsController controller) {
        this.nearEarthObjectsController = controller;
    }

    public String getViewName() {
        return viewName;
    }
}
