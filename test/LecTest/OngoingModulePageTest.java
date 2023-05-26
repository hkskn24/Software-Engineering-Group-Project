package main.java.Pages.LecturerPages;
import org.junit.jupiter.api.Test;
import javax.swing.JButton;
import javax.swing.*;
public class OngoingModulePageTest {

    @Test
    public void testButtonAvailability() {
        OngoingModulePage ongoingModulePage = new OngoingModulePage();

        JButton endModuleButton = getEndModuleButton(ongoingModulePage);
        JButton addButton = getAddButton(ongoingModulePage);
        JButton backButton = getBackButton(ongoingModulePage);

        assert endModuleButton.isEnabled();
        assert addButton.isEnabled();
        assert backButton.isEnabled();
    }

    private JButton getEndModuleButton(OngoingModulePage ongoingModulePage) {
        JPanel buttonPanel = ongoingModulePage.setupButtonPanel();
        return (JButton) buttonPanel.getComponent(0);
    }

    private JButton getAddButton(OngoingModulePage ongoingModulePage) {
        JPanel buttonPanel = ongoingModulePage.setupButtonPanel();
        return (JButton) buttonPanel.getComponent(1);
    }

    private JButton getBackButton(OngoingModulePage ongoingModulePage) {
        JPanel buttonPanel = ongoingModulePage.setupButtonPanel();
        return (JButton) buttonPanel.getComponent(2);
    }
}
