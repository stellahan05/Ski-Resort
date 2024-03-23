package ui;

import model.Trail;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SkiResortGUI extends JPanel implements ListSelectionListener {
    private JList trailList;
    private DefaultListModel<Trail> listModel;

    private static final String addTrailString = "Add Trail";
    private static final String removeTrailString = "Remove Trail";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private JButton removeButton;
    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField trailName;
    private JTextField trailDifficulty;
    private AddTrailListener addTrailListener;
    private JPanel buttonPane;

    private static final String FILE_NAME = "trails.json";

    public SkiResortGUI() {
        super(new BorderLayout());

        initializeListModel();

        initializeTrailList();

        trailName = new JTextField(10);
        trailDifficulty = new JTextField(10);

        initializeAddButton();
        initializeRemoveButton();
        initializeSaveButton();
        initializeLoadButton();

        trailName.addActionListener(addTrailListener);
        trailName.getDocument().addDocumentListener(addTrailListener);

        initializeInputPanel();
        initializeButtonPane();

        add(buttonPane, BorderLayout.SOUTH);
    }

    private void initializeListModel() {
        listModel = new DefaultListModel<>();
        listModel.addElement(new Trail("Collins", "Easy"));
        listModel.addElement(new Trail("Horizon", "Intermediate"));
        listModel.addElement(new Trail("Blaster", "Advanced"));
    }

    private void initializeTrailList() {
        trailList = new JList<>(listModel);
        trailList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        trailList.setSelectedIndex(0);
        trailList.setCellRenderer(new TrailListRenderer());
        trailList.addListSelectionListener(this);
        trailList.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(trailList);
        add(listScrollPane, BorderLayout.CENTER);
    }

    private void initializeAddButton() {
        addButton = new JButton(addTrailString);
        addButton.setEnabled(false);
        addTrailListener = new AddTrailListener(addButton, trailName, trailDifficulty, listModel, trailList);
        addButton.setActionCommand(addTrailString);
        addButton.addActionListener(addTrailListener);
    }

    private void initializeRemoveButton() {
        removeButton = new JButton(removeTrailString);
        removeButton.setActionCommand(removeTrailString);
        removeButton.addActionListener(new RemoveTrailListener(trailList, listModel));
    }

    private void initializeSaveButton() {
        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(e -> saveDataToFile());
    }

    private void initializeLoadButton() {
        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(e -> loadDataFromFile());
    }

    private void initializeInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(trailName);
        inputPanel.add(new JLabel("Difficulty:"));
        inputPanel.add(trailDifficulty);
        add(inputPanel, BorderLayout.NORTH);
    }

    private void initializeButtonPane() {
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

    // Save trails to file
    private void saveDataToFile() {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            List<Trail> trails = new ArrayList<>();
            for (int i = 0; i < listModel.size(); i++) {
                trails.add(listModel.getElementAt(i));
            }

            JSONArray jsonArray = new JSONArray();
            for (Trail trail : trails) {
                jsonArray.put(trail.toJson());
            }
            fileWriter.write(jsonArray.toString());
            JOptionPane.showMessageDialog(this, "Data saved successfully!",
                    saveString, JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data!",
                    saveString, JOptionPane.ERROR_MESSAGE);
        }
    }

    // Load trails from file
    private void loadDataFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONArray jsonArray = new JSONArray(stringBuilder.toString());

            listModel.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject trailObject = jsonArray.getJSONObject(i);
                String name = trailObject.getString("name");
                String difficulty = trailObject.getString("difficulty");
                Trail trail = new Trail(name, difficulty);
                listModel.addElement(trail);
            }
            JOptionPane.showMessageDialog(this, "Data loaded successfully!",
                    loadString, JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data!",
                    loadString, JOptionPane.ERROR_MESSAGE);
        }
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