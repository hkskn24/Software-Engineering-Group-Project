package main.java.Pages.LecturerPages;

import main.java.Data.ModuleData;
import main.java.Entity.Module;
import main.java.Pages.MyPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CompletedModulePage extends MyPage {
    private final JList<Module> completedModuleList;

    public CompletedModulePage() {
        ModuleData.getInstance().updateModules();

        // Add a window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                main.java.Pages.LecturerPages.HomePage homePage = new main.java.Pages.LecturerPages.HomePage();
                homePage.setVisible(true);
            }
        });

        setTitle("Completed Modules");
        setLayout(new BorderLayout());

        completedModuleList = new JList<>();
        updateList();
        add(new JScrollPane(completedModuleList), BorderLayout.CENTER);

        JPanel buttonPanel = setupButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        completedModuleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = completedModuleList.getSelectedIndex();
                    Module selectedModule = completedModuleList.getModel().getElementAt(selectedIndex);
                    new ModuleInfoPage(selectedModule);
                }
            }
        });
    }

    private JPanel setupButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton assignGrades = new JButton("Assign Grades");
        assignGrades.addActionListener(e -> {
            Module selectedModule = completedModuleList.getSelectedValue();
            if (selectedModule != null) {
                new AssignGradesPage(selectedModule);
                this.dispose();
            }
        });
        buttonPanel.add(assignGrades);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new HomePage().setVisible(true);
            this.dispose();
        });
        buttonPanel.add(backButton);

        return buttonPanel;
    }

    private void updateList() {
        DefaultListModel<Module> listModel = new DefaultListModel<>();
        for (Module module : ModuleData.getInstance().getCompletedModules()) {
            listModel.addElement(module);
        }
        completedModuleList.setModel(listModel);
    }
}
