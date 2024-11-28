package view;

import entity.NEOEntity;
import interface_adapter.near_earth_objects.NEOPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayNEOView extends JPanel {
    private final String viewName = "display NEO view";
    private final JTextArea dataArea;
    private NEOPresenter presenter;

    public DisplayNEOView() {
        // Title
        final JLabel title = new JLabel("Near-Earth Object Data");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Text area for displaying data
        dataArea = new JTextArea(15, 40);
        dataArea.setEditable(false);
        final JScrollPane scrollPane = new JScrollPane(dataArea);

        // Back button panel
        final JPanel backPanel = new JPanel();
        JButton back = new JButton("Back");
        backPanel.add(back);

        // Layout setup
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(backPanel, BorderLayout.SOUTH);

        // Back button action
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (presenter != null) {
                    presenter.back(); // Use the presenter for navigation
                }
            }
        });
    }

    public void setPresenter(NEOPresenter presenter) {
        this.presenter = presenter;
    }

    public void displayNEOData(List<NEOEntity> neoEntities) {
        StringBuilder builder = new StringBuilder();

        if (neoEntities.isEmpty()) {
            builder.append("No Near-Earth Objects found for the specified date range.");
        } else {
            builder.append("Nearby Asteroids:\n");
            builder.append("========================================\n");
            for (NEOEntity neo : neoEntities) {
                builder.append(String.format("Name: %s%n", neo.getName()));
                builder.append(String.format("Closest Approach Date: %s%n", neo.getClosestApproachDate()));
                builder.append(String.format("Distance: %.2f km%n", neo.getClosestDistanceKm()));
                builder.append("----------------------------------------\n");
            }
        }

        dataArea.setText(builder.toString());
    }

    public String getViewName() {
        return viewName;
    }
}
