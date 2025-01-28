package com.rlcsim;

import java.awt.Graphics;
import java.awt.Point;

public class Ammeter extends ElectricalComponent {

    private String voltage;

    public Ammeter(Point location, int gridSize, boolean rotate) {
        super(location, gridSize, rotate);
        this.setName("A");
    }

    public void paint(Graphics g) {
        super.paint(g);
        ComponentPainter.paintAmmeter(g, getName(), getLocation(), getRotate());
    }

    public void setVoltage(String voltage) {
        this.voltage = "0";
    }

    public String getVoltage() {
        return voltage;
    }

}