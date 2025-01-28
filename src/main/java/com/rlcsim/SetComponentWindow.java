package com.rlcsim;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SetComponentWindow extends JDialog {

    ArrayList<ElectricalComponent> components;
    ElectricalComponent thisComponent;

    public SetComponentWindow(boolean modal, ElectricalComponent component, ComponentHandler componentHandler) {
        super(MainWindow.getMainWindow(), modal);
        components = componentHandler.getElectricalComponents();
        thisComponent = component;
        setTitle("Set Value");
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        add(panel);

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(gridBagLayout);

        Font labelFont = new Font("Dialog", Font.BOLD, 17);
        Font textFieldFont = new Font("Dialog", Font.BOLD, 19);
        Dimension textFieldDimension = new Dimension(150, 30);

        JLabel nameLabel = new JLabel("Name");
        JLabel valueLabel = new JLabel("Value");

        nameLabel.setFont(labelFont);
        valueLabel.setFont(labelFont);

        JLabel valueUnitLabel = new JLabel("");

        if (component instanceof Resistor) {
            valueUnitLabel.setText("Ω");
        } else if (component instanceof Inductor) {
            valueUnitLabel.setText("H");
        } else if (component instanceof Capacitor) {
            valueUnitLabel.setText("C");
        }

        valueUnitLabel.setFont(labelFont);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.setPreferredSize(new Dimension(100, 30));

        JTextField nameField = new JTextField();
        JTextField valueField = new JTextField();

        nameField.setText(component.getName());
        valueField.setText(component.getValue());

        nameField.setFont(textFieldFont);
        valueField.setFont(textFieldFont);

        nameField.setPreferredSize(textFieldDimension);
        valueField.setPreferredSize(textFieldDimension);

        nameField.setHorizontalAlignment(JTextField.CENTER);
        valueField.setHorizontalAlignment(JTextField.CENTER);

        JPanel subJPanel = new JPanel();
        GridBagConstraints c2 = new GridBagConstraints();
        subJPanel.setLayout(gridBagLayout);

        c2.insets = new Insets(5, 0, 5, 0);
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.gridx = 0;
        c2.gridy = 0;
        c2.weightx = 0;
        c2.weighty = .5;
        subJPanel.add(nameLabel, c2);

        c2.gridx = 0;
        c2.gridy = 1;
        subJPanel.add(valueLabel, c2);

        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.weightx = 1;
        c2.weighty = .1;
        c2.gridwidth = 1;
        c2.insets = new Insets(5, 50, 5, 0);

        c2.gridx = 1;
        c2.gridy = 0;
        subJPanel.add(nameField, c2);

        c2.gridx = 1;
        c2.gridy = 1;
        subJPanel.add(valueField, c2);

        c2.fill = GridBagConstraints.NONE;
        c2.gridwidth = GridBagConstraints.REMAINDER;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.gridwidth = 1;
        c2.weightx = 0;
        c2.insets = new Insets(10, 5, 10, 5);
        c2.gridx = 2;
        c2.gridy = 1;
        subJPanel.add(valueUnitLabel, c2);

        c.weightx = 1;
        c.weighty = .1;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(15, 25, 25, 5);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(subJPanel, c);

        c2.anchor = GridBagConstraints.LAST_LINE_START;
        c2.fill = GridBagConstraints.NONE;
        c.weighty = 1;
        c2.insets = new Insets(75, 0, 10, 0);
        c2.gridwidth = 1;
        c2.gridx = 0;
        c2.gridy = 6;
        subJPanel.add(okButton, c2);

        c2.insets = new Insets(75, 50, 10, 0);
        c2.gridx = 1;
        c2.gridy = 6;
        subJPanel.add(cancelButton, c2);

        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!checkName(nameField.getText(), thisComponent) && !checkValue(valueField.getText())) {
                    new WarningDialogWindow(true, "Name already exists.\nValue has be a positive number.");
                } else if (!checkName(nameField.getText(), thisComponent)) {
                    new WarningDialogWindow(true, "Name already exists.");
                } else if (!checkValue(valueField.getText())) {
                    new WarningDialogWindow(true, "Value has to be a positive number.");
                } else {
                    component.setName(nameField.getText());
                    component.setValue(valueField.getText());
                    dispose();
                }
            }

        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });

        pack();
        setLocationRelativeTo(MainWindow.getMainWindow());
        setVisible(true);
    }

    private boolean checkName(String value, ElectricalComponent thisComponent) {
        if (value.strip().isEmpty()) {
            return false;
        }
        for (ElectricalComponent component : components) {
            if (component == thisComponent) {
                continue;
            }
            if (component.getName().equals(value)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkValue(String value) {

        try {

            Double valueNumber = Double.valueOf(value);

            if (valueNumber <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

}
