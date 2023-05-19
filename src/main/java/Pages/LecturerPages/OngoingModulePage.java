package main.java.Pages.LecturerPages;

import main.java.Data.ModuleData;
import main.java.Entity.Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OngoingModulePage extends MyPage {
    private final JList<Module> ongoingModuleList;

    public OngoingModulePage() {
        setTitle("Ongoing Modules");
        setLayout(new BorderLayout());

        // Add a window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                main.java.Pages.LecturerPages.HomePage homePage = new main.java.Pages.LecturerPages.HomePage();
                homePage.setVisible(true);
            }
        });

        ModuleData.getInstance().updateModules();

        ongoingModuleList = new JList<>();
        updateList();
        add(new JScrollPane(ongoingModuleList), BorderLayout.CENTER);

        JPanel buttonPanel = setupButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        ongoingModuleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = ongoingModuleList.getSelectedIndex();
                    Module selectedModule = ongoingModuleList.getModel().getElementAt(selectedIndex);
                    new ModuleInfoPage(selectedModule);
                }
            }
        });
    }

    private JPanel setupButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton endModuleButton = new JButton("End Module");
        endModuleButton.addActionListener(e -> endSelectedModule());
        buttonPanel.add(endModuleButton);

        JButton addButton = new JButton("Add Assessment");
        addButton.addActionListener(e -> {
            Module selectedModule = ongoingModuleList.getSelectedValue();
            if (selectedModule != null) {
                new AddAssessmentPage(selectedModule);
            }
        });
        buttonPanel.add(addButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
            new HomePage().setVisible(true);
        });
        buttonPanel.add(backButton);

        return buttonPanel;
    }

    private void updateList() {
        DefaultListModel<Module> listModel = new DefaultListModel<>();
        for (Module module : ModuleData.getInstance().getOngoingModules()) {
            listModel.addElement(module);
        }
        ongoingModuleList.setModel(listModel);
    }

    private void endSelectedModule() {
        Module selectedModule = ongoingModuleList.getSelectedValue();
        if (selectedModule != null) {
            ModuleData.getInstance().updateModuleStatus(selectedModule);
            updateList();
            JOptionPane.showMessageDialog(this, "Module is completed", "Module Status Changed", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
