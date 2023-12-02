package org.example.panels;

import org.example.Main;
import org.example.util.CurrentUser;
import org.example.util.Screen;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoginPanel extends JPanel {
    private MenuPanel menuPanel;
    public LoginPanel(Main main) {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);


        JTextField usernameField = new JTextField(20);
        JTextField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JLabel createNewUserLabel = new JLabel("Create A New User");

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

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        inputPanel.add(createNewUserLabel, gbc);


        this.add(inputPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String usernameInput = usernameField.getText();
                String passwordInput = passwordField.getText();
                System.out.println("Username: " + usernameInput + ", Password: " + passwordInput);
                try {
                    InputStream inputStream = getClass().getResourceAsStream("/db/Users.json");

                    if (inputStream == null) {
                        System.err.println("Unable to find Users.json in the resources");
                        return;
                    }

                    try (InputStreamReader reader = new InputStreamReader(inputStream)) {
                        JSONParser jsonParser = new JSONParser();
                        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

                        for (Object obj : jsonArray) {
                            JSONObject jsonObject = (JSONObject) obj;
                            String username = (String) jsonObject.get("username");
                            String password = (String) jsonObject.get("password");
                            if (username.equals(usernameInput) && password.equals(passwordInput)) {

                                main.currentUser = new CurrentUser((String) jsonObject.get("name"), (String) jsonObject.get("phone"), (String) jsonObject.get("street"), (String) jsonObject.get("city"), (String) jsonObject.get("state"), (String) jsonObject.get("zipCode"));
                                menuPanel = new MenuPanel(main);
                                main.frame.add(menuPanel, Screen.MENU.toString());
                                main.showScreen(Screen.MENU);
                                main.cart= new ArrayList<>();
                            }
                            if(main.newUser!=null) {
                                if (main.newUser.username.equals(usernameInput) && main.newUser.password.equals(passwordInput)) {

                                    main.currentUser = new CurrentUser((String) jsonObject.get("name"), (String) jsonObject.get("phone"), (String) jsonObject.get("street"), (String) jsonObject.get("city"), (String) jsonObject.get("state"), (String) jsonObject.get("zipCode"));
                                    menuPanel = new MenuPanel(main);
                                    main.frame.add(menuPanel, Screen.MENU.toString());
                                    main.showScreen(Screen.MENU);
                                    main.cart= new ArrayList<>();
                                }
                            }
                        }
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        createNewUserLabel.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));
        createNewUserLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.showScreen(Screen.CREATE_A_NEW_USER);
            }
        });
    }
}
