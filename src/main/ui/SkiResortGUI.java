package ui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class SkiResortGUI extends JPanel implements ListSelectionListener {
    private JList trailList;
    private DefaultListModel listModel;

    private static final String addTrailString = "Add Trail";
    private static final String removeTrailString = "Remove Trail";
    private JButton removeButton;
    private JButton addButton;
    private JTextField trailName;
    private AddTrailListener addTrailListener;
    private JPanel buttonPane;

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

    public void initializeButtonPane() {
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(trailName);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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
