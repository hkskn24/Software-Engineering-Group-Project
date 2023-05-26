package main.java.Pages.StudentPages;

import main.java.Entity.Module;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class ModuleInfoPageTest {
    @Test
    public void testBackButtonFunctionality() {

        Module module = new Module();

        SwingUtilities.invokeLater(() -> {
            ModuleInfoPage moduleInfoPage = new ModuleInfoPage(module);

            JPanel infoPanel = (JPanel) moduleInfoPage.getContentPane().getComponent(0);

            JButton backButton = getButtonByName(infoPanel, "Back");

            Assertions.assertTrue(backButton.isEnabled());

        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private JButton getButtonByName(JPanel panel, String buttonText) {

        for (Component component : panel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(buttonText)) {
                    return button;
                }
            }
        }

        return null;
    }
}