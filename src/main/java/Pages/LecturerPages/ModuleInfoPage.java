package main.java.Pages.LecturerPages;

import main.java.Entity.Module;

import javax.swing.*;
import java.awt.*;

public class ModuleInfoPage extends main.java.Pages.StudentPages.ModuleInfoPage {
    public ModuleInfoPage(Module module) {
        super(module);
    }

    @Override
    protected void setupInfoPanel(JPanel infoPanel, Module module) {
        super.setupInfoPanel(infoPanel, module);
        viewStudentsButton(infoPanel, module);
    }

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
