package view;

import interface_adapter.fail.FailController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FailView extends JPanel {
    private final String viewName = "fail";
    private FailController failController;
    private JButton back;

    public String getViewName() {
        return this.viewName;
    }

    public void setFailViewController(FailController failViewController) {
        this.failController = failViewController;
    }

    public void refresh(String errorMessage) {
        this.removeAll();

        final JLabel title = new JLabel("Execution Failed");
        final JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        final JLabel message = new JLabel(errorMessage);
        final JPanel messagePanel = new JPanel();
        messagePanel.add(message);

        final JPanel backButtonPanel = new JPanel();
        back = new JButton("Back");
        backButtonPanel.add(back);
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            failController.execute();
                        }
                    }
                }
        );

        this.add(titlePanel);
        this.add(messagePanel);
        this.add(backButtonPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}