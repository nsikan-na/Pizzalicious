package org.example.screens;

import org.example.Main;
import org.example.util.Screen;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(Main navigation) {
        setLayout(new BorderLayout());

        JButton goToMainMenuButton = new JButton("Go to Main Menu");
        goToMainMenuButton.addActionListener(e -> navigation.showScreen(Screen.MENU));

        add(new JLabel("Settings Screen"), BorderLayout.CENTER);
        add(goToMainMenuButton, BorderLayout.SOUTH);
    }
}
