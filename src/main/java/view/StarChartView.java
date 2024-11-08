package view;

import interface_adapter.star_chart.StarChartController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StarChartView extends JPanel {
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
        dateInputField.setColumns(20);
        final LabelTextPanel dateInfo = new LabelTextPanel(new JLabel("Date (YYYY-MM-DD)"), dateInputField);

        final JPanel generateButtonPanel = new JPanel();
        generate = new JButton("Generate");
        generateButtonPanel.add(generate);
        generate.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(generate)) {
                            String longitude = longInputField.getText();
                            String latitude = latInputField.getText();
                            String date = dateInputField.getText();
                            try {
                                starChartController.execute(longitude, latitude, date);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
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
