package main.java.Pages;

import main.java.Controller.RegisterActionListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterActionListenerTest {
    private RegisterActionListener registerActionListener;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField idField;

    @BeforeEach
    public void setup() {
        int userType = 0;
        registerActionListener = new RegisterActionListener(userType);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        idField = new JTextField();
    }

    @Test
    public void testActionPerformed_InvalidUsername() {
        // 输入无效的用户名（包含特殊字符）
        usernameField.setText("test*user");
        passwordField.setText("password");
        idField.setText("1234567890");

        JPanel registerPanel = new JPanel();
        JFrame registerWindow = new JFrame("Sign up");
        registerWindow.add(registerPanel);

        JButton registerButton = new JButton("Sign up");

        registerActionListener.actionPerformed(null);

        assertFalse(registerWindow.isVisible());
        assertTrue(registerWindow.getContentPane().getComponents()[0].isEnabled());
    }

    @Test
    public void testActionPerformed_InvalidPassword() {
        // 输入无效的密码（长度不足）
        usernameField.setText("testuser");
        passwordField.setText("pass");
        idField.setText("1234567890");

        JPanel registerPanel = new JPanel();
        JFrame registerWindow = new JFrame("Sign up");
        registerWindow.add(registerPanel);

        JButton registerButton = new JButton("Sign up");

        registerActionListener.actionPerformed(null);

        assertFalse(registerWindow.isVisible());
        assertTrue(registerWindow.getContentPane().getComponents()[0].isEnabled());
    }

    @Test
    public void testActionPerformed_InvalidID() {
        // 输入无效的学号（不是10位数字）
        usernameField.setText("testuser");
        passwordField.setText("password");
        idField.setText("123");

        JPanel registerPanel = new JPanel();
        JFrame registerWindow = new JFrame("Sign up");
        registerWindow.add(registerPanel);

        JButton registerButton = new JButton("Sign up");

        registerActionListener.actionPerformed(null);

        assertFalse(registerWindow.isVisible());
        assertTrue(registerWindow.getContentPane().getComponents()[0].isEnabled());
    }
}
