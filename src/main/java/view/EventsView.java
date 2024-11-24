package view;

import helper.NumberFormatChecker;
import interface_adapter.events.EventsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventsView extends JPanel {
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
        dateStartInputField.setColumns(20);
        final LabelTextPanel dateStartInfo = new LabelTextPanel(new JLabel("Start Date (YYYY-MM-DD)"), dateStartInputField);
        dateEndInputField.setColumns(20);
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
                            String longitude = longInputField.getText();
                            String latitude = latInputField.getText();
                            String dateStart = dateStartInputField.getText();
                            String dateEnd = dateEndInputField.getText();
                            String body = comboBox.getSelectedItem().toString();
                            try {
                                fmt.parse(dateStart);
                                fmt.parse(dateEnd);
                            } catch (ParseException ex) {
                                JOptionPane.showMessageDialog(new JDialog(), "Please input dates in the correct format (YYYY-MM-DD).", "Date Format Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            if (NumberFormatChecker.checkDouble(longitude) && NumberFormatChecker.checkDouble(latitude)) {
                                eventsController.execute(longitude, latitude, dateStart, dateEnd, body);
                            }
                            else {
                                JOptionPane.showMessageDialog(new JDialog(), "Please input decimals for latitude and longitude.", "Number Format Error", JOptionPane.ERROR_MESSAGE);
                            }
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

    public static void main(String[] args) {
        EventsView ev = new EventsView();
        final JFrame app = new JFrame();
        app.add(ev);
        app.pack();
        app.setVisible(true);
    }
}
