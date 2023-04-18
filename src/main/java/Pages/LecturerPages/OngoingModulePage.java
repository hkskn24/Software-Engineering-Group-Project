package main.java.Pages.LecturerPages;

import main.java.Data.ModuleData;
import main.java.Entity.Module;

import javax.swing.*;
import java.awt.*;

public class OngoingModulePage extends MyPage {
    private JList<Module> ongoingModuleList;

    public OngoingModulePage() {
        setTitle("Ongoing Modules");
        setLayout(new BorderLayout());

        ongoingModuleList = new JList<>();
        updateList();
        add(new JScrollPane(ongoingModuleList), BorderLayout.CENTER);

        JPanel buttonPanel = setupButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel setupButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton endModuleButton = new JButton("End Module");
        endModuleButton.addActionListener(e -> endSelectedModule());
        buttonPanel.add(endModuleButton);

        JButton addButton = new JButton("Add Assessment");
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
