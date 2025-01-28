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

public class ProbeWindow extends JDialog {

    public ProbeWindow(boolean modal, Pair<Wire, Wire> wires) {

        super(MainWindow.getMainWindow(), modal);
        setTitle("Probe");
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        add(panel);

        Double voltageValuePositiveTerminal = Double.valueOf(wires.getFirst().getVoltage());
        Double voltageValueNegativeTerminal = Double.valueOf(wires.getSecond().getVoltage());
        Double voltageDiff = voltageValuePositiveTerminal - voltageValueNegativeTerminal;

        Double angleValuePositiveTerminal = Double.valueOf(wires.getFirst().getPhase());
        Double angleValueNegativeTerminal = Double.valueOf(wires.getSecond().getPhase());
        Double angleDiff = angleValuePositiveTerminal - angleValueNegativeTerminal;

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(gridBagLayout);

        Font labelFont = new Font("Dialog", Font.BOLD, 25);
        Font textFieldFont = new Font("Dialog", Font.BOLD, 18);
        Dimension textFieldDimension = new Dimension(100, 30);

        JLabel positiveLabel = new JLabel("U+");
        JLabel positiveVoltageUnit = new JLabel("V");
        JLabel positiveAngleUnit = new JLabel("°");
        JTextField positiveVoltage = new JTextField("10");
        JTextField positiveAngle = new JTextField("0");
        JPanel positivePanel = new JPanel();
        positiveVoltage.setText(String.format("%.2f", voltageValuePositiveTerminal));
        positiveAngle.setText(String.format("%.2f", angleValuePositiveTerminal));

        positiveVoltage.setFocusable(false);
        positiveAngle.setFocusable(false);

        positiveLabel.setFont(labelFont);
        positiveVoltageUnit.setFont(labelFont);
        positiveAngleUnit.setFont(labelFont);
        positiveVoltage.setFont(textFieldFont);
        positiveAngle.setFont(textFieldFont);
        positiveVoltage.setHorizontalAlignment(JTextField.CENTER);
        positiveAngle.setHorizontalAlignment(JTextField.CENTER);
        positiveVoltage.setPreferredSize(textFieldDimension);
        positiveAngle.setPreferredSize(textFieldDimension);

        JLabel negativeLabel = new JLabel("U-");
        JLabel negativeAngleUnit = new JLabel("°");
        JLabel negativeVoltageUnit = new JLabel("V");
        JTextField negativeVoltage = new JTextField("");
        JTextField negativeAngle = new JTextField("0");
        JPanel negativePanel = new JPanel();

        negativeVoltage.setFocusable(false);
        negativeAngle.setFocusable(false);

        negativeLabel.setFont(labelFont);
        negativeVoltageUnit.setFont(labelFont);
        negativeAngleUnit.setFont(labelFont);
        negativeVoltage.setFont(textFieldFont);
        negativeAngle.setFont(textFieldFont);
        negativeVoltage.setHorizontalAlignment(JTextField.CENTER);
        negativeAngle.setHorizontalAlignment(JTextField.CENTER);
        negativeVoltage.setPreferredSize(textFieldDimension);
        negativeAngle.setPreferredSize(textFieldDimension);
        negativeVoltage.setText(String.format("%.2f", voltageValueNegativeTerminal));
        negativeAngle.setText(String.format("%.2f", angleValueNegativeTerminal));

        JLabel diffLabel = new JLabel("UΔ");
        JLabel diffAngleUnit = new JLabel("°");
        JLabel diffVoltageUnit = new JLabel("V");
        JTextField diffVoltage = new JTextField("");
        JTextField diffAngle = new JTextField("0");
        JPanel diffPanel = new JPanel();

        diffLabel.setFont(labelFont);
        diffVoltageUnit.setFont(labelFont);
        diffAngleUnit.setFont(labelFont);
        diffVoltage.setFont(textFieldFont);
        diffAngle.setFont(textFieldFont);
        diffVoltage.setHorizontalAlignment(JTextField.CENTER);
        diffAngle.setHorizontalAlignment(JTextField.CENTER);
        diffVoltage.setPreferredSize(textFieldDimension);
        diffAngle.setPreferredSize(textFieldDimension);
        diffVoltage.setText(String.format("%.2f", voltageDiff));
        diffAngle.setText(String.format("%.2f", angleDiff));

        diffVoltage.setFocusable(false);
        diffAngle.setFocusable(false);

        GridBagConstraints c2 = new GridBagConstraints();

        positivePanel.setLayout(gridBagLayout);

        c2.gridx = 0;
        c2.gridy = 0;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.fill = GridBagConstraints.NONE;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.gridheight = 2;
        c2.insets = new Insets(0, 7, 5, 7);
        positivePanel.add(positiveLabel, c2);

        c2.gridheight = 1;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.fill = GridBagConstraints.NONE;
        c2.weightx = 1;
        c2.weighty = 1;

        c2.insets = new Insets(15, 0, 0, 0);
        c2.gridx = 1;
        c2.gridy = 0;
        positivePanel.add(positiveVoltage, c2);

        c2.insets = new Insets(0, 0, 15, 0);
        c2.gridx = 1;
        c2.gridy = 1;
        positivePanel.add(positiveAngle, c2);

        c2.fill = GridBagConstraints.BOTH;

        c2.weightx = 1;
        c2.weighty = 1;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.insets = new Insets(15, 0, 0, 0);
        c2.gridx = 2;
        c2.gridy = 0;
        positivePanel.add(positiveVoltageUnit, c2);

        c2.anchor = GridBagConstraints.LINE_START;
        c2.gridx = 2;
        c2.insets = new Insets(0, 2, 15, 0);
        c2.gridy = 1;
        positivePanel.add(positiveAngleUnit, c2);

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(20, 25, 5, 25);
        panel.add(positivePanel, c);

        negativePanel.setLayout(gridBagLayout);

        c2.gridx = 0;
        c2.gridy = 0;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.fill = GridBagConstraints.NONE;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.gridheight = 2;
        c2.insets = new Insets(0, 10, 5, 0);
        negativePanel.add(negativeLabel, c2);

        c2.gridheight = 1;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.fill = GridBagConstraints.NONE;
        c2.weightx = 1;
        c2.weighty = 1;

        c2.insets = new Insets(15, 8, 0, 0);
        c2.gridx = 1;
        c2.gridy = 0;
        negativePanel.add(negativeVoltage, c2);

        c2.insets = new Insets(0, 8, 15, 0);
        c2.gridx = 1;
        c2.gridy = 1;
        negativePanel.add(negativeAngle, c2);

        c2.fill = GridBagConstraints.BOTH;

        c2.weightx = 1;
        c2.weighty = 1;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.insets = new Insets(15, 0, 0, 0);
        c2.gridx = 2;
        c2.gridy = 0;
        negativePanel.add(negativeVoltageUnit, c2);

        c2.anchor = GridBagConstraints.LINE_START;
        c2.gridx = 2;
        c2.insets = new Insets(0, 2, 15, 0);
        c2.gridy = 1;
        negativePanel.add(negativeAngleUnit, c2);

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(5, 25, 5, 25);
        panel.add(negativePanel, c);

        diffPanel.setLayout(gridBagLayout);

        c2.gridx = 0;
        c2.gridy = 0;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.fill = GridBagConstraints.NONE;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.gridheight = 2;
        c2.insets = new Insets(0, 5, 5, 3);
        diffPanel.add(diffLabel, c2);

        c2.gridheight = 1;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.fill = GridBagConstraints.NONE;
        c2.weightx = 1;
        c2.weighty = 1;

        c2.insets = new Insets(15, 0, 0, 0);
        c2.gridx = 1;
        c2.gridy = 0;
        diffPanel.add(diffVoltage, c2);

        c2.insets = new Insets(0, 0, 15, 0);
        c2.gridx = 1;
        c2.gridy = 1;
        diffPanel.add(diffAngle, c2);

        c2.fill = GridBagConstraints.BOTH;

        c2.weightx = 1;
        c2.weighty = 1;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.insets = new Insets(15, 0, 0, 0);
        c2.gridx = 2;
        c2.gridy = 0;
        diffPanel.add(diffVoltageUnit, c2);

        c2.anchor = GridBagConstraints.LINE_START;
        c2.gridx = 2;
        c2.insets = new Insets(0, 2, 15, 0);
        c2.gridy = 1;
        diffPanel.add(diffAngleUnit, c2);

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(5, 25, 20, 25);
        panel.add(diffPanel, c);

        positivePanel.setBackground(Color.lightGray);
        negativePanel.setBackground(Color.lightGray);
        diffPanel.setBackground(Color.lightGray);
        positivePanel.setPreferredSize(new Dimension(180, 100));
        negativePanel.setPreferredSize(new Dimension(180, 100));
        diffPanel.setPreferredSize(new Dimension(180, 100));

        pack();
        setLocationRelativeTo(MainWindow.getMainWindow());
        setVisible(true);

    }

}
