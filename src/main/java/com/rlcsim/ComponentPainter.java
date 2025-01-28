package com.rlcsim;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Font;

public class ComponentPainter {

    public static void paintResistor(Graphics g, String name, String value, Point location, boolean rotate) {

        Graphics2D g2d = (Graphics2D) g.create();

        int nameWidth = g2d.getFontMetrics().stringWidth(name);

        int gridSize = DrawPanel.getGridSize();
        int x = location.x * gridSize;
        int y = location.y * gridSize;

        Font font = new Font("Arial", Font.BOLD, 18);
        g2d.setFont(font);

        if (rotate) {
            g2d.drawString(name, x - (int) (gridSize * 0.8) - (int) (nameWidth * 1.5), y + (int) (gridSize * 2.3));
            g2d.drawString(value, x + (int) (gridSize * 0.8), y + (int) (gridSize * 2.3));
            g2d.drawLine(x, y, x, y + (4 * gridSize));
            g2d.fillRect(x - (gridSize / 4), y + gridSize, gridSize / 2, gridSize * 2);
        } else {
            g2d.drawString(name, x + (int) (gridSize * 1.7), y - (int) (gridSize * 0.8));
            g2d.drawString(value, x + (int) (gridSize * 1.5), y + (int) (gridSize * 1.5));
            g2d.drawLine(x, y, x + (4 * gridSize), y);
            g2d.fillRect(x + gridSize, y - (gridSize / 4), gridSize * 2, gridSize / 2);
        }

        g2d.dispose();

    }

    public static void paintInductor(Graphics g, String name, String value, Point location, boolean rotate) {

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke((int) 2.0));

        int nameWidth = g2d.getFontMetrics().stringWidth(name);
        int gridSize = DrawPanel.getGridSize();
        int x = location.x * gridSize;
        int y = location.y * gridSize;

        Font font = new Font("Arial", Font.BOLD, 18);
        g2d.setFont(font);

        if (rotate) {
            g2d.drawString(name, x - (int) (gridSize * 0.5) - (int) (nameWidth * 1.5), y + (int) (gridSize * 2.3));
            g2d.drawString(value, x + (int) (gridSize * 1), y + (int) (gridSize * 2.3));
            g2d.drawLine(x, y, x, y + (gridSize / 2));
            y += gridSize / 2;
            g2d.drawArc(x - (gridSize / 2), y, gridSize, gridSize, -90, 180);
            y += gridSize;
            g2d.drawArc(x - (gridSize / 2), y, gridSize, gridSize, -90, 180);
            y += gridSize;
            g2d.drawArc(x - (gridSize / 2), y, gridSize, gridSize, -90, 180);
            y += gridSize;
            g2d.drawLine(x, y, x, y + (gridSize / 2));
        } else {
            g2d.drawString(name, x + (int) (gridSize * 1.7), y - gridSize);
            g2d.drawString(value, x + (int) (gridSize * 1.5), y + (int) (gridSize * 1.5));
            g2d.drawLine(x, y, x + (gridSize / 2), y);
            x += gridSize / 2;
            g2d.drawArc(x, y - (gridSize / 2), gridSize, gridSize, 0, 180);
            x += gridSize;
            g2d.drawArc(x, y - (gridSize / 2), gridSize, gridSize, 0, 180);
            x += gridSize;
            g2d.drawArc(x, y - (gridSize / 2), gridSize, gridSize, 0, 180);
            x += gridSize;
            g2d.drawLine(x, y, x + gridSize / 2, y);
        }

        g2d.dispose();
    }

    public static void paintCapacitor(Graphics g, String name, String value, Point location, boolean rotate) {

        Graphics2D g2d = (Graphics2D) g.create();

        int nameWidth = g2d.getFontMetrics().stringWidth(name);

        int gridSize = DrawPanel.getGridSize();
        int x = location.x * gridSize;
        int y = location.y * gridSize;
        g2d.setStroke(new BasicStroke((int) 2.0));

        Font font = new Font("Arial", Font.BOLD, 18);
        g2d.setFont(font);
        if (rotate) {
            g2d.drawString(name, x - (int) (gridSize * 1) - (int) (nameWidth * 1.5), y + (int) (gridSize * 2.3));
            g2d.drawString(value, x + (int) (gridSize * 1), y + (int) (gridSize * 2.3));
            g2d.drawLine(x, y, x, (int) (y + (gridSize * 1.75)));
            y += gridSize * 1.75;
            g2d.drawLine(x - (gridSize / 2), y, x + (gridSize / 2), y);
            y += gridSize * 0.5;
            g2d.drawLine(x - (gridSize / 2), y, x + (gridSize / 2), y);
            g2d.drawLine(x, y, x, (int) (y + (gridSize * 1.75)));
        } else {
            g2d.drawString(name, x + (int) (gridSize * 1.5), y - gridSize);
            g2d.drawString(value, x + (int) (gridSize * 1.5), y + (int) (gridSize * 1.5));
            g2d.drawLine(x, y, (int) (x + (gridSize * 1.75)), y);
            x += gridSize * 1.75;
            g2d.drawLine(x, y - (gridSize / 2), x, y + (gridSize / 2));
            x += gridSize * 0.5;
            g2d.drawLine(x, y - (gridSize / 2), x, y + (gridSize / 2));
            g2d.drawLine(x, y, (int) (x + (gridSize * 1.75)), y);
        }

        g2d.dispose();
    }

    public static void paintVoltageSource(Graphics g, Point location, boolean rotate, boolean ac,
            String[] attributes) {

        Graphics2D g2d = (Graphics2D) g.create();

        String name = new String(attributes[0]);
        String value = new String(attributes[1] + "V");
        String offset = new String(attributes[2] + "V");
        String frequency = new String(attributes[3] + "Hz");
        String phase = new String(attributes[4] + "°");

        int gridSize = DrawPanel.getGridSize();
        int x = location.getLocation().x * gridSize;
        int y = location.getLocation().y * gridSize;

        Font font = new Font("Arial", Font.BOLD, 18);
        g2d.setFont(font);

        g2d.setStroke(new BasicStroke((int) 2.0));
        if (rotate) {
            g2d.drawLine(x, y, x, y + gridSize);
            g2d.drawLine(x, y + (3 * gridSize), x, y + (4 * gridSize));
            g2d.drawOval(x - gridSize, y + gridSize, 2 * gridSize, 2 * gridSize);

            int nameWidth = g2d.getFontMetrics().stringWidth(name);
            g2d.drawString(name, x - (int) (gridSize * 1.25) - (int) (nameWidth), y + (int) (gridSize * 2.2));
            g2d.drawString(value, x + (int) (gridSize * 1.5), y + (int) (gridSize * 2.2));

            if (ac) {
                g2d.drawString(offset, x + (int) (gridSize * 1.5), y + (int) (gridSize * 3.2));
                g2d.drawString(frequency, x + (int) (gridSize * 1.5), y + (int) (gridSize * 4.2));
                g2d.drawString(phase, x + (int) (gridSize * 1.5), y + (int) (gridSize * 5.2));

            }

            font = new Font("Arial", Font.BOLD, 24);
            g2d.setFont(font);
            g2d.drawString("+", x - (int) (gridSize / 4) - (int) (gridSize / 16), y + 2 * gridSize);
            g2d.drawString("-", x - (int) (gridSize / 4) + (int) (gridSize / 16), y + (int) (2.75 * gridSize));
        } else {

            g2d.drawLine(x, y, x + gridSize, y);
            g2d.drawLine(x + (3 * gridSize), y, x + (4 * gridSize), y);
            g2d.drawOval(x + gridSize, y - gridSize, 2 * gridSize, 2 * gridSize);

            g2d.drawString(name, x + (int) (gridSize * 1.75), (int) (y - gridSize * 1.2));
            g2d.drawString(value, x + (int) ((gridSize * 1.75)),
                    y + (int) (gridSize * 2));

            if (ac) {
                g2d.drawString(offset, x + (int) (gridSize * 1.75), y + (int) (gridSize * 3));
                g2d.drawString(frequency, x + (int) (gridSize * 1.75), y + (int) (gridSize * 4));
                g2d.drawString(phase, x + (int) (gridSize * 1.75), y + (int) (gridSize * 5));
            }

            font = new Font("Arial", Font.BOLD, 24);
            g2d.setFont(font);
            g2d.drawString("+", x + gridSize + (int) (gridSize / 4), y + (int) (0.35 * gridSize));
            g2d.drawString("-", x + (int) (2.25 * gridSize), y + (int) (0.35 * gridSize));
        }

        g2d.dispose();
    }

    public static void paintCurrentSource(Graphics g, Point location, boolean rotate, boolean ac,
            String[] attributes) {

        Graphics2D g2d = (Graphics2D) g.create();
        String name = new String(attributes[0]);
        String value = new String(attributes[1] + "A");
        String offset = new String(attributes[2] + "A");
        String frequency = new String(attributes[3] + "Hz");
        String phase = new String(attributes[4] + "°");

        int gridSize = DrawPanel.getGridSize();
        int x = location.x * gridSize;
        int y = location.y * gridSize;

        g2d.setStroke(new BasicStroke((int) 2.0));

        Font font = new Font("Arial", Font.BOLD, 18);
        g2d.setFont(font);

        if (rotate) {
            g2d.drawLine(x, y, x, y + gridSize);
            g2d.drawLine(x, y + (3 * gridSize), x, y + (4 * gridSize));
            g2d.drawOval(x - gridSize, y + gridSize, 2 * gridSize, 2 * gridSize);

            int nameWidth = g2d.getFontMetrics().stringWidth(name);
            g2d.drawString(name, x - (int) (gridSize * 1.25) - (int) (nameWidth), y + (int) (gridSize * 2.2));
            g2d.drawString(value, x + (int) (gridSize * 1.5), y + (int) (gridSize * 2.2));

            if (ac) {
                g2d.drawString(offset, x + (int) (gridSize * 1.5), y + (int) (gridSize * 3.2));
                g2d.drawString(frequency, x + (int) (gridSize * 1.5), y + (int) (gridSize * 4.2));
                g2d.drawString(phase, x + (int) (gridSize * 1.5), y + (int) (gridSize * 5.2));

            }

            font = new Font("Arial", Font.BOLD, 35);
            g2d.setFont(font);
            g2d.drawString("↑", x - (int) (0.45 * gridSize), y + (int) (2.4 * gridSize));
        } else {
            g2d.drawLine(x, y, x + gridSize, y);
            g2d.drawLine(x + (3 * gridSize), y, x + (4 * gridSize), y);
            g2d.drawOval(x + gridSize, y - gridSize, 2 * gridSize, 2 * gridSize);
            g2d.drawString(name, x + (int) (gridSize * 1.75), (int) (y - gridSize * 1.2));
            g2d.drawString(value, x + (int) ((gridSize * 1.75)),
                    y + (int) (gridSize * 2));

            if (ac) {
                g2d.drawString(offset, x + (int) (gridSize * 1.75), y + (int) (gridSize * 3));
                g2d.drawString(frequency, x + (int) (gridSize * 1.75), y + (int) (gridSize * 4));
                g2d.drawString(phase, x + (int) (gridSize * 1.75), y + (int) (gridSize * 5));
            }

            font = new Font("Arial", Font.BOLD, 35);
            g2d.setFont(font);
            g2d.drawString("←", x + (int) (1.15 * gridSize), y + (int) (0.45 * gridSize));
        }

        g2d.dispose();
    }

    public static void paintAmmeter(Graphics g, String name, Point location, boolean rotate) {

        Graphics2D g2d = (Graphics2D) g.create();

        int gridSize = DrawPanel.getGridSize();
        int x = location.x * gridSize;
        int y = location.y * gridSize;

        g2d.setStroke(new BasicStroke((int) 2.0));

        if (rotate) {
            g2d.drawLine(x, y, x, y + gridSize);
            g2d.drawLine(x, y + (3 * gridSize), x, y + (4 * gridSize));
            g2d.drawOval(x - gridSize, y + gridSize, 2 * gridSize, 2 * gridSize);

            Font font = new Font("Arial", Font.BOLD, 18);
            g2d.setFont(font);

            g2d.drawString(name, x - (int) (gridSize * 1.75), y + (int) (gridSize * 2.2));

            font = new Font("Arial", Font.BOLD, 35);
            g2d.setFont(font);
            g2d.drawString("A", x - (int) (0.55 * gridSize), y + (int) (2.4 * gridSize));
        } else {
            g2d.drawLine(x, y, x + gridSize, y);
            g2d.drawLine(x + (3 * gridSize), y, x + (4 * gridSize), y);
            g2d.drawOval(x + gridSize, y - gridSize, 2 * gridSize, 2 * gridSize);

            Font font = new Font("Arial", Font.BOLD, 18);
            g2d.setFont(font);

            g2d.drawString(name, x + (int) (1.75 * gridSize), y - (int) (gridSize * 1.25));

            font = new Font("Arial", Font.BOLD, 35);
            g2d.setFont(font);
            g2d.drawString("A", x + (int) (1.5 * gridSize), y + (int) (0.45 * gridSize));
        }

        g2d.dispose();
    }

    public static void paintVoltmeter(Graphics g, String name, Point location, boolean rotate) {

        Graphics2D g2d = (Graphics2D) g.create();

        int gridSize = DrawPanel.getGridSize();
        int x = location.x * gridSize;
        int y = location.y * gridSize;

        g2d.setStroke(new BasicStroke((int) 2.0));

        if (rotate) {
            g2d.drawLine(x, y, x, y + gridSize);
            g2d.drawLine(x, y + (3 * gridSize), x, y + (4 * gridSize));
            g2d.drawOval(x - gridSize, y + gridSize, 2 * gridSize, 2 * gridSize);

            Font font = new Font("Arial", Font.BOLD, 18);
            g2d.setFont(font);
            g2d.drawString(name, x - (int) (gridSize * 1.75), y + (int) (gridSize * 2.2));

            font = new Font("Arial", Font.BOLD, 35);
            g2d.setFont(font);
            g2d.drawString("V", x - (int) (0.55 * gridSize), y + (int) (2.5 * gridSize));
        } else {
            g2d.drawLine(x, y, x + gridSize, y);
            g2d.drawLine(x + (3 * gridSize), y, x + (4 * gridSize), y);
            g2d.drawOval(x + gridSize, y - gridSize, 2 * gridSize, 2 * gridSize);

            Font font = new Font("Arial", Font.BOLD, 18);
            g2d.setFont(font);

            g2d.drawString(name, x + (int) (1.75 * gridSize), y - (int) (gridSize * 1.25));

            font = new Font("Arial", Font.BOLD, 35);
            g2d.setFont(font);
            g2d.drawString("V", x + (int) (1.5 * gridSize), y + (int) (0.45 * gridSize));
        }

        g2d.dispose();
    }

    public static void paintConnectionPoint(Graphics g, Point location) {

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke((int) 2.0));

        int gridSize = DrawPanel.getGridSize();

        int x = (location.x * gridSize) - (gridSize / 8);
        int y = (location.y * gridSize) - (gridSize / 8);

        g2d.fillRect(x, y, gridSize / 4, gridSize / 4);

        g2d.dispose();
    }

}
