package com.rlcsim;

import java.util.ArrayList;
import java.awt.Point;

public class Connections {

    public Connections() {

    }

    // Sets connections for components.
    public static void setConnections(ArrayList<ElectricalComponent> components, ArrayList<Wire> wires) {

        for (ElectricalComponent component : components) {
            component.setNegativeConnection(null);
            component.setPositiveConnection(null);
            for (Wire wire : wires) {
                ArrayList<Point> allPoints = new ArrayList<>();
                allPoints.addAll(wire.getEndPoints());
                allPoints.addAll(wire.getPoints());

                for (Point point : allPoints) {
                    if (point.equals(component.getPositiveTerminal()) && (component.getPositiveConnection() == null)) {
                        component.setPositiveConnection(wire);
                    }
                    if (point.equals(component.getNegativeTerminal()) && (component.getNegativeConnection() == null)) {
                        component.setNegativeConnection(wire);
                    }

                }

            }

        }

    }

}
