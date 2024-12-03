package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

import interface_adapter.events.EventsController;

/**
 * Class to display the events to the user.
 */
public class EventsView extends JPanel {
    private static final int START_DATE_FIELD_COLUMNS = 200;
    private static final int END_DATE_FIELD_COLUMNS = 200;
    private final String viewName = "events";
    private EventsController eventsController;
    private final JTextField longInputField = new JTextField(20);
    private final JTextField latInputField = new JTextField(20);
    private final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    private final JFormattedTextField dateStartInputField = new JFormattedTextField(fmt);
    private final JFormattedTextField dateEndInputField = new JFormattedTextField(fmt);
    private final JComboBox comboBox = new JComboBox(new String[]{"Sun", "Moon"});
    private final JButton show;
    private final JButton back;

    public EventsView() {
        final JLabel title = new JLabel("Events Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel longInfo = new LabelTextPanel(new JLabel("Longitude"), longInputField);
        final LabelTextPanel latInfo = new LabelTextPanel(new JLabel("Latitude"), latInputField);
        dateStartInputField.setColumns(START_DATE_FIELD_COLUMNS);
        final LabelTextPanel dateStartInfo = new LabelTextPanel(new JLabel("Start Date (YYYY-MM-DD)"),
                dateStartInputField);
        dateEndInputField.setColumns(END_DATE_FIELD_COLUMNS);
        final LabelTextPanel dateEndInfo = new LabelTextPanel(new JLabel("End Date (YYYY-MM-DD)"), dateEndInputField);

        final JPanel comboBoxPanel = new JPanel();
        final JLabel body = new JLabel("Body");
        comboBoxPanel.add(body);
        comboBoxPanel.add(comboBox);

        final JPanel showButtonPanel = new JPanel();
        show = new JButton("Show");
        showButtonPanel.add(show);
        show.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(show)) {
                            final String longitude = longInputField.getText();
                            final String latitude = latInputField.getText();
                            final String dateStart = dateStartInputField.getText();
                            final String dateEnd = dateEndInputField.getText();
                            final String body = comboBox.getSelectedItem().toString();
                            eventsController.execute(longitude, latitude, dateStart, dateEnd, body);
                        }
                    }
                }
        );

        final JPanel backButtonPanel = new JPanel();
        back = new JButton("Back");
        backButtonPanel.add(back);
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            eventsController.execute();
                        }
                    }
                }
        );

        this.add(longInfo);
        this.add(latInfo);
        this.add(dateStartInfo);
        this.add(dateEndInfo);
        this.add(comboBoxPanel);
        this.add(showButtonPanel);
        this.add(backButtonPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setEventsController(EventsController eventsController) {
        this.eventsController = eventsController;
    }
}
