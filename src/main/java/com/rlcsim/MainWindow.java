package com.rlcsim;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    private static MainWindow mainWindow = null;

    private MainWindow() {
        initMainWindow();
    }

    public static MainWindow getMainWindow() {
        if (mainWindow == null) {
            mainWindow = new MainWindow();
        }
        return mainWindow;
    }

    private void initMainWindow() {

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setTitle("RLC-Proto");

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridBagLayout);

        ComponentPanel componentPanel = new ComponentPanel();
        ControlPanel controlPanel = new ControlPanel();
        DrawPanel drawPanel = new DrawPanel(componentPanel, controlPanel);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = .3;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.VERTICAL;

        add(controlPanel, c);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(gridBagLayout);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        subPanel.add(componentPanel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;

        subPanel.add(drawPanel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        add(subPanel, c);

        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        StatusPanel statusPanel = new StatusPanel();
        add(statusPanel, c);

        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;
        JPanel sidePanel = new JPanel();
        add(sidePanel, c);

        this.pack();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                ComponentHandler componentHandler = drawPanel.getComponentHandler();
                if (!FileHandler.checkSavedStatus(componentHandler.getElectricalComponents(),
                        componentHandler.getWires())) {
                    ExitDialogWindow exitDialog = new ExitDialogWindow(true, "Close without saving?");
                    if (exitDialog.getSaveButtonPressed()) {
                        FileHandler.saveToFile(componentHandler.getElectricalComponents(), componentHandler.getWires());
                        System.exit(1);
                    } else if (exitDialog.getYesButtonPressed()) {
                        System.exit(1);
                    }
                } else {
                    System.exit(1);
                }

            }
        });

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

    }

}
