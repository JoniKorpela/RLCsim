package com.rlcsim;

import java.awt.Graphics;
import java.awt.Point;

public class Voltmeter extends ElectricalComponent {

    private String current;

    public Voltmeter(Point location, int gridSize, boolean rotate) {
        super(location, gridSize, rotate);
        this.setName("V");
    }

    public void paint(Graphics g) {
        super.paint(g);
        ComponentPainter.paintVoltmeter(g, getName(), getLocation(), getRotate());
    }

    public void setCurrent(String current) {
        this.current = "0";
    }

    public String getCurrent() {
        return current;
    }

}