package view;

import entity.StarChart;
import interface_adapter.display_star_chart.DisplayStarChartController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class DisplayStarChartView extends JPanel {
    private final String viewName = "display star chart";
    private StarChart starChart;
    private DisplayStarChartController displayStarChartController;
    private JButton back;

    public String getViewName() {
        return this.viewName;
    }

    public void setStarChart(StarChart starChart) {
        this.starChart = starChart;
    }

    public void setDisplayStarChartController(DisplayStarChartController displayStarChartController) {
        this.displayStarChartController = displayStarChartController;
    }

    public void refresh() throws IOException {
        this.removeAll();

        final JLabel title = new JLabel(String.format("Star Chart for %s, %s on %s", starChart.getLongitude(), starChart.getLatitude(), starChart.getDate()));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String imgURL = starChart.getImgURL();
        URL url = new URL(imgURL);
        BufferedImage image = ImageIO.read(url);
        JLabel starChartImg = new JLabel(new ImageIcon(image));

        final JPanel backButtonPanel = new JPanel();
        back = new JButton("Back");
        backButtonPanel.add(back);
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            displayStarChartController.execute();
                        }
                    }
                }
        );

        this.add(title);
        this.add(starChartImg);
        this.add(backButtonPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(600, 600)); // Adjust width and height as needed
    }

    public static void main(String[] args) throws IOException {
        StarChart starChart = new StarChart("-84.39733", "33.775867", "2024-11-06", "https://widgets.astronomyapi.com/star-chart/generated/b54d8700720a2ec9c9e5532f8f18924ea6b68e7d9bbb8c587a0429271b4aaece.png");
        DisplayStarChartView displayStarChartView = new DisplayStarChartView();
        displayStarChartView.setStarChart(starChart);
        displayStarChartView.refresh();
        final JFrame app = new JFrame();
        app.add(displayStarChartView);
        app.pack();
        app.setVisible(true);
    }
}
