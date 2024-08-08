package ui;

import model.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the listener for adding a trail
public class AddTrailListener implements ActionListener, DocumentListener {
    private DefaultListModel<Trail> listModel;
    private JTextField trailName;
    private JTextField trailDifficulty;
    private JList<Trail> trailList;
    private JButton addTrailButton;

    private boolean alreadyEnabled = false;

    public AddTrailListener(JButton addTrailButton, JTextField trailName, JTextField trailDifficulty,
                            DefaultListModel<Trail> model, JList<Trail> list) {
        this.addTrailButton = addTrailButton;
        this.trailName = trailName;
        this.trailDifficulty = trailDifficulty;
        this.listModel = model;
        this.trailList = list;
    }

    // MODIFIES: this
    // EFFECTS: adds new trail to listModel
    public void actionPerformed(ActionEvent e) {
        String name = trailName.getText();
        String difficulty = trailDifficulty.getText();

        if (name.equals("") || alreadyInList(name)) {
            Toolkit.getDefaultToolkit().beep();
            trailName.requestFocusInWindow();
            trailName.selectAll();
            return;
        }

        Trail trail = new Trail(name, difficulty); // Create a Trail object with name and difficulty
        listModel.addElement(trail);
        trailName.requestFocusInWindow();
        trailName.setText("");

        // Select the new item and make it visible
        int index = listModel.getSize() - 1;
        trailList.setSelectedIndex(index);
        trailList.ensureIndexIsVisible(index);
    }

    // EFFECTS: returns true if given trail name is already in the list
    protected boolean alreadyInList(String name) {
        for (int i = 0; i < listModel.getSize(); i++) {
            Trail trail = listModel.getElementAt(i);
            if (trail.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: enables the addTrailButton when text inserted
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }

    // MODIFIES: this
    // EFFECTS: handles empty text field and disables addTrailButton
    public void removeUpdate(DocumentEvent e) {
        handleEmptyTextField(e);
    }

    // MODIFIES: this
    // EFFECTS: handles empty text field and enables addTrailButton if not empty
    public void changedUpdate(DocumentEvent e) {
        if (!handleEmptyTextField(e)) {
            enableButton();
        }
    }

    // MODIFIES: this
    // EFFECTS: enables addTrailButton
    private void enableButton() {
        if (!alreadyEnabled) {
            addTrailButton.setEnabled(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles empty text field, returns true if text field is empty; false otherwise
    private boolean handleEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            addTrailButton.setEnabled(false);
            alreadyEnabled = false;
            return true;
        }
        return false;
    }
}
