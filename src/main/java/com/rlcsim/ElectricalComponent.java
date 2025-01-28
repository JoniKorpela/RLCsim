package com.rlcsim;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

public abstract class ElectricalComponent implements Serializable {

    private Point location;
    private int gridSize;
    private String value;
    private String name;
    private boolean rotate;
    private Point positiveTerminal;
    private Point negativeTerminal;
    private Rectangle hitBox;
    private Wire positiveConnection;
    private Wire negativeConnection;
    private String voltage;
    private String current;
    private String phase;

    public ElectricalComponent(Point location, int gridSize, boolean rotate) {
        this.location = location;
        this.gridSize = gridSize;
        this.rotate = rotate;
        setHitBox();
        positiveTerminal = location;
        if (rotate) {
            negativeTerminal = new Point(location);
            negativeTerminal.y += 4;
        } else {
            negativeTerminal = new Point(location);
            negativeTerminal.x += 4;
        }
    }

    public void paint(Graphics g) {

        if (positiveConnection != null) {
            ComponentPainter.paintConnectionPoint(g, positiveTerminal);

        }
        if (negativeConnection != null) {
            ComponentPainter.paintConnectionPoint(g, negativeTerminal);
        }
    }

    public void setPositiveConnection(Wire wire) {
        this.positiveConnection = wire;
    }

    public void setNegativeConnection(Wire wire) {
        this.negativeConnection = wire;
    }

    public Wire getPositiveConnection() {
        return positiveConnection;
    }

    public Wire getNegativeConnection() {
        return negativeConnection;
    }

    public void deleteConnection(Wire wire) {
        if (wire.equals(positiveConnection)) {
            positiveConnection = null;
        }
        if (wire.equals(negativeConnection)) {
            negativeConnection = null;
        }
    }

    public Point getPositiveTerminal() {
        return positiveTerminal;
    }

    public Point getNegativeTerminal() {
        return negativeTerminal;
    }

    public Point getLocation() {
        return location;
    }

    public int getGridSize() {
        return this.gridSize;
    }

    public String getName() {
        return this.name;
    }

    public boolean getRotate() {
        return this.rotate;
    }

    public String getValue() {
        return this.value;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
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

    private void setHitBox() {
        int absoluteX = location.x * gridSize;
        int absoluteY = location.y * gridSize;
        if (rotate) {
            absoluteX -= gridSize / 1.5;
            hitBox = new Rectangle(absoluteX, absoluteY, (int) (gridSize * 1.5), 4 * gridSize);
        } else {
            absoluteY -= gridSize / 1.5;
            hitBox = new Rectangle(absoluteX, absoluteY, 4 * gridSize, (int) (gridSize * 1.5));
        }
    }

    public boolean isHit(Point point) {
        return hitBox.contains(point);
    }

    public void setComponentValue(String value) {
        this.value = value;
    }
}
