package org.example.panels;

import org.example.Main;
import org.example.util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomizePizzaPanel extends JPanel {
    public CustomizePizzaPanel(Main navigation) {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Hi, Mark");
        welcomeLabel.setHorizontalAlignment(JLabel.RIGHT);
        topPanel.add(welcomeLabel, BorderLayout.NORTH);

        JLabel logoutLabel = new JLabel("Logout");
        logoutLabel.setHorizontalAlignment(JLabel.RIGHT);
        topPanel.add(logoutLabel, BorderLayout.EAST);
        logoutLabel.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));

        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.LOGIN);
            }
        });

        JLabel titleLabel = new JLabel("Customize Pizza");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(createLeftPanel(navigation), BorderLayout.LINE_START);
        add(createRightPanel(navigation), BorderLayout.LINE_END);
    }

    private JPanel createLeftPanel(Main navigation) {
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String[] sizeOptions = {"Small", "Medium", "Large", "Extra Large"};
        JComboBox<String> sizeField = new JComboBox<>(sizeOptions);
        gbc.insets = new Insets(5, 250, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        leftPanel.add(new JLabel("Size:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        leftPanel.add(sizeField, gbc);

        String[] crustOptions = {"Thin", "Thick", "Stuffed"};
        JComboBox<String> crustField = new JComboBox<>(crustOptions);

        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(new JLabel("Crust:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        leftPanel.add(crustField, gbc);

        String[] toppingOptions = {"None", "Pepperoni", "Extra Cheese", "Mushroom", "Onions", "Sausage", "Black Olives", "Green Peppers", "Pineapple"};
        JComboBox<String> topping1Field = new JComboBox<>(toppingOptions);

        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel.add(new JLabel("Topping 1:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        leftPanel.add(topping1Field, gbc);

        JComboBox<String> topping2Field = new JComboBox<>(toppingOptions);

        gbc.gridx = 0;
        gbc.gridy = 4;
        leftPanel.add(new JLabel("Topping 2:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        leftPanel.add(topping2Field, gbc);

        JComboBox<String> topping3Field = new JComboBox<>(toppingOptions);

        gbc.gridx = 0;
        gbc.gridy = 5;
        leftPanel.add(new JLabel("Topping 3:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        leftPanel.add(topping3Field, gbc);

        JComboBox<String> topping4Field = new JComboBox<>(toppingOptions);

        gbc.gridx = 0;
        gbc.gridy = 6;
        leftPanel.add(new JLabel("Topping 4:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        leftPanel.add(topping4Field, gbc);

        JTextField quantityField = new JTextField(10);

        gbc.gridx = 0;
        gbc.gridy = 7;
        leftPanel.add(new JLabel("Quantity:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        leftPanel.add(quantityField, gbc);

        JLabel backButton = new JLabel("Back");
        backButton.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.MENU);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 8;
        leftPanel.add(backButton, gbc);


        return leftPanel;
    }

    private JPanel createRightPanel(Main navigation) {
        JPanel rightPanel = new JPanel(new GridBagLayout());

        ImageIcon originalPizzaIcon = new ImageIcon(CustomizePizzaPanel.class.getResource("/images/pizzaM3.jpg"));

        int newWidth = 350;
        int newHeight = 350;

        Image scaledBeverageImage = originalPizzaIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledBeverageIcon = new ImageIcon(scaledBeverageImage);

        JLabel beverageImageLabel = new JLabel(scaledBeverageIcon);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 500);

        gbc.anchor = GridBagConstraints.CENTER;

        rightPanel.add(beverageImageLabel, gbc);

        JButton submitButton = new JButton("Submit");

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.MENU);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        rightPanel.add(submitButton, gbc);

        return rightPanel;
    }
}