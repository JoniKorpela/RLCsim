package com.rlcsim;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {

    private static JLabel saveLabel;

    public StatusPanel() {
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        setLayout(flowLayout);

        saveLabel = new JLabel(" ");
        add(saveLabel);
    }

    public static void updateSaveStatus(boolean isSaved) {
        if (isSaved) {
            saveLabel.setText("Saved");
        } else {
            saveLabel.setText("Not saved");
        }
    }
}
