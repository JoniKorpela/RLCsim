package com.rlcsim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements Subject, ActionListener {

    private ArrayList<Listener> listeners;

    public ControlPanel() {
        listeners = new ArrayList<Listener>(25);
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridBagLayout);

        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();
        JButton button5 = new JButton();
        JButton button6 = new JButton();

        button1.setActionCommand("Save File");
        button2.setActionCommand("Save File as");
        button3.setActionCommand("Open File");
        button4.setActionCommand("New File");
        button5.setActionCommand("Zoom in");
        button6.setActionCommand("Zoom out");

        // HTML must be used in order to add newlines to tooltips.
        button1.setToolTipText("<html>" + "Save file" + "<br>" + "(CTRL + S)" + "</html>");
        button2.setToolTipText("<html>" + "Save as" + "<br>" + "(F12)" + "</html>");
        button3.setToolTipText("<html>" + "Open a file" + "<br>" + "(CTRL + O)" + "</html>");
        button4.setToolTipText("<html>" + "Open new file" + "<br>" + "(CTRL + N)" + "</html>");
        button5.setToolTipText("<html>" + "Zoom in" + "<br>" + "(+, Scroll down)" + "</html>");
        button6.setToolTipText("<html>" + "Zoom out" + "<br>" + "(-, Scroll up)" + "</html>");

        button1.setPreferredSize(new Dimension(140, 140));
        button2.setPreferredSize(new Dimension(140, 140));
        button3.setPreferredSize(new Dimension(140, 140));
        button4.setPreferredSize(new Dimension(140, 140));
        button5.setPreferredSize(new Dimension(140, 140));
        button6.setPreferredSize(new Dimension(140, 140));

        Dimension buttDimension = new Dimension(140, 140);

        int height = (int) (buttDimension.getHeight() - 50);
        int width = (int) (buttDimension.getWidth() - 50);

        ImageIcon imageIcon = new ImageIcon("images/SAVE.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height,
                java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        button1.setIcon(imageIcon);

        imageIcon = new ImageIcon("images/SAVEAS.png");
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(width, height,
                java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        button2.setIcon(imageIcon);

        imageIcon = new ImageIcon("images/OPENFILE.png");
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(width, height,
                java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        button3.setIcon(imageIcon);

        imageIcon = new ImageIcon("images/NEWFILE.png");
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(width, height,
                java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        button4.setIcon(imageIcon);

        imageIcon = new ImageIcon("images/ZOOMIN.png");
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(width, height,
                java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        button5.setIcon(imageIcon);

        imageIcon = new ImageIcon("images/ZOOMOUT.png");
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(width, height,
                java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        button6.setIcon(imageIcon);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(gridBagLayout);

        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        subPanel.add(button1, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        subPanel.add(button2, c);

        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        subPanel.add(button3, c);

        c.gridx = 3;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        subPanel.add(button4, c);

        c.gridx = 4;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        subPanel.add(button5, c);

        c.gridx = 5;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        subPanel.add(button6, c);

        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(0, 2, 0, 0);

        subPanel.setBackground(Color.LIGHT_GRAY);

        add(subPanel, c);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
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

}
