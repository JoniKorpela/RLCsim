package com.rlcsim;

import java.awt.Graphics;
import java.awt.Point;

public abstract class PowerSource extends ElectricalComponent {

    private String dcOffset = "";
    private String frequency = "";
    private String phaseValue = "";
    private boolean ac;

    public PowerSource(Point location, int gridSize, boolean rotate) {
        super(location, gridSize, rotate);
        this.ac = false;
    }

    public void paint(Graphics g) {
        super.paint(g);

    }

    public void setDcOffset(String dcOffset) {
        this.dcOffset = dcOffset;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setPhaseValue(String phase) {
        this.phaseValue = phase;
    }

    public void setAc(boolean isAC) {
        this.ac = isAC;
    }

    public String getDcOffset() {
        return dcOffset;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getPhaseValue() {
        return phaseValue;
    }

    public boolean getAc() {
        return ac;
    }
}
