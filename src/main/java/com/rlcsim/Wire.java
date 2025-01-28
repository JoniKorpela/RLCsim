package com.rlcsim;

import java.awt.Point;
import java.util.ArrayList;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

public class Wire implements Serializable {

    private ArrayList<Point> endPointList = new ArrayList<>();
    private ArrayList<Point> pointList = new ArrayList<>();
    private ArrayList<Point> allPointList = new ArrayList<>();
    private ArrayList<Pair<Point, Wire>> connections = new ArrayList<>();
    private Rectangle hitBox;
    private int gridSize;
    private String voltage;
    private String current;
    private String phase;

    public Wire(ArrayList<Point> endPointList, int gridSize, ArrayList<Wire> wires) {
        this.endPointList = endPointList;
        this.gridSize = gridSize;
        calculatePoints();
        setHitBox(gridSize);
    }

    public void setConnection(Pair<Point, Wire> connection) {
        connections.add(connection);
    }

    public void deleteConnection(Wire connWire) {

        int size = connections.size();

        for (int i = size - 1; i >= 0; i--) {
            Pair<Point, Wire> connection = connections.get(i);

            if (connection.getSecond().equals(connWire)) {
                connections.remove(i);
            }

        }

    }

    private void setHitBox(int gridSize) {
        Point startPoint = endPointList.get(0);
        Point endPoint = endPointList.get(1);

        int x0 = (int) Math.min(startPoint.getX(), endPoint.getX());
        int y0 = (int) Math.min(startPoint.getY(), endPoint.getY());
        int x1 = (int) Math.max(startPoint.getX(), endPoint.getX());
        int y1 = (int) Math.max(startPoint.getY(), endPoint.getY());
        int absoluteX = x0 * gridSize;
        int absoluteY = y0 * gridSize;

        int deltaX = x1 - x0;
        int deltaY = y1 - y0;

        if (deltaY == 0) {
            absoluteY -= gridSize / 2;
            hitBox = new Rectangle(absoluteX, absoluteY, deltaX * gridSize, (int) 1.5 * gridSize);
        } else if (deltaX == 0) {
            absoluteX -= gridSize / 2;
            hitBox = new Rectangle(absoluteX, absoluteY, (int) 1.5 * gridSize, deltaY * gridSize);
        }
    }

    private void calculatePoints() {
        Point startPoint = endPointList.get(0);
        Point endPoint = endPointList.get(1);
        int x0 = (int) startPoint.getX();
        int y0 = (int) startPoint.getY();
        int x1 = (int) endPoint.getX();
        int y1 = (int) endPoint.getY();

        int deltaX = Math.abs(x0 - x1);
        int deltaY = Math.abs(y0 - y1);

        if (deltaY == 0) {
            int minX = Math.min(x0, x1);
            int maxX = Math.max(x0, x1);
            for (int x = minX + 1; x < maxX; x++) {
                pointList.add(new Point(x, y1));
            }
        }

        if (deltaX == 0) {
            int minY = Math.min(y0, y1);
            int maxY = Math.max(y0, y1);
            for (int y = minY + 1; y < maxY; y++) {
                pointList.add(new Point(x1, y));
            }
        }
        allPointList.addAll(endPointList);
        allPointList.addAll(pointList);
    }

    public ArrayList<Point> getEndPoints() {
        return endPointList;
    }

    public ArrayList<Point> getPoints() {
        return pointList;
    }

    public ArrayList<Point> getAllPoints() {
        return allPointList;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public boolean isHit(Point point) {
        return hitBox.contains(point);
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getCurrent() {
        return current;
    }

    public String getVoltage() {
        return voltage;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public void paint(Graphics g) {
        Point startPoint = endPointList.get(0);
        Point endPoint = endPointList.get(1);
        int x0 = (int) startPoint.getX();
        int y0 = (int) startPoint.getY();
        int x1 = (int) endPoint.getX();
        int y1 = (int) endPoint.getY();

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke((int) 2.0));
        // g2d.draw(getHitBox());
        g2d.drawLine(x0 * gridSize, y0 * gridSize, x1 * gridSize, y1 * gridSize);

        for (Pair<Point, Wire> connection : connections) {
            int x = (connection.getFirst().x * gridSize) - (gridSize / 8);
            int y = (connection.getFirst().y * gridSize) - (gridSize / 8);
            g2d.fillRect(x, y, gridSize / 4, gridSize / 4);

        }

        g2d.dispose();
    }

}
