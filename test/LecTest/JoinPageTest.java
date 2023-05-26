package main.java.Pages.LecturerPages;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class JoinPageTest {

    @Test
    public void testButtonAvailability() {
        JoinPage joinPage = new JoinPage();

        JButton joinButton = getJoinButton(joinPage);
        JButton backButton = getBackButton(joinPage);

        assert joinButton.isEnabled();
        assert backButton.isEnabled();
    }

    private JButton getJoinButton(JoinPage joinPage) {
        JPanel buttonPanel = joinPage.setupButtonPanel();
        return (JButton) buttonPanel.getComponent(0);
    }

    private JButton getBackButton(JoinPage joinPage) {
        JPanel buttonPanel = joinPage.setupButtonPanel();
        return (JButton) buttonPanel.getComponent(1);
    }
}