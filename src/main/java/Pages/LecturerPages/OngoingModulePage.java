package main.java.Pages.LecturerPages;

import main.java.Data.ModuleData;
import main.java.Entity.Module;
import main.java.Pages.Lec_ModuleInfoPage;
import main.java.Pages.StudentsPages.ModuleInfoPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OngoingModulePage extends MyPage {
    private final JList<Module> ongoingModuleList;

    public OngoingModulePage() {
        setTitle("Ongoing Modules");
        setLayout(new BorderLayout());

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
                    new Lec_ModuleInfoPage(selectedModule);
                }
            }
        });
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
