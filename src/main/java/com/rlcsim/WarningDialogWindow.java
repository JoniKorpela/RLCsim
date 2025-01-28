package com.rlcsim;

import java.awt.Color;
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
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class WarningDialogWindow extends JDialog {

    public WarningDialogWindow(boolean modal, String message) {
        super(MainWindow.getMainWindow(), modal);
        setTitle("RLC-proto");
        setResizable(false);
        setBackground(new Color(214, 217, 223));
        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(gridBagLayout);
        add(panel);

        JTextArea warningText = new JTextArea(message);
        JLabel warningIcon = new JLabel(UIManager.getIcon("OptionPane.warningIcon"));
        warningText.setFont(new Font("Dialog", Font.BOLD, 17));
        warningText.setEditable(false);
        warningText.setFocusable(false);
        warningText.setLineWrap(true);
        warningText.setPreferredSize(new Dimension(300, 100));
        Color color = new Color(214, 217, 223);
        warningText.setBackground(color);
        warningText.setBorder(null);
        panel.setBackground(color);

        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 30));

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0;
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(40, 50, 10, 0);
        panel.add(warningIcon, c);

        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(90, 15, 10, 50);
        panel.add(warningText, c);

        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(50, 15, 10, 110);
        panel.add(okButton, c);

        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(MainWindow.getMainWindow());
        setVisible(true);
    }

}
