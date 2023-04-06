package main.java.Pages;

import main.java.Controller.ModuleController;
import main.java.Entity.Module;
import main.java.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ModulePage extends JFrame{
    private JList<Module> moduleList;
    private JTextField searchTextField;

    public ModulePage() {
        setupModulePage();

        setTitle("Module Page");
        setBounds(500,300,1094,729);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel(new BorderLayout());

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

        JPanel searchPanel = setupSearchPanel();

        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        setContentPane(contentPanel);
        setVisible(true);
    }

    private void setupModulePage() {
        ArrayList<Module> modules = Data.getInstance().modules;
        updateModuleList(modules);
    }

    private void updateModuleList(List<Module> modules) {
        DefaultListModel<Module> moduleListModel = new DefaultListModel<>();
        for (Module module : modules) {
            moduleListModel.addElement(module);
        }

        if (moduleList == null) {
            moduleList = new JList<>(moduleListModel);
        } else {
            moduleList.setModel(moduleListModel);
        }
    }

    private JPanel setupSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchTextField = new JTextField(20);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchModules());

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            searchTextField.setText("");
            searchModules();
        });

        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);

        return searchPanel;
    }

    private void searchModules() {
        String term = searchTextField.getText().trim();
        ArrayList<Module> modules = Data.getInstance().modules;
        List<Module> result = ModuleController.searchMoules(modules, term);
        updateModuleList(result);
    }

    public static void main(String[] args) {
        new ModulePage();
    }
}


