package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoginScreen extends JFrame {

    public LoginScreen() {
        this.setTitle("Pizza Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundImageIcon = new ImageIcon(LoginScreen.class.getResource("/images/cart.jpg"));
        Image backgroundImage = backgroundImageIcon.getImage();

        setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension();
            }
        });

        setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension();
            }
        });

        JTextField usernameField = new JTextField(20);
        JTextField passwordField = new JTextField(20);
        JButton loginButton = new JButton("Login");

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(loginButton, gbc);

        getContentPane().add(inputPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                System.out.println("Username: " + username + ", Password: " + password);
                try {
                    JSONParser parser = new JSONParser();
                    InputStream inputStream = getClass().getResourceAsStream("/db/Users.json");

                    if (inputStream == null) {
                        System.err.println("Unable to find Users.json in the resources");
                        return;
                    }

                    try (InputStreamReader reader = new InputStreamReader(inputStream)) {
                        JSONParser jsonParser = new JSONParser();
                        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

                        // Iterate through the array elements
                        for (Object obj : jsonArray) {
                            JSONObject jsonObject = (JSONObject) obj;
                            // Process each JSONObject here
                            System.out.println(jsonObject.toJSONString());
                        }
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
        });
    }
}
