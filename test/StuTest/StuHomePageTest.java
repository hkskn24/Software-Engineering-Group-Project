package main.java.Pages.StudentPages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class StuHomePageTest {
    @Test
    public void testButtonsAreEnabled() {
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage();

            JButton btnModulePage = getButtonByName(homePage, "Module");
            JButton btnGradePage = getButtonByName(homePage, "Grade");
            JButton btnAchievement = getButtonByName(homePage, "Achievement");
            JButton btnAssessment = getButtonByName(homePage, "Assessment");
            JButton btnBack = getButtonByName(homePage, "Log out");

            Assertions.assertTrue(btnModulePage.isEnabled());
            Assertions.assertTrue(btnGradePage.isEnabled());
            Assertions.assertTrue(btnAchievement.isEnabled());
            Assertions.assertTrue(btnAssessment.isEnabled());
            Assertions.assertTrue(btnBack.isEnabled());
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private JButton getButtonByName(HomePage homePage, String buttonText) {

        JPanel panel = (JPanel) homePage.getContentPane().getComponent(1);

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
