package ui;

import model.Trail;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TrailListRenderer extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Trail) {
            Trail trail = (Trail) value;
            label.setText(trail.getName());
            // Customize appearance based on difficulty
            if (trail.getDifficulty().equalsIgnoreCase("easy")) {
                label.setIcon(createDifficultyShape(Color.GREEN, "easy")); // Green for easy
            } else if (trail.getDifficulty().equalsIgnoreCase("intermediate")) {
                label.setIcon(createDifficultyShape(Color.BLUE, "intermediate")); // blue for intermediate
            } else if (trail.getDifficulty().equalsIgnoreCase("advanced")) {
                label.setIcon(createDifficultyShape(Color.BLACK, "advanced")); // black for advanced
            }
        }

        return label;
    }

    private ImageIcon createDifficultyShape(Color color, String difficulty) {
        int size = 20;
        ImageIcon icon = new ImageIcon(new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB));
        Graphics2D g2d = (Graphics2D) icon.getImage().getGraphics();
        g2d.setColor(color);

        switch (difficulty.toLowerCase()) {
            case "easy":
                g2d.fillOval(0, 0, size, size);
                break;
            case "intermediate":
                g2d.fillRect(0, 0, size, size);
                break;
            case "advanced":
                g2d.fillPolygon(new int[]{size / 2, 0, size}, new int[]{0, size, size}, 3);
                break;
            default:
                // Default to green circle for unknown difficulty
                g2d.fillOval(0, 0, size, size);
                break;
        }

        g2d.dispose();
        return icon;
    }

}
