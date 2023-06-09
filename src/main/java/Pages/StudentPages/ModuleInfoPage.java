package main.java.Pages.StudentPages;

import main.java.Entity.Module;
import main.java.Pages.MyPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Page to show module information
 *
 * @author : Yunxin Wang
 * @version : v4.1
 */
public class ModuleInfoPage extends MyPage {
    /**
     * @param module selected module
     */
    public ModuleInfoPage(Module module) {
        setTitle(module.getName());

        JPanel infoPanel = new JPanel(new GridBagLayout());
        setupInfoPanel(infoPanel, module);
        add(infoPanel);

        addBackButton(infoPanel);

        // add scroll panel
        JScrollPane scrollPane = new JScrollPane(infoPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setContentPane(scrollPane);
        setVisible(true);
    }

    /**
     * @param infoPanel information panel
     */
    private void addBackButton(JPanel infoPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.EAST;

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.addActionListener(e -> dispose());
        infoPanel.add(backButton, gbc);
        setLocationRelativeTo(null);
    }

    /**
     * @param infoPanel information panel
     * @param module selected module
     */
    protected void setupInfoPanel(JPanel infoPanel, Module module) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        // set Module Title
        JLabel title = new JLabel(module.getName());
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        infoPanel.add(title, gbc);

        // set Lecturer
        addInfoSection(infoPanel, gbc, "Lecturer", module.getLecturer(), 1);

        // set Summary
        addInfoSection(infoPanel, gbc, "Summary", module.getSummary(), 3);

        // set Aims
        addInfoSection(infoPanel, gbc, "Aims", module.getAims(), 5);

        // set syllabus
        addInfoSection(infoPanel, gbc, "Syllabus", module.getSyllabus(), 7);

        //set Reading List
        addInfoSection(infoPanel, gbc, "Reading List", module.getReadingList(), 9);

        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    /**
     * @param panel information panel
     * @param gbc GridBagConstraints
     * @param title page title
     * @param content page content
     * @param rowIndex index
     */
    private void addInfoSection(JPanel panel, GridBagConstraints gbc, String title, String content, int rowIndex) {
        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = rowIndex;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(sectionTitle, gbc);

        JTextArea sectionContent = new JTextArea(content);
        sectionContent.setLineWrap(true);
        sectionContent.setWrapStyleWord(true);
        sectionContent.setEditable(false);
        sectionContent.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = rowIndex + 1;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(sectionContent, gbc);
    }
}
