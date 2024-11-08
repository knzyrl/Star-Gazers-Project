package view;

import interface_adapter.home.HomeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JPanel {
    private String viewName = "home";
    private HomeController homeController;
    private JButton starChart;

    public HomeView() {
        final JLabel title = new JLabel("Home Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel starChartButtonPanel = new JPanel();
        starChart = new JButton("Generate Star Chart");
        starChartButtonPanel.add(starChart);
        starChart.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(starChart)) {
                            System.out.println("star chart pressed");
                            homeController.execute("star chart");
                        }
                    }
                }
        );

        this.add(title);
        this.add(starChartButtonPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
