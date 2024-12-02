package view;

import entity.StarChart;
import interface_adapter.display_star_chart.DisplayStarChartController;
import use_case.star_chart.StarChartOutputData;

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
    private DisplayStarChartController displayStarChartController;
    private JButton back;

    public String getViewName() {
        return this.viewName;
    }

    public void setDisplayStarChartController(DisplayStarChartController displayStarChartController) {
        this.displayStarChartController = displayStarChartController;
    }

    public void refresh(StarChartOutputData starChartOutputData) {
        this.removeAll();

        final JLabel title = new JLabel(String.format("Star Chart for %s, %s on %s", starChartOutputData.getLongitude(), starChartOutputData.getLatitude(), starChartOutputData.getDate()));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String imgURL = starChartOutputData.getImgURL();
        BufferedImage image = null;
        try {
            URL url = new URL(imgURL);
            image = ImageIO.read(url);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
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
    }
}
