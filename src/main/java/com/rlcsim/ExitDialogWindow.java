package com.rlcsim;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ExitDialogWindow extends JDialog {

    private boolean yesButtonPressed = false;
    private boolean saveButtonPressed = false;
    private boolean cancelButtonPressed = false;

    public ExitDialogWindow(boolean modal, String string) {
        super(MainWindow.getMainWindow(), modal);
        setTitle("RLC-proto");

        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(gridBagLayout);
        add(panel);

        JLabel warningLabel = new JLabel(string);
        JLabel warningIcon = new JLabel(UIManager.getIcon("OptionPane.warningIcon"));
        warningLabel.setFont(new Font("Dialog", Font.BOLD, 17));

        JButton yesButton = new JButton("Yes");
        yesButton.setFocusPainted(false);
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        yesButton.setPreferredSize(new Dimension(100, 30));
        saveButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.setPreferredSize(new Dimension(100, 30));

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = .1;
        c.weighty = .1;
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(50, 50, 10, 0);
        panel.add(warningIcon, c);

        c.weightx = 1;
        c.weighty = .1;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(50, 15, 10, 50);
        panel.add(warningLabel, c);

        JPanel buttonPanel = new JPanel();
        GridBagConstraints c2 = new GridBagConstraints();
        buttonPanel.setLayout(gridBagLayout);

        c2.gridx = 0;
        c2.gridy = 0;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.fill = GridBagConstraints.NONE;
        c2.insets = new Insets(0, 0, 0, 0);
        buttonPanel.add(yesButton, c2);

        c2.gridx = 1;
        c2.gridy = 0;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.fill = GridBagConstraints.NONE;
        c2.insets = new Insets(0, 12, 0, 0);
        buttonPanel.add(saveButton, c2);

        c2.gridx = 2;
        c2.gridy = 0;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.fill = GridBagConstraints.NONE;
        c2.insets = new Insets(0, 12, 0, 0);
        buttonPanel.add(cancelButton, c2);

        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 1;

        panel.add(buttonPanel, c);

        yesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                yesButtonPressed = true;
                dispose();
            }

        });

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonPressed = true;
                dispose();
            }

        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed = true;
                dispose();
            }

        });

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(MainWindow.getMainWindow());
        setVisible(true);

    }

    public boolean getSaveButtonPressed() {
        return saveButtonPressed;
    }

    public boolean getCancelButtonPressed() {
        return cancelButtonPressed;
    }

    public boolean getYesButtonPressed() {
        return yesButtonPressed;
    }

}
