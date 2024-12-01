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

    public void refresh(MoonPhaseOutputData moonPhaseOutputData) throws IOException {
        this.removeAll();

        final JLabel title = new JLabel(String.format("Moon Phase for %s, %s on %s", moonPhaseOutputData.getLongitude(), moonPhaseOutputData.getLatitude(), moonPhaseOutputData.getDate()));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String imgURL = moonPhaseOutputData.getImageURL();
        URL url = new URL(imgURL);
        BufferedImage image = ImageIO.read(url);
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

    public static void main(String[] args) throws IOException {
        MoonPhaseOutputData moonPhaseOutputData = new MoonPhaseOutputData("33.775867", "-84.39733", "2024-11-06", "https://widgets.astronomyapi.com/moon-phase/generated/bba7ff5f078c4d3002bb8651b1cca6b2896720cf95d0ad798c3f2f32fb5e9248.png", false);
        DisplayMoonPhaseView displayMoonPhaseView = new DisplayMoonPhaseView();
        displayMoonPhaseView.refresh(moonPhaseOutputData);
        final JFrame app = new JFrame();
        app.add(displayMoonPhaseView);
        app.pack();
        app.setVisible(true);
    }
}
