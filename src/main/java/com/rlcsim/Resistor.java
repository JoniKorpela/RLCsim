package com.rlcsim;

import java.awt.Graphics;
import java.awt.Point;

public class Resistor extends ElectricalComponent {

    private static int count;

    public Resistor(Point location, int gridSize, boolean rotate) {
        super(location, gridSize, rotate);
        this.setComponentValue("100");
        this.setName("R" + count++);
    }

    public void paint(Graphics g) {
        super.paint(g);

        ComponentPainter.paintResistor(g, getName(), getValue(), getLocation(), getRotate());

    }

}
