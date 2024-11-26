package view;

import entity.EventsList;
import entity.Event;
import interface_adapter.display_events.DisplayEventsController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayEventsView extends JPanel {
    private final String viewName = "display events view";
    private EventsList eventsList;
    private DisplayEventsController displayEventsController;
    private JButton back;

    public String getViewName() {
        return this.viewName;
    }

    public void setEventsList(EventsList eventsList) {
        this.eventsList = eventsList;
    }

    public void setDisplayEventsController(DisplayEventsController displayEventsController) {
        this.displayEventsController = displayEventsController;
    }

    public void refresh() {
        this.removeAll();

        JPanel titlePanel = new JPanel();
        final JLabel title = new JLabel(String.format("<html><div style='text-align: center;'>Events for the %s <br/> at %s, %s <br/> from %s to %s</div></html>", eventsList.getBody(), eventsList.getLongitude(), eventsList.getLatitude(), eventsList.getDateStart(), eventsList.getDateEnd()));
        titlePanel.add(title);

        JPanel eventsPanel = new JPanel();
        if (eventsList.getEventsList().isEmpty()) {
            final JLabel noEvents = new JLabel("No events were found for your query specifications.");
            eventsPanel.add(noEvents);
        }
        else {
            eventsPanel = generateEventsPanel();
        }

        final JPanel backButtonPanel = new JPanel();
        back = new JButton("Back");
        backButtonPanel.add(back);
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            displayEventsController.execute();
                        }
                    }
                }
        );

        this.add(titlePanel);
        this.add(eventsPanel);
        this.add(backButtonPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private JPanel generateEventsPanel() {
        final JPanel eventsPanel = new JPanel();
        for (Event event : eventsList.getEventsList()) {
            String eventType = switch (event.getType()) {
                case "total_solar_eclipse" -> "Total Solar Eclipse";
                case "annular_solar_eclipse" -> "Annular Solar Eclipse";
                case "partial_solar_eclipse" -> "Partial Solar Eclipse";
                case "total_lunar_eclipse" -> "Total Lunar Eclipse";
                case "annular_lunar_eclipse" -> "Annular Lunar Eclipse";
                case "partial_lunar_eclipse" -> "Partial Lunar Eclipse";
                default -> "";
            };
            final JLabel eventLabel = new JLabel(String.format("%s on %s", eventType, event.getDate()));
            eventsPanel.add(eventLabel);
        }
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        return eventsPanel;
    }
}
