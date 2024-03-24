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

// Represents the GUI for managing ski resort trails
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
    private JComboBox<String> difficultyFilter;

    private static final String FILE_NAME = "./data/skiResort.json";

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

        initializeDifficultyFilter();

        initializeInputPanel();
        initializeButtonPane();

        add(buttonPane, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes list model with default trails
    private void initializeListModel() {
        listModel = new DefaultListModel<>();
        listModel.addElement(new Trail("Collins", "Easy"));
        listModel.addElement(new Trail("Horizon", "Intermediate"));
        listModel.addElement(new Trail("Blaster", "Advanced"));
    }

    // MODIFIES: this
    // EFFECTS: initializes trail list
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

    // MODIFIES: this
    // EFFECTS: initialize the add trail button
    private void initializeAddButton() {
        addButton = new JButton(addTrailString);
        addButton.setEnabled(false);
        addTrailListener = new AddTrailListener(addButton, trailName, trailDifficulty, listModel, trailList);
        addButton.setActionCommand(addTrailString);
        addButton.addActionListener(addTrailListener);
    }

    // MODIFIES: this
    // EFFECTS: initializes the remove trail button
    private void initializeRemoveButton() {
        removeButton = new JButton(removeTrailString);
        removeButton.setActionCommand(removeTrailString);
        removeButton.addActionListener(new RemoveTrailListener(trailList, listModel));
    }

    // MODIFIES: this
    // EFFECTS: initializes the save button
    private void initializeSaveButton() {
        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(e -> saveDataToFile());
    }

    // MODIFIES: this
    // EFFECTS: initializes the load button
    private void initializeLoadButton() {
        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(e -> loadDataFromFile());
    }

    // MODIFIES: this
    // EFFECTS: initializes difficulty filter dropdown
    private void initializeDifficultyFilter() {
        String[] difficultyLevels = { "All", "Easy", "Intermediate", "Advanced" };
        difficultyFilter = new JComboBox<>(difficultyLevels);
        difficultyFilter.addActionListener(e -> filterTrailsByDifficulty((String) difficultyFilter.getSelectedItem()));
        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filter by Difficulty:"));
        filterPanel.add(difficultyFilter);
        add(filterPanel, BorderLayout.WEST);
    }

    // REQUIRES: selectedDifficulty is one of "All", "Easy", "Intermediate", "Advanced"
    // MODIFIES: this
    // EFFECTS: filters displayed trails based on selected difficulty
    private void filterTrailsByDifficulty(String selectedDifficulty) {
        if (selectedDifficulty.equals("All")) {
            trailList.setModel(listModel);
        } else {
            DefaultListModel<Trail> filteredModel = new DefaultListModel<>();
            for (int i = 0; i < listModel.getSize(); i++) {
                Trail trail = listModel.getElementAt(i);
                if (trail.getDifficulty().equalsIgnoreCase(selectedDifficulty)) {
                    filteredModel.addElement(trail);
                }
            }
            trailList.setModel(filteredModel);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes inout panel for entering new trail details
    private void initializeInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(trailName);
        inputPanel.add(new JLabel("Difficulty:"));
        inputPanel.add(trailDifficulty);
        add(inputPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes button panel with action buttons
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

    // MODIFIES: data
    // EFFECTS: saves trails to file
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

    // MODIFIES: this
    // EFFECTS: loads trails from file
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

    // EFFECTS: enables or disables remove button based on trail selection
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

    // EFFECTS: creates and displays the GUI
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