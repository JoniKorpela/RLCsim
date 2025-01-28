package com.rlcsim;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Point;

public class ComponentHandler implements Subject, Listener {

    private ArrayList<Listener> listeners = new ArrayList<>();
    private ArrayList<ElectricalComponent> components = new ArrayList<>();
    private ArrayList<Wire> wires = new ArrayList<>();
    private DrawPanel drawPanel;
    private boolean hasSource = false;
    JFrame parentFrame;

    public ComponentHandler(Listener componentPanel, DrawPanel drawPanel, Subject controlPanel) {
        listeners.add(componentPanel);
        this.drawPanel = drawPanel;
        controlPanel.addListener(this);
        parentFrame = drawPanel.getParentFrame();

    }

    public void addComponent(Point location, boolean rotation) {

        ElectricalComponent component = null;
        DrawPanel.State state = drawPanel.getState();

        if (state == DrawPanel.State.RESISTOR) {
            component = new Resistor(location, DrawPanel.getGridSize(), rotation);
        } else if (state == DrawPanel.State.INDUCTOR) {
            component = new Inductor(location, DrawPanel.getGridSize(), rotation);
        } else if (state == DrawPanel.State.CAPACITOR) {
            component = new Capacitor(location, DrawPanel.getGridSize(), rotation);
        } else if (state == DrawPanel.State.VOLTAGESOURCE) {
            component = new VoltageSource(location, DrawPanel.getGridSize(), rotation);
            notifyListeners("hasSource");
            hasSource = true;
            drawPanel.setState(DrawPanel.State.STANDARD);
        } else if (state == DrawPanel.State.CURRENTSOURCE) {
            component = new CurrentSource(location, DrawPanel.getGridSize(), rotation);
            notifyListeners("hasSource");
            hasSource = true;
            drawPanel.setState(DrawPanel.State.STANDARD);
        } else if (state == DrawPanel.State.VOLTMETER) {
            component = new Voltmeter(location, DrawPanel.getGridSize(), rotation);
        } else if (state == DrawPanel.State.AMMETER) {
            component = new Ammeter(location, DrawPanel.getGridSize(), rotation);
        }

        if (component != null) {

            components.add(component);

            for (Wire wire : wires) {
                ArrayList<Point> allPoints = new ArrayList<>();
                allPoints.addAll(wire.getEndPoints());
                allPoints.addAll(wire.getPoints());

                for (Point point : allPoints) {
                    if (point.equals(component.getPositiveTerminal())) {
                        component.setPositiveConnection(wire);
                    }
                    if (point.equals(component.getNegativeTerminal())) {
                        component.setNegativeConnection(wire);
                    }

                }

            }
        }
        setConnections();

    }

    public void addWire(Wire wire) {

        ArrayList<Point> allPoints = new ArrayList<>();
        allPoints.addAll(wire.getEndPoints());
        allPoints.addAll(wire.getPoints());
        for (Point point : allPoints) {
            for (ElectricalComponent component : components) {
                if (point.equals(component.getPositiveTerminal()) && component.getPositiveTerminal() == null) {
                    component.setPositiveConnection(wire);
                }
                if (point.equals(component.getNegativeTerminal()) && component.getPositiveTerminal() == null) {
                    component.setNegativeConnection(wire);
                }
            }
        }
        for (Point point : wire.getEndPoints()) {
            for (Wire wire2 : wires) {

                ArrayList<Point> allPoints2 = new ArrayList<>();
                allPoints2.addAll(wire2.getEndPoints());
                allPoints2.addAll(wire2.getPoints());

                for (Point point2 : allPoints2) {
                    if (point.equals(point2)) {
                        wire2.setConnection(new Pair<Point, Wire>(point, wire));
                    }
                }
            }
        }
        wires.add(wire);
        setConnections();
    }

    public void removeComponent(Point mouseLocation) {

        int size = components.size();

        for (int i = size - 1; i >= 0; i--) {
            ElectricalComponent component = components.get(i);

            if (component.isHit(mouseLocation)) {
                components.remove(i);
                if (component instanceof VoltageSource || component instanceof CurrentSource) {
                    notifyListeners("sourceDeleted");
                    hasSource = false;
                }
                setConnections();
                return;
            }
        }

        size = wires.size();

        for (int i = size - 1; i >= 0; i--) {
            Wire wire = wires.get(i);

            if (wire.isHit(mouseLocation)) {

                removeWire(wire);
                setConnections();
                return;
            }
        }

    }

    private void removeWire(Wire wire) {

        int size = components.size();
        for (int i = size - 1; i >= 0; i--) {
            ElectricalComponent component = components.get(i);
            component.deleteConnection(wire);
        }

        size = wires.size();

        for (int i = size - 1; i >= 0; i--) {
            Wire wire2 = wires.get(i);
            wire2.deleteConnection(wire);
        }

        wires.remove(wire);
    }

    private void setConnections() {

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

    public void showGhost(Graphics g, Point location, int gridSize, Boolean rotation) {

        DrawPanel.State state = drawPanel.getState();

        switch (state) {
            case RESISTOR:
                ComponentPainter.paintResistor(g, "R", "100", location, rotation);
                break;
            case INDUCTOR:
                ComponentPainter.paintInductor(g, "L", "100", location, rotation);
                break;
            case CAPACITOR:
                ComponentPainter.paintCapacitor(g, "C", "100", location, rotation);
                break;
            case VOLTAGESOURCE:
                ComponentPainter.paintVoltageSource(g, location, rotation, false,
                        new String[] { "U", "10", "Testi", "Testi", "Testi" });
                break;
            case CURRENTSOURCE:
                ComponentPainter.paintCurrentSource(g, location, rotation, false,
                        new String[] { "I", "10", "Testi", "Testi", "Testi" });
                break;
            case VOLTMETER:
                ComponentPainter.paintVoltmeter(g, "V", location, rotation);
                break;
            case AMMETER:
                ComponentPainter.paintAmmeter(g, "A", location, rotation);
                break;
            default:
                break;
        }
    }

    public ArrayList<ElectricalComponent> getElectricalComponents() {
        return components;
    }

    public ArrayList<Wire> getWires() {
        return wires;
    }

    public void setComponents(ArrayList<ElectricalComponent> components) {
        notifyListeners("sourceDeleted");
        hasSource = false;

        for (ElectricalComponent component : components) {
            if (component instanceof VoltageSource || component instanceof CurrentSource) {
                notifyListeners("hasSource");
                hasSource = true;
            }

        }
        this.components = components;
    }

    public void setWires(ArrayList<Wire> wires) {
        this.wires = wires;
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListeners(String update) {
        for (Listener listener : listeners) {
            listener.update(update);
        }
    }

    public void setValue(Point mouseLocation, Point mLocationRelativeToFrame) {

        for (ElectricalComponent component : components) {
            if (component.isHit(mouseLocation)) {

                if (component instanceof VoltageSource || component instanceof CurrentSource) {
                    new SetSourceWindow(true, (PowerSource) component, this);
                } else if (component instanceof Resistor || component instanceof Capacitor
                        || component instanceof Inductor) {
                    new SetComponentWindow(true, component, this);
                }
                return;
            }
        }
    }

    public void getValue(Point mouseLocation, Point mLocationRelativeToFrame) {
        for (ElectricalComponent component : components) {
            if (component.isHit(mouseLocation)) {

                if (!(component instanceof VoltageSource) && !(component instanceof CurrentSource)) {
                    new ValueWindow(false, component);
                    return;
                }

            }
        }

    }

    public boolean hasSource() {
        return hasSource;
    }

    public void simulateCircuit() {
        Random random = new Random();

        for (ElectricalComponent component : components) {
            component.setVoltage(String.valueOf(random.nextDouble(100)));
            component.setCurrent(String.valueOf(random.nextDouble(100)));
            component.setPhase(String.valueOf(random.nextDouble(100)));
        }
        for (Wire wire : wires) {
            wire.setVoltage(String.valueOf(random.nextDouble(100)));
            wire.setCurrent(String.valueOf(random.nextDouble(100)));
            wire.setPhase(String.valueOf(random.nextDouble(100)));
        }
        notifyListeners("simulationStarted");

    }

    public void stopSimulation() {
        for (ElectricalComponent component : components) {
            component.setVoltage("");
            component.setCurrent("");
            component.setPhase("");
        }
        for (Wire wire : wires) {
            wire.setVoltage("");
            wire.setCurrent("");
            wire.setPhase("");
        }
        notifyListeners("simulationStopped");
        if (hasSource) {
            notifyListeners("hasSource");
        }

    }

    @Override
    public void update(String command) {
        switch (command) {
            // Commands from ControlPanel:
            case "Save File":
                if (!FileHandler.saveToFile(getElectricalComponents(), getWires())) {
                    new WarningDialogWindow(true, "Failed to save file.");
                }
                break;
            case "Save File as":
                if (!FileHandler.saveAsToFile(getElectricalComponents(), getWires())) {
                    new WarningDialogWindow(true, "Failed to save file.");
                }
                break;
            case "Open File":
                Pair<ArrayList<ElectricalComponent>, ArrayList<Wire>> openFilePair = FileHandler.loadFromFile();
                if (openFilePair != null) {
                    setComponents(openFilePair.getFirst());
                    setWires(openFilePair.getSecond());
                    drawPanel.setState(DrawPanel.State.STANDARD);
                }
                break;
            case "New File":
                if (!FileHandler.checkSavedStatus(components, wires)) {
                    ExitDialogWindow exitDialog = new ExitDialogWindow(true,
                            "Open new file without saving?");
                    if (exitDialog.getSaveButtonPressed()) {
                        if (!FileHandler.saveToFile(components, wires)) {
                            break;
                        }
                    } else if (exitDialog.getCancelButtonPressed()) {
                        break;
                    }
                }

                Pair<ArrayList<ElectricalComponent>, ArrayList<Wire>> newFilePair = FileHandler.newFile();
                setComponents(newFilePair.getFirst());
                setWires(newFilePair.getSecond());
                drawPanel.setState(DrawPanel.State.STANDARD);
                break;
        }
        StatusPanel.updateSaveStatus(FileHandler.checkSavedStatus(components, wires));
    }

    public boolean canSimulate() {
        for (ElectricalComponent component : components) {
            if (component instanceof CurrentSource || component instanceof VoltageSource) {
                if (component.getNegativeConnection() == null || component.getPositiveConnection() == null) {
                    new WarningDialogWindow(true, "Open circuit! Unable to simulate.");
                    return false;
                } else {
                    return true;
                }
            }
        }
        new WarningDialogWindow(true, "No power source! Unable to simulate.");
        return false;
    }

}
