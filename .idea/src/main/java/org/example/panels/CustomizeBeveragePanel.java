package org.example.panels;

import org.example.Main;
import org.example.util.CartItem;
import org.example.util.Screen;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CustomizeBeveragePanel extends JPanel {

    private double defaultPrice = 1;
    private double additional = 0;

    public CustomizeBeveragePanel(Main main) {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Customize Beverage");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.setBackground(Color.WHITE);
        add(topPanel, BorderLayout.NORTH);
        add(createMainPanel(main), BorderLayout.CENTER);
    }

    private JPanel createMainPanel(Main main) {
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(getClass().getResource("/images/back2.png")).getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        String[] softDrinkOptions = {"Coca-Cola", "Sprite", "Mountain-dew", "Pepsi", "Grape Fanta", "Orange Fanta"};
        JComboBox<String> softDrinkField = new JComboBox<>(softDrinkOptions);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Soft Drink:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(softDrinkField, gbc);


        String[] sizeOptions = {"Small", "Medium", "Large"};
        JComboBox<String> sizeField = new JComboBox<>(sizeOptions);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Size:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(sizeField, gbc);


        JTextField quantityField = new JTextField(10);
        quantityField.setText("1");

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Quantity:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(quantityField, gbc);

        JLabel backButton = new JLabel("Back to Menu");
        backButton.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.showScreen(Screen.MENU);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 11;
        panel.add(backButton, gbc);

        ImageIcon originalPizzaIcon = new ImageIcon(CustomizeBeveragePanel.class.getResource("/images/drink.jpg"));

        int newWidth = 350;
        int newHeight = 350;

        Image scaledPizzaImage = originalPizzaIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledPizzaIcon = new ImageIcon(scaledPizzaImage);

        JLabel pizzaImage = new JLabel(scaledPizzaIcon);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 9;
        gbc.insets = new Insets(5, 300, 5, -100);

        panel.add(pizzaImage, gbc);

        JLabel priceLabel = new JLabel("$" + defaultPrice);
        gbc.gridheight = 1;
        gbc.gridx = 3;
        gbc.gridy = 10;
        panel.add(priceLabel, gbc);

        JButton submitButton = new JButton("Add to Cart");

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String softDrinkInput = (String) softDrinkField.getSelectedItem();
                String sizeInput = (String) sizeField.getSelectedItem();
                Integer quantityInput = Integer.parseInt(quantityField.getText());
                ArrayList<String> itemDetailsArr = new ArrayList<>();
                itemDetailsArr.add(softDrinkInput);
                itemDetailsArr.add(sizeInput);
                itemDetailsArr.add("None");
                itemDetailsArr.add("None");
                itemDetailsArr.add("None");
                itemDetailsArr.add("None");
                main.addToCart(new CartItem("drink", itemDetailsArr, defaultPrice + additional, quantityInput));
                main.printCart();
                main.showScreen(Screen.MENU);
            }
        });

        gbc.gridx = 3;
        gbc.gridy = 11;
        panel.add(submitButton, gbc);

        sizeField.addActionListener(e -> {
            String sizeInput = (String) sizeField.getSelectedItem();
            double quantityInput = Double.parseDouble(quantityField.getText());
            if (sizeInput.equals("Small")) {
                additional = 0;
            }
            if (sizeInput.equals("Medium")) {
                additional = 1;
            }
            if (sizeInput.equals("Large")) {
                additional = 2;
            }
            double x = defaultPrice + additional;
            double price = x * quantityInput;
            priceLabel.setText("$" + price);
        });
        quantityField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTextChange();
            }

            private void handleTextChange() {
                    double quantityInput = Double.parseDouble(quantityField.getText());
                    double x = defaultPrice + additional;
                    double price = x * quantityInput;
                    priceLabel.setText("$" + price);

            }
        });

        return panel;
    }
}