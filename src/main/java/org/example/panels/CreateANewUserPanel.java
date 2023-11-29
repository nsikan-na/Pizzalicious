package org.example.panels;

import org.example.Main;
import org.example.util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateANewUserPanel extends JPanel {
    public CreateANewUserPanel(Main navigation) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextField nameField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField streetField = new JTextField(20);
        JTextField cityField = new JTextField(20);
        JTextField stateField = new JTextField(20);
        JTextField zipCodeField = new JTextField(20);
        JLabel backButton = new JLabel("Back");
        JButton submitButton = new JButton("Submit");

        String[] accountTypeOptions = {"VISA", "MASTERCARD"};
        JComboBox<String> accountTypeField = new JComboBox<>(accountTypeOptions);

        JTextField usernameField = new JTextField(20);
        JTextField passwordField = new JTextField(20);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);


        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Phone:"), gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        inputPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Street:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(streetField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("City:"), gbc);

        gbc.gridx = 4;
        gbc.gridy = 2;
        inputPanel.add(cityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("State:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(stateField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Zip Code:"), gbc);

        gbc.gridx = 4;
        gbc.gridy = 3;
        inputPanel.add(zipCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Account Type:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        inputPanel.add(accountTypeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        inputPanel.add(usernameField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 4;
        gbc.gridy = 5;
        inputPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        inputPanel.add(backButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 6;
        inputPanel.add(submitButton, gbc);

        this.add(inputPanel, BorderLayout.CENTER);

        backButton.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.LOGIN);
            }
        });

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.LOGIN);
            }
        });
    }


}
