package main.java.Pages;

import main.java.Data;
import main.java.Entity.Achievement;
import main.java.Entity.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AchievementPage extends JFrame{
    private JTable table;
    private DefaultTableModel tableModel;

    public AchievementPage() {
        setTitle("Achievement");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(250, 250, 250));
        setBounds(500,300,1094,729);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columNames = {"Name", "Type", "Semester"};
        tableModel = new DefaultTableModel(columNames, 0);
        table = new JTable(tableModel);

        setupAchievement();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void setupAchievement() {
        ArrayList<Achievement> achievements = Data.getInstance().achievements;
        for (Achievement achievement : achievements) {
            addAchievement(achievement);
        }
    }

    private void addAchievement(Achievement achievement) {
        Object[] newAchievement = {achievement.getName(), achievement.getType(), achievement.getSemester()};
        tableModel.addRow(newAchievement);
    }

    public static void main(String[] args) {
        AchievementPage achievementPage = new AchievementPage();
    }
}
