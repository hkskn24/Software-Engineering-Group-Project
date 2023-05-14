package main.java.Pages.LecturerPages;

import main.java.Data.ModuleData;
import main.java.Entity.Module;

import javax.swing.*;
import java.awt.*;

public class CompletedModulePage extends MyPage{
    private final JList<Module> completedModuleList;

    public CompletedModulePage() {
        setTitle("Completed Modules");
        setLayout(new BorderLayout());

        completedModuleList = new JList<>();
        updateList();
        add(new JScrollPane(completedModuleList), BorderLayout.CENTER);
    }

    private void updateList() {
        DefaultListModel<Module> listModel = new DefaultListModel<>();
        for (Module module : ModuleData.getInstance().getCompletedModules()) {
            listModel.addElement(module);
        }
        completedModuleList.setModel(listModel);
    }
}
