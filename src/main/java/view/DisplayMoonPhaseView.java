package view;

import interface_adapter.display_moon_phase.DisplayMoonPhaseController;
import use_case.moon_phase.MoonPhaseOutputData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class DisplayMoonPhaseView extends JPanel {
    private final String viewName = "display Moon Phase";
    private DisplayMoonPhaseController displayMoonPhaseController;
    private JButton back;

    public String getViewName() {
        return this.viewName;
    }

    public void setDisplayMoonPhaseController(DisplayMoonPhaseController displayMoonPhaseController) {
        this.displayMoonPhaseController = displayMoonPhaseController;
    }

    public void refresh(MoonPhaseOutputData moonPhaseOutputData) {
        this.removeAll();

        final JLabel title = new JLabel(String.format("Moon Phase for %s, %s on %s", moonPhaseOutputData.getLongitude(), moonPhaseOutputData.getLatitude(), moonPhaseOutputData.getDate()));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String imgURL = moonPhaseOutputData.getImageURL();
        BufferedImage image = null;
        try {
            URL url = new URL(imgURL);
            image = ImageIO.read(url);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        JLabel moonPhaseImg = new JLabel(new ImageIcon(image));

        final JPanel backButtonPanel = new JPanel();
        back = new JButton("Back");
        backButtonPanel.add(back);
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            displayMoonPhaseController.execute();
                        }
                    }
                }
        );

        this.add(title);
        this.add(moonPhaseImg);
        this.add(backButtonPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
