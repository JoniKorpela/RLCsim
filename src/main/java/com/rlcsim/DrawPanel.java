package com.rlcsim;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.*;
import javax.swing.border.MatteBorder;

public class DrawPanel extends JPanel implements Listener {

    public static enum State {
        STANDARD, PROBE, REMOVE, RESISTOR, INDUCTOR, CAPACITOR, WIRE, VOLTMETER, AMMETER, VOLTAGESOURCE,
        CURRENTSOURCE, RUN
    }

    private static int gridSize = 20;

    private State currentState = State.STANDARD;
    private double zoomFactor = 1.0;
    private Point gridPointer;
    private boolean componentRotation;
    private WireTool wireTool;
    private ProbeTool probeTool;
    private Point mousePt;
    private Point origin = new Point(0, 0);
    private BufferedImage img;
    private JFrame parentFrame;
    private static BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    private static Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0),
            "blank cursor");

    private static Cursor probeCursor;
    private ComponentHandler componentHandler;

    public DrawPanel(Subject componentPanel, Subject controlPanel) {

        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        this.setBackground(Color.WHITE);
        MatteBorder border = BorderFactory.createMatteBorder(2, 0, 2, 2, Color.BLACK);
        setBorder(border);
        setVisible(true);
        componentPanel.addListener(this);
        controlPanel.addListener(this);
        componentHandler = new ComponentHandler((Listener) componentPanel, this, controlPanel);
        componentRotation = true;

        KeyBindings.setKeyBindings(this, componentHandler, (ControlPanel) controlPanel);

        ImageIcon imageIcon = new ImageIcon("images/PROBE.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(30, 30,
                java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);

        probeCursor = Toolkit.getDefaultToolkit().createCustomCursor(imageIcon.getImage(), new Point(0, 30), "Probe");

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = adjust(e.getPoint());
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                final int x = adjust(new Point(e.getX(), e.getY())).x + Math.abs(origin.x);
                final int y = adjust(new Point(e.getX(), e.getY())).y + Math.abs(origin.y);

                final int xRelativeToFrame = e.getX() + getX();
                final int yRelativeToFrame = e.getY() + getY();

                Point mouseLocation = new Point(x, y);
                Point mLocationRelativeToFrame = new Point(xRelativeToFrame, yRelativeToFrame);

                if (e.getButton() == 1 && currentState == State.WIRE) {

                    Point point = getClosestGridPoint(mouseLocation);

                    wireTool.draw(point.x, point.y);

                    if (wireTool.isReady()) {
                        componentHandler
                                .addWire(new Wire(wireTool.getEndPoints(), gridSize, componentHandler.getWires()));
                        Point newWirePoint = wireTool.terminalIsHit(point, componentHandler.getWires(),
                                componentHandler.getElectricalComponents());
                        if (newWirePoint != null) {
                            wireTool.draw(newWirePoint.x, newWirePoint.y);
                        }
                    }
                }

                else if (e.getButton() == 1 && currentState == State.PROBE) {

                    probeTool.setProbe(componentHandler.getWires(), getClosestGridPoint(mouseLocation));
                    if (probeTool.isReady()) {
                        repaint();
                        new ProbeWindow(true, probeTool.getWires());
                        probeTool.resetProbes();
                    }

                }

                else if (e.getButton() == 1 && currentState == State.REMOVE) {
                    componentHandler.removeComponent(mouseLocation);
                }

                else if (e.getButton() == 1 && currentState != State.STANDARD) {
                    componentHandler.addComponent(getClosestGridPoint(mouseLocation), componentRotation);
                }

                if (e.getButton() == 3 && currentState == State.STANDARD) {
                    componentHandler.setValue(mouseLocation, mLocationRelativeToFrame);
                }

                else if (e.getButton() == 3 &&
                        (currentState == State.AMMETER
                                || currentState == State.VOLTMETER
                                || currentState == State.RESISTOR
                                || currentState == State.INDUCTOR
                                || currentState == State.CAPACITOR
                                || currentState == State.CURRENTSOURCE
                                || currentState == State.VOLTAGESOURCE
                                || currentState == State.WIRE
                                || currentState == State.REMOVE)) {
                    setState(State.STANDARD);
                }

                else if (e.getButton() == 3 && currentState == State.PROBE) {
                    setState(State.PROBE);
                }

                else if (e.getButton() == 3 && currentState == State.RUN) {
                    componentHandler.getValue(mouseLocation, mLocationRelativeToFrame);
                }

                StatusPanel.updateSaveStatus(FileHandler.checkSavedStatus(componentHandler.getElectricalComponents(),
                        componentHandler.getWires()));
                repaint();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setFocusable(true);
                requestFocus();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // setFocusable(false);
            }

        });

        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int ticks = e.getWheelRotation();
                double newZoomFactor = zoomFactor * Math.pow(1.2, ticks);
                setZoomFactor(newZoomFactor);
                origin.setLocation(adjust(new Point(origin)));
                repaint();
            }

        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                int dx = adjust(new Point(e.getX(), e.getY())).x - mousePt.x;
                int dy = adjust(new Point(e.getX(), e.getY())).y - mousePt.y;

                int newOriginX = origin.x + dx;
                int newOriginY = origin.y + dy;

                if (newOriginX > 0) {
                    newOriginX = 0;
                }
                if (newOriginY > 0) {
                    newOriginY = 0;
                }

                origin.setLocation(newOriginX, newOriginY);
                mousePt = adjust(e.getPoint());

                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                final int x = adjust(new Point(e.getX(), e.getY())).x + Math.abs(origin.x);
                final int y = adjust(new Point(e.getX(), e.getY())).y + Math.abs(origin.y);
                gridPointer = getClosestGridPoint(new Point(x, y));
                repaint();

            }

        });

    }

    public Point calcGridSquare(Point point) {
        int pointX = point.x;
        int pointY = point.y;
        int x, y;

        x = (int) (pointX / gridSize);
        y = (int) (pointY / gridSize);

        return new Point(x, y);
    }

    public Point getClosestGridPoint(Point absolutePoint) {
        Point returnPoint = new Point();
        Point gridSquare = calcGridSquare(absolutePoint);
        ArrayList<Point> possiblePoints = new ArrayList<>();

        possiblePoints.add(gridSquare);
        possiblePoints.add(new Point(gridSquare.x, gridSquare.y + 1));
        possiblePoints.add(new Point(gridSquare.x + 1, gridSquare.y));
        possiblePoints.add(new Point(gridSquare.x + 1, gridSquare.y + 1));

        double currentDistance = Double.MAX_VALUE;

        for (Point gridPoint : possiblePoints) {
            double gridPointX = gridPoint.x * gridSize;
            double gridPointY = gridPoint.y * gridSize;

            double calcDistance = Math
                    .sqrt(Math.pow((gridPointX - absolutePoint.x), 2) + Math.pow((gridPointY - absolutePoint.y), 2));

            if (calcDistance < currentDistance) {
                returnPoint = gridPoint;
                currentDistance = calcDistance;
            }
        }

        if (returnPoint.x == 0) {
            returnPoint.x += 1;
        }
        if (returnPoint.y == 0) {
            returnPoint.y += 1;
        }

        return returnPoint;
    }

    public State getState() {
        return currentState;
    }

    // This is horrible
    public void setState(State state) {

        if (state == State.STANDARD || state == State.REMOVE || state == State.RUN) {
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        } else if (state == State.PROBE) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setCursor(probeCursor);
        } else {
            setCursor(blankCursor);
        }

        if (state == State.WIRE) {
            wireTool = new WireTool();
        }

        if ((currentState == State.RUN || currentState == State.PROBE) && state == State.STANDARD) {
            componentHandler.stopSimulation();
        }

        if (state == State.RUN && currentState != State.RUN) {
            if (componentHandler.canSimulate()) {
                componentHandler.simulateCircuit();
            } else {
                return;
            }
        }

        if (state == State.PROBE && currentState == State.RUN) {
            probeTool = new ProbeTool();
        } else if (state == State.PROBE && currentState == State.PROBE) {
            currentState = State.RUN;
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            return;
        }
        currentState = state;
    }

    public boolean getComponentRotation() {
        return componentRotation;
    }

    public void setComponentRotation(boolean rotation) {
        this.componentRotation = rotation;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

        this.img = getCircuitImage();
        if (img != null) {
            g2d.drawImage(img, null, origin.x, origin.y);
        }
        g2d.dispose();

    }

    public BufferedImage getCircuitImage() {

        int h = getHeight() + Math.abs(origin.y);
        int w = getWidth() + Math.abs(origin.x);

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setPaint(new Color(255, 255, 255));

        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2d.setColor(Color.BLUE);

        for (int x = 0; x < w; x += gridSize) {
            for (int y = 0; y < h; y += gridSize) {
                g2d.fillOval(x - 1, y - 1, 2, 2);
            }
        }

        g2d.setColor(Color.BLACK);

        for (ElectricalComponent component : componentHandler.getElectricalComponents()) {
            component.paint(g2d);
        }

        for (Wire wire : componentHandler.getWires()) {
            wire.paint(g2d);
        }

        g2d.setColor(Color.PINK);

        if (gridPointer != null && currentState != State.STANDARD && currentState != State.REMOVE
                && currentState != State.RUN) {
            int x = gridPointer.x * gridSize;
            int y = gridPointer.y * gridSize;
            g2d.fillOval(x - 3, y - 3, 6, 6);
        }

        g2d.setColor(Color.GRAY);

        if (currentState == State.WIRE) {
            int x = gridPointer.x;
            int y = gridPointer.y;
            wireTool.paint(g2d, x, y);
        }

        if (currentState == State.CAPACITOR ||
                currentState == State.RESISTOR ||
                currentState == State.INDUCTOR ||
                currentState == State.AMMETER ||
                currentState == State.VOLTMETER ||
                currentState == State.VOLTAGESOURCE ||
                currentState == State.CURRENTSOURCE) {
            componentHandler.showGhost(g2d, gridPointer, gridSize, componentRotation);
        }

        if (currentState == State.PROBE) {
            probeTool.paint(g2d);
        }

        g2d.dispose();

        return image;
    }

    public static int getGridSize() {
        return gridSize;
    }

    public JFrame getParentFrame() {
        Window parentWindow = SwingUtilities.windowForComponent(this);
        if (parentWindow instanceof JFrame) {
            parentFrame = (JFrame) parentWindow;
        }
        return parentFrame;
    }

    public ComponentHandler getComponentHandler() {
        return componentHandler;
    }

    @Override
    public void update(String command) {
        Double newZoomFactor;
        switch (command) {
            // Commands from ComponentPanel:
            case "Probe":
                setState(State.PROBE);
                break;
            case "Remove":
                setState(State.REMOVE);
                break;
            case "Resistor":
                setState(State.RESISTOR);
                break;
            case "Inductor":
                setState(State.INDUCTOR);
                break;
            case "Capacitor":
                setState(State.CAPACITOR);
                break;
            case "Wire":
                setState(State.WIRE);
                break;
            case "Voltage Meter":
                setState(State.VOLTMETER);
                break;
            case "Current Meter":
                setState(State.AMMETER);
                break;
            case "Voltage Source":
                setState(State.VOLTAGESOURCE);
                break;
            case "Current Source":
                setState(State.CURRENTSOURCE);
                break;
            case "RUN":
                if (componentHandler.canSimulate()) {
                    setState(State.RUN);
                }
                break;
            case "STOP":
                setState(State.STANDARD);
                break;
            // Commands from ControlPanel:
            case "Zoom in":
                newZoomFactor = zoomFactor + 0.25;
                setZoomFactor(newZoomFactor);
                origin.setLocation(adjust(new Point(origin)));
                repaint();
                break;
            case "Zoom out":
                newZoomFactor = zoomFactor - 0.25;
                setZoomFactor(newZoomFactor);
                origin.setLocation(adjust(new Point(origin)));
                repaint();
                break;
            default:
                break;
        }
        repaint();
    }

    // Checks if newZoomFactor is within acceptable limits
    private void setZoomFactor(double newZoomFactor) {
        if (newZoomFactor >= 1.0 && newZoomFactor <= 3) {
            zoomFactor = newZoomFactor;
        }
    }

    // Adjust location of point if zoomFactor != 0
    private Point adjust(Point point) {
        return new Point((int) (point.x / zoomFactor), (int) (point.y / zoomFactor));
    }

}
