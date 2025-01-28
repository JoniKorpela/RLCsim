package com.rlcsim;

import java.awt.Graphics;
import java.awt.Point;

public class CurrentSource extends PowerSource {

    public CurrentSource(Point location, int gridSize, boolean rotate) {
        super(location, gridSize, rotate);
        this.setComponentValue("1");
        this.setName("I");
    }

    public void paint(Graphics g) {
        super.paint(g);
        String[] attributes = { getName(), getValue(), getDcOffset(), getFrequency(), getPhaseValue() };
        ComponentPainter.paintCurrentSource(g, getLocation(), getRotate(), getAc(), attributes);

    }

}
