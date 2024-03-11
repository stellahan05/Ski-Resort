package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTrailListener implements ActionListener, DocumentListener {
    private DefaultListModel<String> listModel;
    private JTextField trailName;
    private JButton addTrailButton;
    private JList<String> trailList;

    private boolean alreadyEnabled = false;

    public AddTrailListener(JButton button, JTextField textField, DefaultListModel<String> model, JList<String> list) {
        this.addTrailButton = button;
        this.trailName = textField;
        this.listModel = model;
        this.trailList = list;
    }

    public void actionPerformed(ActionEvent e) {
        String name = trailName.getText();

        if (name.equals("") || alreadyInList(name)) {
            Toolkit.getDefaultToolkit().beep();
            trailName.requestFocusInWindow();
            trailName.selectAll();
            return;
        }

        int index = listModel.getSize();

        listModel.addElement(trailName.getText());
        trailName.requestFocusInWindow();
        trailName.setText("");

        trailList.setSelectedIndex(index);
        trailList.ensureIndexIsVisible(index);
    }

    protected boolean alreadyInList(String name) {
        return listModel.contains(name);
    }

    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }

    public void removeUpdate(DocumentEvent e) {
        handleEmptyTextField(e);
    }

    public void changedUpdate(DocumentEvent e) {
        if (!handleEmptyTextField(e)) {
            enableButton();
        }
    }

    private void enableButton() {
        if (!alreadyEnabled) {
            addTrailButton.setEnabled(true);
        }
    }

    private boolean handleEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            addTrailButton.setEnabled(false);
            alreadyEnabled = false;
            return true;
        }
        return false;
    }
}
