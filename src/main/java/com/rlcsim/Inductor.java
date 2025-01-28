package com.rlcsim;

import java.awt.Graphics;
import java.awt.Point;

public class Inductor extends ElectricalComponent {

    private static int count;

    public Inductor(Point location, int gridSize, boolean rotate) {
        super(location, gridSize, rotate);
        this.setComponentValue("100");
        this.setName("L" + count++);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        ComponentPainter.paintInductor(g, getName(), getValue(), getLocation(), getRotate());

    }

}
