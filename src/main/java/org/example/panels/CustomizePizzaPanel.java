package org.example.panels;

import org.example.Main;
import org.example.util.CartItem;
import org.example.util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CustomizePizzaPanel extends JPanel {
    private int price = 7;

    public CustomizePizzaPanel(Main main) {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Customize Pizza");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(createMainPanel(main), BorderLayout.CENTER);
    }

    private JPanel createMainPanel(Main main) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[] sizeOptions = {"Small", "Medium", "Large", "Extra Large"};
        JComboBox<String> sizeField = new JComboBox<>(sizeOptions);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Size:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(sizeField, gbc);

        String[] crustOptions = {"Thin", "Thick ", "Stuffed"};
        JComboBox<String> crustField = new JComboBox<>(crustOptions);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Crust:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(crustField, gbc);

        String[] toppingOptions = {"None", "Pepperoni", "Extra Cheese", "Mushroom", "Onions", "Sausage", "Black Olives", "Green Peppers", "Pineapple"};
        JComboBox<String> topping1Field = new JComboBox<>(toppingOptions);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Topping 1:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(topping1Field, gbc);

        JComboBox<String> topping2Field = new JComboBox<>(toppingOptions);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Topping 2:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(topping2Field, gbc);

        JComboBox<String> topping3Field = new JComboBox<>(toppingOptions);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Topping 3:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(topping3Field, gbc);

        JComboBox<String> topping4Field = new JComboBox<>(toppingOptions);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Topping 4:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(topping4Field, gbc);

        JTextField quantityField = new JTextField(10);

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
        gbc.gridy = 9;
        panel.add(backButton, gbc);

        ImageIcon originalPizzaIcon = new ImageIcon(CustomizePizzaPanel.class.getResource("/images/pizzaM3.jpg"));

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

        JButton submitButton = new JButton("Add to Cart");

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String sizeInput = (String) sizeField.getSelectedItem();
                String crustInput = (String) crustField.getSelectedItem();
                String topping1Input = (String) topping1Field.getSelectedItem();
                String topping2Input = (String) topping2Field.getSelectedItem();
                String topping3Input = (String) topping3Field.getSelectedItem();
                String topping4Input = (String) topping4Field.getSelectedItem();
                Integer quantityInput = Integer.parseInt(quantityField.getText());
                ArrayList<String> itemDetailsArr = new ArrayList<>();
                itemDetailsArr.add(sizeInput);
                itemDetailsArr.add(crustInput);
                itemDetailsArr.add(topping1Input);
                itemDetailsArr.add(topping2Input);
                itemDetailsArr.add(topping3Input);
                itemDetailsArr.add(topping4Input);
                main.addToCart(new CartItem("pizza", itemDetailsArr, 5, quantityInput));
                main.printCart();
                main.showScreen(Screen.MENU);
            }


        });

        gbc.gridx = 3;
        gbc.gridy = 9;
        panel.add(submitButton, gbc);

        return panel;
    }
}