package org.example;

import org.example.screens.LoginPanel;
import org.example.screens.MenuPanel;
import org.example.util.Screen;

import javax.swing.*;
import java.awt.*;


public class Main {
    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main example = new Main();
            example.showScreen(Screen.LOGIN);
        });
    }

    public Main() {
        frame = new JFrame("Pizza Project");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());

        LoginPanel mainMenuPanel = new LoginPanel(this);
        MenuPanel settingsPanel = new MenuPanel(this);

        frame.add(mainMenuPanel, Screen.LOGIN.toString());
        frame.add(settingsPanel, Screen.MENU.toString());

        frame.setVisible(true);
    }

    public void showScreen(Screen screen) {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), screen.toString());
    }
}
