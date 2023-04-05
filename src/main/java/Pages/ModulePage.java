package main.java.Pages;

import main.java.Entity.Module;
import main.java.Data;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ModulePage extends JFrame{
    private JList<Module> moduleList;

    public ModulePage() {
        setupModulePage();

        setTitle("Module Page");
        setBounds(100, 100, 800, 600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        moduleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = moduleList.getSelectedIndex();
                    Module selectedModule = moduleList.getModel().getElementAt(selectedIndex);
                    new ModuleInfoPage(selectedModule);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(moduleList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setContentPane(scrollPane);
        setVisible(true);
    }

    private void setupModulePage() {
        ArrayList<Module> modules = Data.getInstance().modules;
        DefaultListModel<Module> moduleListModel = new DefaultListModel<>();
        for (Module module : modules) {
            moduleListModel.addElement(module);
        }

        moduleList = new JList<>(moduleListModel);
    }


    public static void main(String[] args) {
        new ModulePage();
    }
}


