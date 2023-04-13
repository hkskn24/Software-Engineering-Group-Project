package main.java.Pages;

import main.java.Controller.ModuleController;
import main.java.Data.ModuleData;
import main.java.Entity.Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ModulePage extends JFrame {
    private JList<Module> moduleList;
    private JTextField searchTextField;

    public ModulePage() {
        setupModulePage();

        setTitle("Module Page");
        setSize(1094, 729);
        setLocationRelativeTo(null);
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
        addBackButton(contentPanel);

        setContentPane(contentPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ModulePage();
    }

    private void setupModulePage() {
        ArrayList<Module> modules = ModuleData.getInstance().modules;
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
        ArrayList<Module> modules = ModuleData.getInstance().modules;
        List<Module> result = ModuleController.searchMoules(modules, term);
        updateModuleList(result);
    }

    private void addBackButton(JPanel contentPanel) {
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            new HomePage().setVisible(true);
            dispose();
        });

        bottomPanel.add(backButton, BorderLayout.EAST);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
    }
}


