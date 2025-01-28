package com.rlcsim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ProbeTool {
    private Point blackProbeLocation;
    private Point redProbeLocation;
    private Wire blackWire;
    private Wire redWire;
    private ImageIcon redProbe;
    private ImageIcon blackProbe;

    public ProbeTool() {
        blackProbeLocation = null;
        redProbeLocation = null;
        blackWire = null;
        redWire = null;

        ImageIcon imageIcon = new ImageIcon("PROBE.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(30, 30,
                java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        redProbe = imageIcon;

        imageIcon = new ImageIcon("BLACKPROBE.png");
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(30, 30,
                java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        blackProbe = imageIcon;

    }

    public void setProbe(ArrayList<Wire> wires, Point location) {

        for (int i = 0; i < wires.size() && redWire != null && blackWire == null; i++) {
            ArrayList<Point> allPoints = new ArrayList<>();
            allPoints.addAll(wires.get(i).getEndPoints());
            allPoints.addAll(wires.get(i).getPoints());
            for (Point point : allPoints) {
                if (point.equals(location)) {
                    blackWire = wires.get(i);
                    blackProbeLocation = point;
                    return;
                }
            }
        }

        for (int i = 0; i < wires.size() && redWire == null; i++) {
            ArrayList<Point> allPoints = new ArrayList<>();
            allPoints.addAll(wires.get(i).getEndPoints());
            allPoints.addAll(wires.get(i).getPoints());
            for (Point point : allPoints) {
                if (point.equals(location)) {
                    redWire = wires.get(i);
                    redProbeLocation = point;
                    return;
                }
            }
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        if (blackProbeLocation != null) {
            g2d.setColor(Color.BLACK);
            int gridSize = DrawPanel.getGridSize();
            int x = blackProbeLocation.x * gridSize;
            int y = blackProbeLocation.y * gridSize;

            g2d.drawImage(blackProbe.getImage(), x, y - gridSize, 30, 30, Color.WHITE, null);

        }
        if (redProbeLocation != null) {
            g2d.setColor(Color.RED);
            int gridSize = DrawPanel.getGridSize();
            int x = redProbeLocation.x * gridSize;
            int y = redProbeLocation.y * gridSize;

            g2d.drawImage(redProbe.getImage(), x, y - gridSize, 30, 30, Color.WHITE, null);
        }

        g2d.dispose();

    }

    public boolean isReady() {
        if (blackWire != null && redWire != null) {
            return true;
        }
        return false;
    }

    public void resetProbes() {
        blackProbeLocation = null;
        redProbeLocation = null;
        blackWire = null;
        redWire = null;

    }

    public Pair<Wire, Wire> getWires() {
        return new Pair<>(redWire, blackWire);
    }

}
