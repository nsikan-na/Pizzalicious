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

public class CustomizePizzaPanel extends JPanel {
    private double defaultPrice = 5;

    private double sizeAdditional = 0;

    private double crustAdditional = 0;

    private double topping1Additional = 0;
    private double topping2Additional = 0;
    private double topping3Additional = 0;
    private double topping4Additional = 0;

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

        String[] crustOptions = {"Thin", "Thick", "Stuffed"};
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
        gbc.gridy = 10;
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

        JLabel priceLabel = new JLabel("$" + defaultPrice);
        gbc.gridheight = 1;
        gbc.gridx = 3;
        gbc.gridy = 9;
        panel.add(priceLabel, gbc);

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
                main.addToCart(new CartItem("pizza", itemDetailsArr, defaultPrice + sizeAdditional + crustAdditional + topping1Additional + topping2Additional + topping3Additional + topping4Additional, quantityInput));
                main.printCart();
                main.showScreen(Screen.MENU);
            }


        });

        gbc.gridx = 3;
        gbc.gridy = 10;
        panel.add(submitButton, gbc);

        sizeField.addActionListener(e -> {
            String sizeInput = (String) sizeField.getSelectedItem();
            double quantityInput = Double.parseDouble(quantityField.getText());
            if (sizeInput.equals("Small")) {
                sizeAdditional = 0;
            }
            if (sizeInput.equals("Medium")) {
                sizeAdditional = 2;
            }
            if (sizeInput.equals("Large")) {
                sizeAdditional = 4;
            }
            if (sizeInput.equals("Extra Large")) {
                sizeAdditional = 6;
            }
            double x = defaultPrice + sizeAdditional + crustAdditional + topping1Additional + topping2Additional + topping3Additional + topping4Additional;
            double price = x * quantityInput;
            priceLabel.setText("$" + price);
        });

        crustField.addActionListener(e -> {
            String crustInput = (String) crustField.getSelectedItem();
            double quantityInput = Double.parseDouble(quantityField.getText());
            if (crustInput.equals("Thin")) {
                crustAdditional = 0;
            }
            if (crustInput.equals("Thick")) {
                crustAdditional = 1;
            }
            if (crustInput.equals("Stuffed")) {
                crustAdditional = 2;
            }
            double x = defaultPrice + sizeAdditional + crustAdditional + topping1Additional + topping2Additional + topping3Additional + topping4Additional;
            double price = x * quantityInput;
            priceLabel.setText("$" + price);
        });
        topping1Field.addActionListener(e -> {
            String topping1Input = (String) topping1Field.getSelectedItem();
            double quantityInput = Double.parseDouble(quantityField.getText());
            if (topping1Input.equals("None")) {
                topping1Additional = 0;
            }else{
                topping1Additional = 1;
            }
            double x = defaultPrice + sizeAdditional + crustAdditional + topping1Additional + topping2Additional + topping3Additional + topping4Additional;
            double price = x * quantityInput;
            priceLabel.setText("$" + price);
        });
        topping2Field.addActionListener(e -> {
            String topping2Input = (String) topping2Field.getSelectedItem();
            double quantityInput = Double.parseDouble(quantityField.getText());
            if (topping2Input.equals("None")) {
                topping2Additional = 0;
            }else{
                topping2Additional = 1;
            }
            double x = defaultPrice + sizeAdditional + crustAdditional + topping1Additional + topping2Additional + topping3Additional + topping4Additional;
            double price = x * quantityInput;
            priceLabel.setText("$" + price);
        });
        topping3Field.addActionListener(e -> {
            String topping3Input = (String) topping3Field.getSelectedItem();
            double quantityInput = Double.parseDouble(quantityField.getText());
            if (topping3Input.equals("None")) {
                topping3Additional = 0;
            }else{
                topping3Additional = 1;
            }
            double x = defaultPrice + sizeAdditional + crustAdditional + topping1Additional + topping2Additional + topping3Additional + topping4Additional;
            double price = x * quantityInput;
            priceLabel.setText("$" + price);
        });
        topping4Field.addActionListener(e -> {
            String topping4Input = (String) topping4Field.getSelectedItem();
            double quantityInput = Double.parseDouble(quantityField.getText());
            if (topping4Input.equals("None")) {
                topping4Additional = 0;
            }else{
                topping4Additional = 1;
            }
            double x = defaultPrice + sizeAdditional + crustAdditional + topping1Additional + topping2Additional + topping3Additional + topping4Additional;
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
                handleTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTextChange();
            }

            private void handleTextChange() {
                double quantityInput = Double.parseDouble(quantityField.getText());
                double x = defaultPrice + sizeAdditional + crustAdditional + topping1Additional + topping2Additional + topping3Additional + topping4Additional;
                double price = x * quantityInput;
                priceLabel.setText("$" + price);

            }
        });
        return panel;
    }
}