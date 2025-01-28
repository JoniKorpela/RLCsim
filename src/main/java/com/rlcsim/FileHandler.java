package com.rlcsim;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;

// Handles all File I/O tasks
public class FileHandler {

    // Buffers for wires and components to be used during saving and loading.
    private static ArrayList<ElectricalComponent> componentsBuffer = new ArrayList<>();
    private static ArrayList<Wire> wiresBuffer = new ArrayList<>();

    // ActiveFile holds the last used save file.
    private static File activeFile;

    public FileHandler() {
    }

    // If activeFile exists, calls the save method with that as a parameter. Else calls saveAsToFile.
    // Copies components and wires to buffers.
    public static boolean saveToFile(ArrayList<ElectricalComponent> components, ArrayList<Wire> wires) {
        if (activeFile != null) {
            componentsBuffer = new ArrayList<>(components);
            wiresBuffer = new ArrayList<>(wires);
            return saveObjectsToFile(activeFile);
        } else {
            return saveAsToFile(components, wires);
        }
    }

    // Calls fileChooser in order to get a activeFile. Calls the save method with it
    // Copies components and wires to buffers.
    public static boolean saveAsToFile(ArrayList<ElectricalComponent> components, ArrayList<Wire> wires) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            componentsBuffer = new ArrayList<>(components);
            wiresBuffer = new ArrayList<>(wires);
            activeFile = fileChooser.getSelectedFile();
            return saveObjectsToFile(activeFile);
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            return true;
        }
        return false;
    }

    // Loads component and wirelists from file to buffer, and returns the buffers' contents.
    public static Pair<ArrayList<ElectricalComponent>, ArrayList<Wire>> loadFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(fileChooser);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            activeFile = fileChooser.getSelectedFile();
            loadObjectsFromFile(activeFile);
            // Check that loaded lists are not null. If so, instantiate empty lists.
            if (wiresBuffer == null) {
                wiresBuffer = new ArrayList<>();
            }
            if (componentsBuffer == null) {
                componentsBuffer = new ArrayList<>();
            }
            Pair<ArrayList<ElectricalComponent>, ArrayList<Wire>> pair = new Pair<ArrayList<ElectricalComponent>, ArrayList<Wire>>(
                    componentsBuffer, wiresBuffer);
            return pair;
        }
        return null;
    }

    // Saves buffers to file.
    private static boolean saveObjectsToFile(File file) {
        try (FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(componentsBuffer);
            objectOut.writeObject(wiresBuffer);
            fileOut.flush();
            objectOut.flush();
            fileOut.close();
            objectOut.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Loads buffers from file.
    @SuppressWarnings("unchecked")
    private static void loadObjectsFromFile(File file) {
        try (FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            componentsBuffer = (ArrayList<ElectricalComponent>) objectIn.readObject();
            wiresBuffer = (ArrayList<Wire>) objectIn.readObject();
            fileIn.close();
            objectIn.close();
        } catch (Exception e) {
            new WarningDialogWindow(true, "Failed to open file.");
            e.printStackTrace();
        }
    }

    // Clears the buffers and returns empty lists.
    public static Pair<ArrayList<ElectricalComponent>, ArrayList<Wire>> newFile() {
        activeFile = null;
        componentsBuffer.clear();
        wiresBuffer.clear();
        ArrayList<ElectricalComponent> components = new ArrayList<>();
        ArrayList<Wire> wires = new ArrayList<>();
        Pair<ArrayList<ElectricalComponent>, ArrayList<Wire>> pair = new Pair<ArrayList<ElectricalComponent>, ArrayList<Wire>>(
                components, wires);
        return pair;
    }

    // Checks whether file has been modified since last save. Returns true if file
    // is saved, and it is safe to exit/open a new file.
    public static boolean checkSavedStatus(ArrayList<ElectricalComponent> components, ArrayList<Wire> wires) {
        boolean isSaved = false;
        if (componentsBuffer.equals(components) && wiresBuffer.equals(wires)) {
            isSaved = true;
        }
        return isSaved;
    }
}
