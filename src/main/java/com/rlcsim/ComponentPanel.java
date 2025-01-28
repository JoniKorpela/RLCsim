package com.rlcsim;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ComponentPanel extends JPanel implements Subject, Listener, ActionListener {

        private ArrayList<Listener> listeners;
        private JButton button1 = new JButton();
        private JButton button2 = new JButton();
        private JButton button3 = new JButton();
        private JButton button4 = new JButton();
        private JButton button5 = new JButton();
        private JButton button6 = new JButton();
        private JButton button7 = new JButton();
        private JButton button8 = new JButton();
        private JButton button9 = new JButton();
        private JButton button10 = new JButton();
        private JButton button11 = new JButton("RUN");

        public ComponentPanel() {

                listeners = new ArrayList<Listener>(25);

                button1.setActionCommand("Probe");
                button2.setActionCommand("Remove");
                button3.setActionCommand("Resistor");
                button4.setActionCommand("Inductor");
                button5.setActionCommand("Capacitor");
                button6.setActionCommand("Wire");
                button7.setActionCommand("Voltage Meter");
                button8.setActionCommand("Current Meter");
                button9.setActionCommand("Voltage Source");
                button10.setActionCommand("Current Source");
                button11.setActionCommand("RUN");
                button11.setBackground(Color.GREEN);

                // HTML must be used in order to add newlines to tooltips.
                button1.setToolTipText("<html>" + "Probe tool" + "<br>" + "(P)" + "</html>");
                button2.setToolTipText("<html>" + "Remove a component" + "<br>" + "(D, DEL)" + "</html>");
                button3.setToolTipText("<html>" + "Add a resistor" + "<br>" + "(R)" + "</html>");
                button4.setToolTipText("<html>" + "Add a inductor" + "<br>" + "(L)" + "</html>");
                button5.setToolTipText("<html>" + "Add a capacitor" + "<br>" + "(C)" + "</html>");
                button6.setToolTipText("<html>" + "Add a wire" + "<br>" + "(W)" + "</html>");
                button7.setToolTipText("<html>" + "Add a voltmeter" + "<br>" + "(SHIFT + V)" + "</html>");
                button8.setToolTipText("<html>" + "Add an ammeter" + "<br>" + "(SHIFT + I)" + "</html>");
                button9.setToolTipText("<html>" + "Add a voltage source" + "<br>" + "(V)" + "</html>");
                button10.setToolTipText("<html>" + "Add a current source" + "<br>" + "(I)" + "</html>");
                button11.setToolTipText("<html>" + "Simulate circuit" + "<br>" + "(SPACEBAR)" + "</html>");

                button1.addActionListener(this);
                button2.addActionListener(this);
                button3.addActionListener(this);
                button4.addActionListener(this);
                button5.addActionListener(this);
                button6.addActionListener(this);
                button7.addActionListener(this);
                button8.addActionListener(this);
                button9.addActionListener(this);
                button10.addActionListener(this);
                button11.addActionListener(this);

                button1.setEnabled(false);

                Dimension buttDimension = new Dimension(140, 140);
                setBackground(Color.BLACK);

                MatteBorder border = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);

                setBorder(border);

                button1.setPreferredSize(buttDimension);
                button2.setPreferredSize(buttDimension);
                button3.setPreferredSize(buttDimension);
                button4.setPreferredSize(buttDimension);
                button5.setPreferredSize(buttDimension);
                button6.setPreferredSize(buttDimension);
                button7.setPreferredSize(buttDimension);
                button8.setPreferredSize(buttDimension);
                button9.setPreferredSize(buttDimension);
                button10.setPreferredSize(buttDimension);
                button11.setPreferredSize(buttDimension);

                int height = (int) (buttDimension.getHeight() - 50);
                int width = (int) (buttDimension.getWidth() - 50);

                ImageIcon imageIcon = new ImageIcon("images/L.png");
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(width, height,
                                java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                button4.setIcon(imageIcon);

                imageIcon = new ImageIcon("images/Rtest.png");
                image = imageIcon.getImage();
                newimg = image.getScaledInstance(width, height,
                                java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                button3.setIcon(imageIcon);

                imageIcon = new ImageIcon("images/C.png");
                image = imageIcon.getImage();
                newimg = image.getScaledInstance(width, height,
                                java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                button5.setIcon(imageIcon);

                imageIcon = new ImageIcon("images/WIRE.png");
                image = imageIcon.getImage();
                newimg = image.getScaledInstance(width, height,
                                java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                button6.setIcon(imageIcon);

                imageIcon = new ImageIcon("images/PROBE.png");
                image = imageIcon.getImage();
                newimg = image.getScaledInstance(width, height,
                                java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                button1.setIcon(imageIcon);

                imageIcon = new ImageIcon("images/DELETE.png");
                image = imageIcon.getImage();
                newimg = image.getScaledInstance(width, height,
                                java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                button2.setIcon(imageIcon);

                imageIcon = new ImageIcon("images/AMMETER.png");
                image = imageIcon.getImage();
                newimg = image.getScaledInstance(width, height,
                                java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                button8.setIcon(imageIcon);

                imageIcon = new ImageIcon("images/VOLTAGESOURCE.png");
                image = imageIcon.getImage();
                newimg = image.getScaledInstance(width, height,
                                java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                button9.setIcon(imageIcon);

                imageIcon = new ImageIcon("images/VOLTMETER.png");
                image = imageIcon.getImage();
                newimg = image.getScaledInstance(width, height,
                                java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                button7.setIcon(imageIcon);

                imageIcon = new ImageIcon("images/CURRENTSOURCE.png");
                image = imageIcon.getImage();
                newimg = image.getScaledInstance(width, height,
                                java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                button10.setIcon(imageIcon);

                imageIcon = new ImageIcon("images/RUN.png");
                image = imageIcon.getImage();
                newimg = image.getScaledInstance(width, height,
                                java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                button11.setIcon(imageIcon);

                GridBagLayout gridBagLayout = new GridBagLayout();
                GridBagConstraints gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.fill = GridBagConstraints.BOTH;
                gridBagConstraints.anchor = GridBagConstraints.WEST;
                gridBagConstraints.weighty = 1;
                gridBagConstraints.weightx = 1;

                this.setLayout(gridBagLayout);

                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                this.add(button1, gridBagConstraints);

                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 0;
                this.add(button2, gridBagConstraints);

                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 2;
                this.add(button3, gridBagConstraints);

                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 2;
                this.add(button4, gridBagConstraints);

                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 3;
                this.add(button5, gridBagConstraints);

                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 3;
                this.add(button6, gridBagConstraints);

                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 4;
                this.add(button7, gridBagConstraints);

                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 4;
                this.add(button8, gridBagConstraints);

                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 5;
                this.add(button9, gridBagConstraints);

                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 5;
                this.add(button10, gridBagConstraints);

                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 6;
                gridBagConstraints.gridwidth = 2;
                this.add(button11, gridBagConstraints);

        }

        @Override
        public void addListener(Listener listener) {
                listeners.add(listener);
        }

        @Override
        public void removeListener(Listener listener) {
                listeners.remove(listener);
        }

        @Override
        public void notifyListeners(String button) {
                for (Listener listener : listeners) {
                        listener.update(button);
                }
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
                notifyListeners(arg0.getActionCommand());
        }

        @Override
        public void update(String update) {
                if (update.equals("hasSource")) {
                        button9.setEnabled(false);
                        button10.setEnabled(false);
                } else if (update.equals("sourceDeleted")) {
                        button9.setEnabled(true);
                        button10.setEnabled(true);
                } else if (update.equals("simulationStarted")) {
                        button1.setEnabled(true);
                        button2.setEnabled(false);
                        button3.setEnabled(false);
                        button4.setEnabled(false);
                        button5.setEnabled(false);
                        button6.setEnabled(false);
                        button7.setEnabled(false);
                        button8.setEnabled(false);
                        button9.setEnabled(false);
                        button10.setEnabled(false);
                        button11.setText("STOP");
                        button11.setBackground(Color.RED);
                        button11.setActionCommand("STOP");
                        button11.setToolTipText(
                                        "<html>" + "Stop simulating circuit" + "<br>" + "(SPACEBAR)" + "</html>");
                        int height = (int) (140 - 50);
                        int width = (int) (140 - 50);

                        ImageIcon imageIcon = new ImageIcon("STOP.png");
                        Image image = imageIcon.getImage();
                        Image newimg = image.getScaledInstance(width, height,
                                        java.awt.Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(newimg);
                        button11.setIcon(imageIcon);

                } else if (update.equals("simulationStopped")) {
                        button1.setEnabled(false);
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                        button4.setEnabled(true);
                        button5.setEnabled(true);
                        button6.setEnabled(true);
                        button7.setEnabled(true);
                        button8.setEnabled(true);
                        button9.setEnabled(true);
                        button10.setEnabled(true);
                        button11.setText("RUN");
                        button11.setBackground(Color.GREEN);
                        button11.setActionCommand("RUN");
                        button11.setToolTipText("<html>" + "Simulate circuit" + "<br>" + "(SPACEBAR)" + "</html>");

                        int height = (int) (140 - 50);
                        int width = (int) (140 - 50);

                        ImageIcon imageIcon = new ImageIcon("RUN.png");
                        Image image = imageIcon.getImage();
                        Image newimg = image.getScaledInstance(width, height,
                                        java.awt.Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(newimg);
                        button11.setIcon(imageIcon);

                }
        }

}
