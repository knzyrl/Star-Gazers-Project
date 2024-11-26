package view;

import entity.MoonPhase;
import interface_adapter.display_moon_phase.DisplayMoonPhaseController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class DisplayMoonPhaseView extends JPanel {
    private MoonPhase moonPhase;
    private final String viewName = "display Moon Phase";
    private DisplayMoonPhaseController displayMoonPhaseController;
    private JButton back;

    public void setMoonPhase(MoonPhase moonPhase) {
        this.moonPhase = moonPhase;
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setDisplayMoonPhaseController(DisplayMoonPhaseController displayMoonPhaseController) {
        this.displayMoonPhaseController = displayMoonPhaseController;
    }

    public void refresh() throws IOException {
        this.removeAll();

        final JLabel title = new JLabel(String.format("Moon Phase for %s, %s on %s", moonPhase.getLongitude(), moonPhase.getLatitude(), moonPhase.getDate()));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String imgURL = moonPhase.getImgURL();
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
        MoonPhase moonPhase = new MoonPhase("-84.39733", "33.775867", "2024-11-06", "https://widgets.astronomyapi.com/moon-phase/generated/bba7ff5f078c4d3002bb8651b1cca6b2896720cf95d0ad798c3f2f32fb5e9248.png");
        DisplayMoonPhaseView displayMoonPhaseView = new DisplayMoonPhaseView();
        displayMoonPhaseView.setMoonPhase(moonPhase);
        displayMoonPhaseView.refresh();
        final JFrame app = new JFrame();
        app.add(displayMoonPhaseView);
        app.pack();
        app.setVisible(true);
    }
}
