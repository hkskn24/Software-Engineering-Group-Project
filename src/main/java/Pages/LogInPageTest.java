package main.java.Pages;

import com.formdev.flatlaf.FlatDarculaLaf;
import main.java.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Controller.RecoverPasswordActionListener;
import main.java.Controller.RegisterActionListener;
import main.java.Controller.RetrievePasswordActionListener;

import javax.swing.*;
import java.awt.*;

public class LogInPageTest {
    private LogInPage logInPage;

    @BeforeEach
    public void setUp() {
        FlatDarculaLaf.setup();
        logInPage = new LogInPage();
    }

    @Test
    public void testExistedUsernameAndPassword() {
        //测试有效登陆
        logInPage.usernameField.setText("aoi");
        logInPage.passwordField.setText("111111");
        logInPage.userType = 0;
        logInPage.actionPerformed(null);
        Assertions.assertEquals("students", Config.getUserType());
        Assertions.assertEquals("aoi", Config.getUsername());
        Assertions.assertFalse(logInPage.isVisible());
        logInPage.dispose();
    }

    @Test
    public void testnotexistedUsername() {
        //测试不存在用户
        logInPage.usernameField.setText("pumpkin");
        logInPage.passwordField.setText("111111");
        Assertions.assertTrue(logInPage.isVisible());
    }

    @Test
    public void testWrongPassword() {
        //测试密码错误
        logInPage.usernameField.setText("aoi");
        logInPage.passwordField.setText("666666");
        logInPage.actionPerformed(null);

        Window[] windows = Window.getWindows();
        boolean messageDialogFound = false;
        for (Window window : windows) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                if (dialog.getContentPane().getComponentCount() > 0 && dialog.getContentPane().getComponent(0) instanceof JOptionPane) {
                    JOptionPane messageDialog = (JOptionPane) dialog.getContentPane().getComponent(0);
                    if (messageDialog.getMessage().equals("Incorrect username or password!")) {
                        messageDialogFound = true;
                        break;
                    }
                }
            }
        }
        Assertions.assertTrue(messageDialogFound);
        Assertions.assertTrue(logInPage.isVisible());
    }

    @Test
    public void testInvalidPassword() {
        //测试密码格式无效
        logInPage.usernameField.setText("aoi");
        logInPage.passwordField.setText("InvalidPassword");
        logInPage.actionPerformed(null);

        Window[] windows = Window.getWindows();
        boolean messageDialogFound = false;
        for (Window window : windows) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                if (dialog.getContentPane().getComponentCount() > 0 && dialog.getContentPane().getComponent(0) instanceof JOptionPane) {
                    JOptionPane messageDialog = (JOptionPane) dialog.getContentPane().getComponent(0);
                    if (messageDialog.getMessage().equals("Please ensure your password consists of 6-10 digits or English characters only.")) {
                        messageDialogFound = true;
                        break;
                    }
                }
            }
        }
        Assertions.assertTrue(messageDialogFound);
        Assertions.assertTrue(logInPage.isVisible());
    }

    @Test
    public void testInvalidUsername() {
        //测试用户名格式无效
        logInPage.usernameField.setText("aoi123");
        logInPage.passwordField.setText("111111");
        logInPage.actionPerformed(null);

        Window[] windows = Window.getWindows();
        boolean messageDialogFound = false;
        for (Window window : windows) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                if (dialog.getContentPane().getComponentCount() > 0 && dialog.getContentPane().getComponent(0) instanceof JOptionPane) {
                    JOptionPane messageDialog = (JOptionPane) dialog.getContentPane().getComponent(0);
                    if (messageDialog.getMessage().equals("Please ensure your username consists of Chinese characters or English letters only.")) {
                        messageDialogFound = true;
                        break;
                    }
                }
            }
        }
        Assertions.assertTrue(messageDialogFound);
        Assertions.assertTrue(logInPage.isVisible());
    }
}
