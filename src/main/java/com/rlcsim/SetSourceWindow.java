package com.rlcsim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SetSourceWindow extends JDialog {

    JFrame parentFrame;

    public SetSourceWindow(boolean modal, PowerSource powerSource, ComponentHandler componentHandler) {
        super(MainWindow.getMainWindow(), modal);
        parentFrame = MainWindow.getMainWindow();
        setTitle("Set Source");
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
        JLabel amplitudeLabel = new JLabel("Amplitude");
        JLabel offsetLabel = new JLabel("DC-offset");
        JLabel frequencyLabel = new JLabel("Frequency");
        JLabel phaseLabel = new JLabel("Phase");
        JLabel typeLabel = new JLabel("Type");

        nameLabel.setFont(labelFont);
        amplitudeLabel.setFont(labelFont);
        offsetLabel.setFont(labelFont);
        frequencyLabel.setFont(labelFont);
        phaseLabel.setFont(labelFont);
        typeLabel.setFont(labelFont);

        JLabel amplitudeUnitLabel = new JLabel("");
        JLabel offsetUnitLabel = new JLabel("");
        JLabel frequencytUnitLabel = new JLabel("Hz");
        JLabel phaseUnitLabel = new JLabel("°");

        amplitudeUnitLabel.setFont(labelFont);
        offsetUnitLabel.setFont(labelFont);
        frequencytUnitLabel.setFont(labelFont);
        phaseUnitLabel.setFont(labelFont);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        ButtonGroup radioButtonGroup = new ButtonGroup();
        JRadioButton dcButton = new JRadioButton("DC");
        JRadioButton acButton = new JRadioButton("AC");

        radioButtonGroup.add(dcButton);
        radioButtonGroup.add(acButton);

        JPanel radioButtonpanel = new JPanel();
        radioButtonpanel.setLayout(new GridLayout(1, 2));
        radioButtonpanel.add(dcButton);
        radioButtonpanel.add(acButton);
        dcButton.setBackground(Color.WHITE);
        acButton.setBackground(Color.WHITE);

        okButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.setPreferredSize(new Dimension(100, 30));

        JTextField nameField = new JTextField();
        JTextField amplitudeField = new JTextField();
        JTextField offsetField = new JTextField();
        JTextField frequencyField = new JTextField();
        JTextField phaseField = new JTextField();

        nameField.setText(powerSource.getName());
        amplitudeField.setText(powerSource.getValue());

        nameField.setFont(textFieldFont);
        amplitudeField.setFont(textFieldFont);
        offsetField.setFont(textFieldFont);
        frequencyField.setFont(textFieldFont);
        phaseField.setFont(textFieldFont);

        nameField.setPreferredSize(textFieldDimension);
        amplitudeField.setPreferredSize(textFieldDimension);
        offsetField.setPreferredSize(textFieldDimension);
        frequencyField.setPreferredSize(textFieldDimension);
        phaseField.setPreferredSize(textFieldDimension);

        nameField.setHorizontalAlignment(JTextField.CENTER);
        amplitudeField.setHorizontalAlignment(JTextField.CENTER);
        offsetField.setHorizontalAlignment(JTextField.CENTER);
        frequencyField.setHorizontalAlignment(JTextField.CENTER);
        phaseField.setHorizontalAlignment(JTextField.CENTER);

        if (powerSource instanceof VoltageSource) {
            amplitudeUnitLabel.setText("V");
            offsetUnitLabel.setText("V");
        } else if (powerSource instanceof CurrentSource) {
            amplitudeUnitLabel.setText("A");
            offsetUnitLabel.setText("A");
        }

        if (powerSource.getAc()) {
            acButton.setSelected(true);
            offsetField.setText(powerSource.getDcOffset());
            frequencyField.setText(powerSource.getFrequency());
            phaseField.setText(powerSource.getPhaseValue());

        } else {
            dcButton.setSelected(true);
            offsetField.setEditable(false);
            offsetField.setFocusable(false);
            offsetField.setText("N/A");
            offsetField.setBackground(Color.lightGray);

            frequencyField.setEditable(false);
            frequencyField.setFocusable(false);
            frequencyField.setText("N/A");
            frequencyField.setBackground(Color.lightGray);

            phaseField.setEditable(false);
            phaseField.setFocusable(false);
            phaseField.setText("N/A");
            phaseField.setBackground(Color.lightGray);
        }

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
        subJPanel.add(amplitudeLabel, c2);

        c2.gridx = 0;
        c2.gridy = 2;
        subJPanel.add(offsetLabel, c2);

        c2.gridx = 0;
        c2.gridy = 3;
        subJPanel.add(frequencyLabel, c2);

        c2.gridx = 0;
        c2.gridy = 4;
        subJPanel.add(phaseLabel, c2);

        c2.gridx = 0;
        c2.gridy = 5;
        subJPanel.add(typeLabel, c2);

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
        subJPanel.add(amplitudeField, c2);

        c2.gridx = 1;
        c2.gridy = 2;
        subJPanel.add(offsetField, c2);

        c2.gridx = 1;
        c2.gridy = 3;
        subJPanel.add(frequencyField, c2);

        c2.gridx = 1;
        c2.gridy = 4;
        subJPanel.add(phaseField, c2);

        c2.gridx = 1;
        c2.gridy = 2;
        subJPanel.add(offsetField, c2);

        c2.gridx = 1;
        c2.gridy = 3;
        subJPanel.add(frequencyField, c2);

        c2.gridx = 1;
        c2.gridy = 4;
        subJPanel.add(phaseField, c2);

        c2.fill = GridBagConstraints.NONE;
        c2.gridx = 1;
        c2.gridy = 5;
        subJPanel.add(radioButtonpanel, c2);

        c2.fill = GridBagConstraints.NONE;
        c2.gridwidth = GridBagConstraints.REMAINDER;
        c2.anchor = GridBagConstraints.LINE_START;
        c2.gridwidth = 1;
        c2.weightx = 0;
        c2.insets = new Insets(10, 5, 10, 5);
        c2.gridx = 2;
        c2.gridy = 1;
        subJPanel.add(amplitudeUnitLabel, c2);

        c2.gridx = 2;
        c2.gridy = 2;
        subJPanel.add(offsetUnitLabel, c2);

        c2.gridx = 2;
        c2.gridy = 3;

        subJPanel.add(frequencytUnitLabel, c2);
        c2.gridx = 2;
        c2.gridy = 4;
        subJPanel.add(phaseUnitLabel, c2);

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

                if (acButton.isSelected()) {
                    powerSource.setAc(true);
                    if (!checkAmplitude(amplitudeField.getText())) {
                        new WarningDialogWindow(true, "Amplitude has to be a positive \nnumerical value.");
                    } else if (!checkOffset(offsetField.getText())) {
                        new WarningDialogWindow(true, "Offset has to be a positive \nnumerical value.");
                    } else if (!checkFrequency(frequencyField.getText())) {
                        new WarningDialogWindow(true, "Frequency must be a positive \nnumerical value.");
                    } else if (!checkPhase(phaseField.getText())) {
                        new WarningDialogWindow(true, "Phase must be a numerical value.");
                    } else {
                        powerSource.setDcOffset(offsetField.getText());
                        powerSource.setFrequency(frequencyField.getText());
                        powerSource.setPhaseValue(phaseField.getText());
                        powerSource.setValue(amplitudeField.getText());
                        powerSource.setName(nameField.getText());
                        dispose();
                    }
                } else {
                    if (!checkAmplitude(amplitudeField.getText())) {
                        new WarningDialogWindow(true, "Amplitude has to be a positive \nnumerical value.");
                    } else if (!checkName(nameField.getText())) {
                        new WarningDialogWindow(true, "Name must not be empty.");
                    } else {
                        powerSource.setValue(amplitudeField.getText());
                        powerSource.setAc(false);
                        powerSource.setDcOffset("");
                        powerSource.setFrequency("");
                        powerSource.setPhaseValue("");
                        powerSource.setName(nameField.getText());
                        dispose();
                    }
                }
            }

        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }

        });

        dcButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                offsetField.setEditable(false);
                offsetField.setFocusable(false);
                offsetField.setBackground(Color.lightGray);
                offsetField.setText("N/A");

                frequencyField.setEditable(false);
                frequencyField.setFocusable(false);
                frequencyField.setBackground(Color.lightGray);
                frequencyField.setText("N/A");

                phaseField.setEditable(false);
                phaseField.setFocusable(false);
                phaseField.setBackground(Color.lightGray);
                phaseField.setText("N/A");

            }

        });

        acButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                offsetField.setEditable(true);
                offsetField.setFocusable(true);
                offsetField.setText(powerSource.getDcOffset());
                offsetField.setBackground(Color.WHITE);

                frequencyField.setEditable(true);
                frequencyField.setFocusable(true);
                frequencyField.setText(powerSource.getFrequency());
                frequencyField.setBackground(Color.WHITE);

                phaseField.setEditable(true);
                phaseField.setFocusable(true);
                phaseField.setText(powerSource.getPhaseValue());
                phaseField.setBackground(Color.WHITE);

            }

        });

        pack();
        setLocationRelativeTo(parentFrame);
        setVisible(true);

    }

    private boolean checkOffset(String value) {

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

    private boolean checkFrequency(String value) {

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

    private boolean checkPhase(String value) {

        try {
            Double.valueOf(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean checkAmplitude(String value) {

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

    private boolean checkName(String value) {
        return !value.strip().isEmpty();
    }
}
