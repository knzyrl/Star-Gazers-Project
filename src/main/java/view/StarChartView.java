package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.star_chart.StarChartController;

/**
 * Class for the view displayed to the user when they select the Star Chart use case from the Home View.
 */
public class StarChartView extends JPanel {
    private static final int DATE_FIELD_COLUMNS = 20;
    private final String viewName = "star chart";
    private StarChartController starChartController;
    private final JTextField longInputField = new JTextField(20);
    private final JTextField latInputField = new JTextField(20);
    private final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    private final JFormattedTextField dateInputField = new JFormattedTextField(fmt);
    private final JButton generate;
    private final JButton back;

    public StarChartView() {
        final JLabel title = new JLabel("Star Chart Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel longInfo = new LabelTextPanel(new JLabel("Longitude"), longInputField);
        final LabelTextPanel latInfo = new LabelTextPanel(new JLabel("Latitude"), latInputField);
        dateInputField.setColumns(DATE_FIELD_COLUMNS);
        final LabelTextPanel dateInfo = new LabelTextPanel(new JLabel("Date (YYYY-MM-DD)"), dateInputField);

        final JPanel generateButtonPanel = new JPanel();
        generate = new JButton("Generate");
        generateButtonPanel.add(generate);
        generate.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(generate)) {
                            final String longitude = longInputField.getText();
                            final String latitude = latInputField.getText();
                            final String date = dateInputField.getText();
                            try {
                                starChartController.execute(longitude, latitude, date);
                            }
                            catch (IOException ioException) {
                                System.err.println(ioException.getMessage());
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
                            starChartController.execute();
                        }
                    }
                }
        );

        this.add(title);
        this.add(longInfo);
        this.add(latInfo);
        this.add(dateInfo);
        this.add(generateButtonPanel);
        this.add(backButtonPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    public String getViewName() {
        return this.viewName;
    }

    public void setStarChartController(StarChartController starChartController) {
        this.starChartController = starChartController;
    }
}
