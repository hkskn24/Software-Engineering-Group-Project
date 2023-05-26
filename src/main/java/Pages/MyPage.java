package main.java.Pages;

import javax.swing.*;

/**
 * Set the basic settings for all pages
 *
 * @author : Yunxin Wang
 * @version : v4.0
 */
public class MyPage extends JFrame {
    /**
     * set up the pages
     */
    public MyPage() {
        setBounds(500, 300, 1094, 729);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }
}
