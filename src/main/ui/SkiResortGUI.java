package ui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.IOException;

public class SkiResortGUI extends JPanel implements ListSelectionListener {
    private JList trailList;
    private DefaultListModel listModel;

    private static final String addTrailString = "Add Trail";
    private static final String removeTrailString = "Remove Trail";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private JButton removeButton;
    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField trailName;
    private AddTrailListener addTrailListener;
    private JPanel buttonPane;
    private SkiResortApp skiResortApp;

    public SkiResortGUI() {
        super(new BorderLayout());

        listModel = new DefaultListModel<>();
        listModel.addElement("Collins");
        listModel.addElement("Horizon");
        listModel.addElement("Blaster");

        trailList = new JList(listModel);
        trailList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        trailList.setSelectedIndex(0);
        trailList.addListSelectionListener(this);
        trailList.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(trailList);

        trailName = new JTextField(10);
        initializeAddButton();
        initializeRemoveButton();
        initializeSaveButton();
        initializeLoadButton();

        trailName.addActionListener(addTrailListener);
        trailName.getDocument().addDocumentListener(addTrailListener);
        String name = listModel.getElementAt(trailList.getSelectedIndex()).toString();

        initializeButtonPane();

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    public void initializeAddButton() {
        addButton = new JButton(addTrailString);
        addButton.setEnabled(false);
        addTrailListener = new AddTrailListener(addButton, trailName, listModel, trailList);
        addButton.setActionCommand(addTrailString);
        addButton.addActionListener(addTrailListener);
    }

    public void initializeRemoveButton() {
        removeButton = new JButton(removeTrailString);
        removeButton.setActionCommand(removeTrailString);
        removeButton.addActionListener(new RemoveTrailListener(trailList, listModel));
    }

    public void initializeSaveButton() {
        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(e -> saveDataToFile());
    }

    public void initializeLoadButton() {
        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(e -> loadDataFromFile());
    }


    public void initializeButtonPane() {
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(trailName);
        buttonPane.add(addButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    private void saveDataToFile() {
        skiResortApp.saveTrails();
        JOptionPane.showMessageDialog(this, "Data saved successfully!",
                saveString, JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadDataFromFile() {
        skiResortApp.loadTrails();
        JOptionPane.showMessageDialog(this, "Data loaded successfully!",
                loadString, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (trailList.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
            }
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Ski Resort App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentPane = new SkiResortGUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
