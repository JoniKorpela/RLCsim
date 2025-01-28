package com.rlcsim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ValueWindow extends JDialog {

    public ValueWindow(boolean modal, ElectricalComponent component) {

        super(MainWindow.getMainWindow(), modal);
        setLocation(800, 500);

        setTitle(component.getName());
        setResizable(true);
        setBackground(new Color(214, 217, 223));
        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(gridBagLayout);
        add(panel);
        setBackground(Color.BLUE);

        Font labelFont = new Font("Dialog", Font.BOLD, 17);
        Font textFieldFont = new Font("Dialog", Font.BOLD, 18);
        Dimension textFieldDimension = new Dimension(150, 30);

        JLabel currentLabel = new JLabel("I");
        JLabel voltageLabel = new JLabel("U");
        JLabel powerLabel = new JLabel("P");

        JLabel currentRmsLabel = new JLabel("Irms");
        JLabel voltageRmsLabel = new JLabel("Urms");
        JLabel angleLabel = new JLabel("∠");

        currentLabel.setFont(labelFont);
        voltageLabel.setFont(labelFont);
        powerLabel.setFont(labelFont);
        currentRmsLabel.setFont(labelFont);
        voltageRmsLabel.setFont(labelFont);
        angleLabel.setFont(labelFont);

        Double current = Double.valueOf(component.getCurrent());
        Double voltage = Double.valueOf(component.getVoltage());
        Double power = current * voltage;
        Double currentRMS = current / Math.sqrt(2);
        Double voltageRMS = voltage / Math.sqrt(2);
        Double angle = Double.valueOf(component.getPhase());

        JTextField currentField = new JTextField();
        JTextField voltageField = new JTextField();
        JTextField powerField = new JTextField();

        currentField.setText(component.getCurrent());
        voltageField.setText(component.getVoltage());
        powerField.setText(
                String.valueOf(Double.valueOf(component.getCurrent()) * Double.valueOf(component.getVoltage())));

        JTextField currentRmsField = new JTextField();
        JTextField voltageRmsField = new JTextField();
        JTextField angleField = new JTextField();

        currentField.setText(String.format("%.2f", current));
        voltageField.setText(String.format("%.2f", voltage));
        powerField.setText(String.valueOf(String.format("%.2f", power)));
        currentRmsField.setText(String.format("%.2f", currentRMS));
        voltageRmsField.setText(String.format("%.2f", voltageRMS));
        angleField.setText(String.format("%.2f", angle));

        currentField.setFont(textFieldFont);
        voltageField.setFont(textFieldFont);
        powerField.setFont(textFieldFont);
        currentRmsField.setFont(textFieldFont);
        voltageRmsField.setFont(textFieldFont);
        angleField.setFont(textFieldFont);

        currentField.setPreferredSize(textFieldDimension);
        voltageField.setPreferredSize(textFieldDimension);
        powerField.setPreferredSize(textFieldDimension);
        currentRmsField.setPreferredSize(textFieldDimension);
        voltageRmsField.setPreferredSize(textFieldDimension);
        angleField.setPreferredSize(textFieldDimension);

        currentField.setHorizontalAlignment(JTextField.CENTER);
        voltageField.setHorizontalAlignment(JTextField.CENTER);
        powerField.setHorizontalAlignment(JTextField.CENTER);
        currentRmsField.setHorizontalAlignment(JTextField.CENTER);
        voltageRmsField.setHorizontalAlignment(JTextField.CENTER);
        angleField.setHorizontalAlignment(JTextField.CENTER);

        currentField.setEditable(false);
        voltageField.setEditable(false);
        powerField.setEditable(false);
        currentRmsField.setEditable(false);
        voltageRmsField.setEditable(false);
        angleField.setEditable(false);

        currentField.setFocusable(false);
        voltageField.setFocusable(false);
        powerField.setFocusable(false);
        currentRmsField.setFocusable(false);
        voltageRmsField.setFocusable(false);
        angleField.setFocusable(false);

        JLabel currentUnit = new JLabel("A");
        JLabel voltageUnit = new JLabel("V");
        JLabel powerUnit = new JLabel("W");
        JLabel currentRmsUnit = new JLabel("A");
        JLabel voltageRmsUnit = new JLabel("V");
        JLabel angleUnit = new JLabel("°");

        currentUnit.setFont(labelFont);
        voltageUnit.setFont(labelFont);
        powerUnit.setFont(labelFont);
        currentRmsUnit.setFont(labelFont);
        voltageRmsUnit.setFont(labelFont);
        angleUnit.setFont(labelFont);

        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(gridBagLayout);
        panelLeft.setBackground(Color.LIGHT_GRAY);

        JPanel panelRight = new JPanel();
        panelRight.setLayout(gridBagLayout);
        panelRight.setBackground(Color.LIGHT_GRAY);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 10, 10, 5);
        panelLeft.add(currentLabel, c);

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(20, 0, 10, 0);
        panelLeft.add(currentField, c);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(20, 5, 10, 15);
        panelLeft.add(currentUnit, c);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 10, 10, 1);
        panelLeft.add(voltageLabel, c);

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 10, 0);
        panelLeft.add(voltageField, c);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(0, 5, 10, 15);
        panelLeft.add(voltageUnit, c);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 10, 10, 1);
        panelLeft.add(powerLabel, c);

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 10, 0);
        panelLeft.add(powerField, c);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(0, 5, 20, 15);
        panelLeft.add(powerUnit, c);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 10, 10, 2);
        panelRight.add(currentRmsLabel, c);

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(20, 0, 10, 0);
        panelRight.add(currentRmsField, c);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(20, 5, 10, 15);
        panelRight.add(currentRmsUnit, c);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 10, 10, 2);
        panelRight.add(voltageRmsLabel, c);

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 10, 0);
        panelRight.add(voltageRmsField, c);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(0, 5, 10, 15);
        panelRight.add(voltageRmsUnit, c);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 10, 10);
        panelRight.add(angleLabel, c);

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 10, 0);
        panelRight.add(angleField, c);

        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(0, 5, 20, 15);
        panelRight.add(angleUnit, c);

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 20, 20, 10);
        panel.add(panelLeft, c);

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(20, 10, 20, 20);
        panel.add(panelRight, c);

        pack();
        setVisible(true);

    }

}
