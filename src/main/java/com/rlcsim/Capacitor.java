package com.rlcsim;

import java.awt.Graphics;
import java.awt.Point;

public class Capacitor extends ElectricalComponent {

    private static int count;

    public Capacitor(Point location, int gridSize, boolean rotate) {
        super(location, gridSize, rotate);
        this.setComponentValue("100");
        this.setName("C" + count++);
    }

    public void paint(Graphics g) {
        super.paint(g);
        ComponentPainter.paintCapacitor(g, getName(), getValue(), getLocation(), getRotate());
    }
}
