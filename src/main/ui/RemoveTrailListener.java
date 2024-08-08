package ui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the listener for removing a trail
public class RemoveTrailListener implements ActionListener {
    private JList<Trail> trailList;
    private DefaultListModel<Trail> listModel;

    public RemoveTrailListener(JList<Trail> list, DefaultListModel<Trail> model) {
        this.trailList = list;
        this.listModel = model;
    }

    // MODIFIES: this
    // EFFECTS: removes selected trail from listModel
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = trailList.getSelectedIndex();
        listModel.remove(index);

        int size = listModel.getSize();

        if (size == 0) {
            // No trails left, disable remove button.
            // Remove button should only be enabled when there's a selection.
            // This condition should technically never be reached in this context.
        } else {
            if (index == listModel.getSize()) {
                index--;
            }
            trailList.setSelectedIndex(index);
            trailList.ensureIndexIsVisible(index);
        }

    }
}
