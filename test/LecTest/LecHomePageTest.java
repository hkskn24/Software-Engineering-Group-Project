package main.java.Pages.LecturerPages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class LecHomePageTest {
    @Test
    public void testButtonsAreEnabled() {

        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage();

            JButton btnLec_ModulePage = getButtonByName(homePage, "Add Module");
            JButton btnLec_StudentPage = getButtonByName(homePage, "Join Module");
            JButton btnOngoingcourses = getButtonByName(homePage, "View ongoing courses");
            JButton btnCompletedcourses = getButtonByName(homePage, "View completed courses");
            JButton btnBack = getButtonByName(homePage, "Log out");

            Assertions.assertTrue(btnLec_ModulePage.isEnabled());
            Assertions.assertTrue(btnLec_StudentPage.isEnabled());
            Assertions.assertTrue(btnOngoingcourses.isEnabled());
            Assertions.assertTrue(btnCompletedcourses.isEnabled());
            Assertions.assertTrue(btnBack.isEnabled());
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private JButton getButtonByName(HomePage homePage, String buttonText) {

        JPanel panel = (JPanel) homePage.getContentPane().getComponent(0);

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