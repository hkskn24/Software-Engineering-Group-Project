package main.java.Pages.StudentPages;

import main.java.Controller.ModuleController;
import main.java.Data.ModuleData;
import main.java.Entity.Module;
import main.java.Pages.MyPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Page shows the modules user joined with search panel and information
 *
 * @author : Yunxin Wang
 * @version : v4.0
 */
public class ModulePage extends MyPage {
    private JList<Module> moduleList;
    private JTextField searchTextField;

    /**
     * set up the page
     */
    public ModulePage() {
        ModuleData.getInstance().updateModules();

        setupModulePage();
        setTitle("Module Page");

        // Add a window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HomePage homePage = new HomePage();
                homePage.setVisible(true);
            }
        });

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

    /**
     * @param args args
     */
    public static void main(String[] args) {
        new ModulePage();
    }

    /**
     *
     */
    private void setupModulePage() {
        ModuleData.getInstance().updateModules();
        ArrayList<Module> modules = ModuleData.getInstance().modules;
        updateModuleList(modules);
    }

    /**
     * @param modules modules the student joined
     */
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

    /**
     * @return {@link JPanel}
     */
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

    /**
     * search from joined modules
     */
    private void searchModules() {
        String term = searchTextField.getText().trim();
        ArrayList<Module> modules = ModuleData.getInstance().modules;
        List<Module> result = ModuleController.searchMoules(modules, term);
        updateModuleList(result);
    }

    /**
     * @param contentPanel content panel
     */
    private void addBackButton(JPanel contentPanel) {
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });

        bottomPanel.add(backButton, BorderLayout.EAST);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
    }
}


