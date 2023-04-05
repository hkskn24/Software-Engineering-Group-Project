package main.java.Pages;

import main.java.Entity.Module;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ModuleInfoPage extends JFrame {
    public ModuleInfoPage(Module module) {
        setTitle(module.getName());
        setBounds(100, 100, 800, 600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        setupInfoPanel(infoPanel, module);

        add(infoPanel);
    }

    public static void main(String[] args) {
        Module module = new Module();
        module.setName("Name");
        module.setReadingList("ReadingList");
        module.setSyllabus("Syllabus");
        new ModuleInfoPage(module);
    }

    private void setupInfoPanel(JPanel infoPanel, Module module) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);

        // set Module Title
        JLabel title = new JLabel(module.getName());
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        infoPanel.add(title, gbc);

        // set syllabus
        JLabel syllabusLabel = new JLabel("syllabus");
        syllabusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        infoPanel.add(syllabusLabel, gbc);

        JTextArea syllabus = new JTextArea(module.getSyllabus());
        syllabus.setLineWrap(true);
        syllabus.setWrapStyleWord(true);
        syllabus.setEditable(false);
        syllabus.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        infoPanel.add(syllabus, gbc);

        // set reading list
        // set syllabus
        JLabel readingListLabel = new JLabel("readingList");
        readingListLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        infoPanel.add(readingListLabel, gbc);

        JTextArea readingList = new JTextArea(module.getReadingList());
        readingList.setLineWrap(true);
        readingList.setWrapStyleWord(true);
        readingList.setEditable(false);
        readingList.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        infoPanel.add(readingList, gbc);

        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

}
