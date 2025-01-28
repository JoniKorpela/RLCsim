package com.rlcsim;

import java.awt.Graphics;
import java.awt.Point;

public class VoltageSource extends PowerSource {

    public VoltageSource(Point location, int gridSize, boolean rotate) {
        super(location, gridSize, rotate);
        this.setComponentValue("10");
        this.setName("U");
    }

    public void paint(Graphics g) {

        super.paint(g);

        String[] attributes = { getName(), getValue(), getDcOffset(), getFrequency(), getPhaseValue() };

        ComponentPainter.paintVoltageSource(g, getLocation(), getRotate(), getAc(), attributes);

    }

}