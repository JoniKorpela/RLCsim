package com.rlcsim;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class WireTool {

    private int x0;
    private int y0;
    private int x1;
    private int y1;
    private boolean drawing = false;
    private boolean isReady = false;
    private int gridSize;

    public WireTool() {
    }

    public void paint(Graphics g, int x, int y) {

        gridSize = DrawPanel.getGridSize();
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke((int) 2.0));
        if (drawing) {
            int xMag = (int) Math.abs(x0 - x);
            int yMag = (int) Math.abs(y0 - y);

            if (xMag > yMag) {
                g2d.drawLine(x0 * gridSize, y0 * gridSize, x * gridSize, y0 * gridSize);
            } else {
                g2d.drawLine(x0 * gridSize, y0 * gridSize, x0 * gridSize, y * gridSize);
            }

        }

        g2d.dispose();
    }

    public void draw(int x, int y) {
        if (!drawing) {
            x0 = x;
            y0 = y;
            drawing = true;
            isReady = false;
        } else {
            int xMag = (int) Math.abs(x0 - x);
            int yMag = (int) Math.abs(y0 - y);
            if (xMag > yMag) {
                x1 = x;
                y1 = y0;
            } else {
                x1 = x0;
                y1 = y;
            }
            drawing = false;
            isReady = true;
        }
    }

    public boolean isReady() {
        return isReady;
    }

    public ArrayList<Point> getEndPoints() {
        ArrayList<Point> list = new ArrayList<>();
        Point p1 = new Point(x0, y0);
        Point p2 = new Point(x1, y1);
        list.add(p1);
        list.add(p2);
        return list;
    }

    // Checks whether the given point has hit a component's terminal or a wire.
    // If so, returns null; otherwise returns the latest point of the latest drawn
    // wire.
    public Point terminalIsHit(Point point, ArrayList<Wire> wires, ArrayList<ElectricalComponent> components) {
        for (ElectricalComponent component : components) {
            if (component.getNegativeTerminal().equals(point) || component.getPositiveTerminal().equals(point)) {
                return null;
            }
        }
        for (int i = 0; i < wires.size() - 1; i++) {
            Wire wire = wires.get(i);
            for (Point point2 : wire.getAllPoints()) {
                if (point2.equals(point)) {
                    return null;
                }
            }
        }
        return wires.get(wires.size() - 1).getEndPoints().get(1);
    }

}