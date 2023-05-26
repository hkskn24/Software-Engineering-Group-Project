package main.java.Pages.StudentPages;

import main.java.Controller.AchievementController;
import main.java.Data.AchievementData;
import main.java.Entity.Achievement;
import main.java.Pages.MyPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Page shows the achievements with sort and filter operations
 *
 * @author : Yiyao guo
 * @version : v4.0
 */
public class AchievementPage extends MyPage {
    /**
     * table module of achievement table
     */
    private final DefaultTableModel tableModel;
    /**
     * sorter for achievement
     */
    private final TableRowSorter<DefaultTableModel> sorter;

    /**
     * set up the page
     */
    public AchievementPage() {
        setTitle("Achievement");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(250, 250, 250));

        // Add a window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HomePage homePage = new HomePage();
                homePage.setVisible(true);
            }
        });

        String[] columNames = {"Name", "Type", "Semester"};
        tableModel = new DefaultTableModel(columNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);

        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        setupAchievement();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // filter
        JPanel filterPanel = setupFilterPanel();

        // sort
        JPanel sortPanel = setupSortPanel(table);

        setupCombinedPanel(filterPanel, sortPanel);

        setVisible(true);
    }

    /**
     * get user achievements
     */
    private void setupAchievement() {
        ArrayList<Achievement> achievements = AchievementData.getInstance().achievements;
        for (Achievement achievement : achievements) {
            addAchievement(achievement);
        }
    }

    /**
     * add achievement to table
     *
     * @param achievement achievement
     */
    private void addAchievement(Achievement achievement) {
        Object[] newAchievement = {achievement.getName(), achievement.getType(), achievement.getSemester()};
        tableModel.addRow(newAchievement);
    }

    /**
     * set up filter panel
     *
     * @return {@link JPanel}
     */
    private JPanel setupFilterPanel() {
        JPanel filterPanel = new JPanel();

        String[] semesterOptions = {"All", "1", "2", "3", "4", "5", "6", "7", "8"};
        JComboBox<String> semesterComboBox = new JComboBox<>(semesterOptions);
        filterPanel.add(new JLabel("Filter by semester: "));
        filterPanel.add(semesterComboBox);

        String[] typeOptions = {"All", "Competition", "Service", "Scholarship"};
        JComboBox<String> typeComboBox = new JComboBox<>(typeOptions);
        filterPanel.add(new JLabel("Filter by type: "));
        filterPanel.add(typeComboBox);

        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener(AchievementController.createFilterActionListener(sorter, semesterComboBox, typeComboBox));
        filterPanel.add(filterButton);

        return filterPanel;

    }

    /**
     * set up sort panel
     *
     * @param table achievement table
     * @return {@link JPanel}
     */
    private JPanel setupSortPanel(JTable table) {
        JPanel sortPanel = new JPanel();

        JLabel sortLabel = new JLabel("Sort by Semester");
        sortPanel.add(sortLabel);
        JCheckBox ascendingBox = new JCheckBox("Ascending");
        sortPanel.add(ascendingBox);
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(e -> {
            int columnIndex = 2;
            AchievementController.sortTable(table, columnIndex, ascendingBox.isSelected());
        });
        sortPanel.add(sortButton);

        return sortPanel;
    }

    /**
     * combine the filter and sort panel
     *
     * @param filterPanel filter panel
     * @param sortPanel   sort panel
     */
    private void setupCombinedPanel(JPanel filterPanel, JPanel sortPanel) {
        JPanel combinedPanel = new JPanel(new GridBagLayout());
        getContentPane().add(combinedPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        combinedPanel.add(filterPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0;

        combinedPanel.add(sortPanel, gbc);

        addBackButton(combinedPanel, gbc);

        combinedPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    /**
     * add back button
     *
     * @param contentPanel  content panel
     * @param gbc          GridBagConstraints
     */
    private void addBackButton(JPanel contentPanel, GridBagConstraints gbc) {
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new HomePage().setVisible(true);
            dispose();
        });

        bottomPanel.add(backButton, BorderLayout.EAST);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        contentPanel.add(bottomPanel, gbc);
    }

    /***
     * @param args args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AchievementPage::new);
    }
}
