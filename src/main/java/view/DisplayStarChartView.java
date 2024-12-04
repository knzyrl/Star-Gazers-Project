package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import interface_adapter.display_star_chart.DisplayStarChartController;
import use_case.star_chart.StarChartOutputData;

/**
 * Class for the Display View for the Star Cahrt use case.
 */
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

    /**
     * Method responsible for displaying the Star Chart to the user.
     * @param starChartOutputData output data to be visualized.
     */
    public void refresh(StarChartOutputData starChartOutputData) {
        this.removeAll();

        final JLabel title = new JLabel(String.format("Star Chart for %s, %s on %s", starChartOutputData.getLongitude(),
                starChartOutputData.getLatitude(), starChartOutputData.getDate()));
        final JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        final String imgURL = starChartOutputData.getImgURL();
        BufferedImage image = null;
        try {
            final URL url = new URL(imgURL);
            image = ImageIO.read(url);
        }
        catch (IOException action) {
            System.err.println(action.getMessage());
        }
        final JLabel starChartImg = new JLabel(new ImageIcon(image));

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

        this.add(titlePanel);
        this.add(starChartImg);
        this.add(backButtonPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
