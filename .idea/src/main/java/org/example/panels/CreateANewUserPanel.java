package org.example.panels;

import org.example.Main;
import org.example.util.NewUser;
import org.example.util.Screen;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CreateANewUserPanel extends JPanel {
    private LoginPanel loginPanel;

    public CreateANewUserPanel(Main main) {
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

        JPanel inputPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(getClass().getResource("/images/back2.png")).getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        setBackground(Color.WHITE);
        inputPanel.setBackground(Color.WHITE);
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
                main.showScreen(Screen.LOGIN);
            }
        });

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String nameInput = nameField.getText();
                String phoneInput = phoneField.getText();
                String streetInput = streetField.getText();
                String cityInput = cityField.getText();
                String stateInput = stateField.getText();
                String zipCodeInput = zipCodeField.getText();
                String accountTypeInput = (String) accountTypeField.getSelectedItem();
                String usernameInput = usernameField.getText();
                String passwordInput = passwordField.getText();
                if(usernameInput.equals("") ||passwordInput.equals("")||nameInput.equals("")||phoneInput.equals("")||streetInput.equals("")||cityInput.equals("")||stateInput.equals("")||zipCodeInput.equals("")){
                    return;
                }
                try {
                    InputStream inputStream = getClass().getResourceAsStream("/db/Users.json");

                    if (inputStream == null) {
                        System.err.println("Unable to find Users.json in the resources");
                        return;
                    }

                    try (InputStreamReader reader = new InputStreamReader(inputStream)) {
                        JSONParser jsonParser = new JSONParser();
                        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

                        JSONObject newData = new JSONObject();
                        newData.put("name", nameInput);
                        newData.put("phone", phoneInput);
                        newData.put("street", streetInput);
                        newData.put("city", cityInput);
                        newData.put("state", stateInput);
                        newData.put("zipCode", zipCodeInput);
                        newData.put("accountType", accountTypeInput);
                        newData.put("username", usernameInput);
                        newData.put("password", passwordInput);
                        main.newUser = new NewUser(usernameInput, passwordInput);
                        jsonArray.add(newData);
                        try (FileWriter fileWriter = new FileWriter("src/main/resources/db/Users.json")) {
                            fileWriter.write(jsonArray.toJSONString());
                            System.out.println("Data appended to the existing JSON file successfully!");
                        } catch (IOException err) {
                            err.printStackTrace();
                        }

                    }
                } catch (IOException | ParseException err) {
                    err.printStackTrace();
                    err.printStackTrace();
                }
                loginPanel = new LoginPanel(main);
                main.frame.add(loginPanel, Screen.LOGIN.toString());
                main.showScreen(Screen.LOGIN);
            }
        });
    }


}
