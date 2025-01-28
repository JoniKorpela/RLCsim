package com.rlcsim;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

// Sets all keybindings in the program
public class KeyBindings {

    public static void setKeyBindings(DrawPanel drawPanel, ComponentHandler componentHandler,
            ControlPanel controlPanel) {
        setRun(drawPanel);
        setProbe(drawPanel);
        setResistor(drawPanel);
        setRotationKey(drawPanel);
        setEscape(drawPanel);
        setInductor(drawPanel);
        setCapacitor(drawPanel);
        setAmmeter(drawPanel);
        setVoltmeter(drawPanel);
        setWire(drawPanel);
        setRemove(drawPanel);
        setVoltageSource(drawPanel, componentHandler);
        setCurrentSource(drawPanel, componentHandler);
        setSaveFile(drawPanel, componentHandler);
        setSaveAs(drawPanel, componentHandler);
        setOpenFile(drawPanel, componentHandler);
        setNewFile(drawPanel, componentHandler);
        setZoomIn(drawPanel);
        setZoomOut(drawPanel);
        setHideControlPanel(drawPanel, controlPanel);

    }

    private static void setRun(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "SPACEBAR");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("SPACEBAR", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() != DrawPanel.State.RUN && drawPanel.getState() != DrawPanel.State.PROBE) {
                    drawPanel.setState(DrawPanel.State.RUN);
                } else if (drawPanel.getState() == DrawPanel.State.RUN
                        || drawPanel.getState() == DrawPanel.State.PROBE) {
                    drawPanel.setState(DrawPanel.State.STANDARD);
                }
            }
        });
    }

    private static void setRotationKey(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK), "CTRL_R");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("CTRL_R", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() != DrawPanel.State.STANDARD && drawPanel.getState() != DrawPanel.State.PROBE
                        && drawPanel.getState() != DrawPanel.State.RUN) {
                    drawPanel.setComponentRotation(!drawPanel.getComponentRotation());
                    drawPanel.repaint();
                }
            }

        });
    }

    private static void setResistor(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "R");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("R", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() != DrawPanel.State.RUN && drawPanel.getState() != DrawPanel.State.PROBE) {

                    drawPanel.setState(DrawPanel.State.RESISTOR);
                }
                drawPanel.repaint();
            }

        });
    }

    private static void setEscape(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESC");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("ESC", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (drawPanel.getState() == DrawPanel.State.PROBE) {
                    drawPanel.setState(DrawPanel.State.PROBE);
                } else if (drawPanel.getState() == DrawPanel.State.RUN) {
                    drawPanel.setState(DrawPanel.State.RUN);
                } else {
                    drawPanel.setState(DrawPanel.State.STANDARD);
                }

                drawPanel.repaint();
            }

        });
    }

    private static void setInductor(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), "L");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("L", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() != DrawPanel.State.RUN && drawPanel.getState() != DrawPanel.State.PROBE) {

                    drawPanel.setState(DrawPanel.State.INDUCTOR);
                }
                drawPanel.repaint();
            }

        });
    }

    private static void setProbe(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "P");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("P", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() == DrawPanel.State.RUN) {

                    drawPanel.setState(DrawPanel.State.PROBE);
                }
                drawPanel.repaint();
            }

        });
    }

    private static void setCapacitor(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "C");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("C", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() != DrawPanel.State.RUN && drawPanel.getState() != DrawPanel.State.PROBE) {

                    drawPanel.setState(DrawPanel.State.CAPACITOR);
                }
                drawPanel.repaint();
            }

        });
    }

    private static void setWire(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "W");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("W", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() != DrawPanel.State.RUN && drawPanel.getState() != DrawPanel.State.PROBE) {

                    drawPanel.setState(DrawPanel.State.WIRE);
                }
                drawPanel.repaint();
            }

        });
    }

    private static void setRemove(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "D");
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "DELETE");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("D", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() != DrawPanel.State.RUN && drawPanel.getState() != DrawPanel.State.PROBE) {
                    drawPanel.setState(DrawPanel.State.REMOVE);
                }
                drawPanel.repaint();
            }

        });

        actionMap.put("DELETE", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() != DrawPanel.State.RUN) {
                    drawPanel.setState(DrawPanel.State.REMOVE);
                }
                drawPanel.repaint();
            }

        });
    }

    private static void setVoltageSource(DrawPanel drawPanel, ComponentHandler componentHandler) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, 0), "V");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("V", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (drawPanel.getState() != DrawPanel.State.RUN && !componentHandler.hasSource()
                        && drawPanel.getState() != DrawPanel.State.PROBE) {
                    drawPanel.setState(DrawPanel.State.VOLTAGESOURCE);

                }

                drawPanel.repaint();
            }

        });
    }

    private static void setCurrentSource(DrawPanel drawPanel, ComponentHandler componentHandler) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0), "I");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("I", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() != DrawPanel.State.RUN && !componentHandler.hasSource()
                        && drawPanel.getState() != DrawPanel.State.PROBE) {

                    drawPanel.setState(DrawPanel.State.CURRENTSOURCE);
                }
                drawPanel.repaint();
            }

        });
    }

    private static void setVoltmeter(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.SHIFT_DOWN_MASK), "SHIFT_V");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("SHIFT_V", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() != DrawPanel.State.RUN && drawPanel.getState() != DrawPanel.State.PROBE) {

                    drawPanel.setState(DrawPanel.State.VOLTMETER);
                }
                drawPanel.repaint();
            }

        });
    }

    private static void setAmmeter(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.SHIFT_DOWN_MASK), "SHIFT_I");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("SHIFT_I", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawPanel.getState() != DrawPanel.State.RUN && drawPanel.getState() != DrawPanel.State.PROBE) {

                    drawPanel.setState(DrawPanel.State.AMMETER);
                }
                drawPanel.repaint();
            }

        });
    }

    private static void setSaveFile(DrawPanel drawPanel, ComponentHandler componentHandler) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "CTRL_S");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("CTRL_S", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                componentHandler.update("Save File");
            }

        });
    }

    private static void setSaveAs(DrawPanel drawPanel, ComponentHandler componentHandler) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "F12");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("F12", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                componentHandler.update("Save File as");
            }

        });
    }

    private static void setOpenFile(DrawPanel drawPanel, ComponentHandler componentHandler) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), "CTRL_O");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("CTRL_O", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                componentHandler.update("Open File");
            }

        });
    }

    private static void setNewFile(DrawPanel drawPanel, ComponentHandler componentHandler) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "CTRL_N");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("CTRL_N", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                componentHandler.update("New File");
            }

        });
    }

    private static void setZoomIn(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), "+");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("+", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.update("Zoom in");
            }

        });
    }

    private static void setZoomOut(DrawPanel drawPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "-");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("-", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.update("Zoom out");
            }

        });
    }

    private static void setHideControlPanel(DrawPanel drawPanel, ControlPanel controlPanel) {
        InputMap IM = drawPanel.getInputMap(JComponent.WHEN_FOCUSED);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "F11");
        ActionMap actionMap = drawPanel.getActionMap();

        actionMap.put("F11", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (controlPanel.isVisible()) {
                    controlPanel.setVisible(false);
                } else {
                    controlPanel.setVisible(true);
                }

                drawPanel.repaint();
            }

        });

    }

}
