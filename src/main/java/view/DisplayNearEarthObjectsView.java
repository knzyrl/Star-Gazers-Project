package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import interface_adapter.near_earth_objects.NearEarthObjectsPresenter;
import use_case.near_earth_objects.NearEarthObjectsOutputData;

/**
 * Class consisting of methods to display information on asteroids near the Earth.
 */
public class DisplayNearEarthObjectsView extends JPanel {
    private static final int TEXT_AREA_ROWS = 15;
    private static final int TEXT_AREA_COLUMNS = 40;
    private final String viewName = "display NEO view";
    private final JTextArea dataArea;
    private NearEarthObjectsPresenter presenter;

    public DisplayNearEarthObjectsView() {
        // Title
        final JLabel title = new JLabel("Near-Earth Object Data");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Text area for displaying data
        dataArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        dataArea.setEditable(false);
        final JScrollPane scrollPane = new JScrollPane(dataArea);

        // Back button panel
        final JPanel backPanel = new JPanel();
        final JButton backButton = new JButton("Back");
        backPanel.add(backButton);

        backButton.addActionListener(event -> {
            if (presenter != null) {
                presenter.back();
            }
        });

        // Layout setup
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(backPanel, BorderLayout.SOUTH);

    }

    public void setPresenter(NearEarthObjectsPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Displays the Near-Earth Object (NEO) data in the text area.
     *
     * @param neoOutputData List of Near-Earth Object entities to display.
     */
    public void displayNearEarthObjectsData(List<NearEarthObjectsOutputData> neoOutputData) {
        final StringBuilder builder = new StringBuilder();

        if (neoOutputData.isEmpty()) {
            builder.append("No Near-Earth Objects found for the specified date range.");
        }
        else {
            builder.append("Nearby Asteroids:\n");
            builder.append("========================================\n");
            for (NearEarthObjectsOutputData neo : neoOutputData) {
                builder.append(String.format("Name: %s%n", neo.getName()));
                builder.append(String.format("Closest Approach Date: %s%n", neo.getClosestApproachDate()));
                builder.append(String.format("Distance: %.2f km%n", neo.getClosestDistanceKm()));
                builder.append(String.format("Relative Velocity: %.2f km/s%n", neo.getRelativeVelocity()));
                builder.append("----------------------------------------\n");
            }
        }

        dataArea.setText(builder.toString());
    }

    public String getViewName() {
        return viewName;
    }
}
