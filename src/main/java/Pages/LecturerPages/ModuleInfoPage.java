package main.java.Pages.LecturerPages;

import main.java.Entity.Module;

import javax.swing.*;
import java.awt.*;

/**
 * Page to show module information
 *
 * @author : Yunxin Wang
 * @version : v4.0
 */
public class ModuleInfoPage extends main.java.Pages.StudentPages.ModuleInfoPage {
    /**
     * @param module selected module
     */
    public ModuleInfoPage(Module module) {
        super(module);
    }

    /**
     * @param infoPanel information panel
     * @param module selected module
     */
    @Override
    protected void setupInfoPanel(JPanel infoPanel, Module module) {
        super.setupInfoPanel(infoPanel, module);
        viewStudentsButton(infoPanel, module);
    }

    /**
     * @param infoPanel information panel
     * @param module selected module
     */
    private void viewStudentsButton(JPanel infoPanel, Module module) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.WEST;

        JButton viewStudentsButton = new JButton("View Students");
        viewStudentsButton.addActionListener(e -> {
            new StudentListPage(module);
            dispose();
        });

        infoPanel.add(viewStudentsButton, gbc);
    }
}
