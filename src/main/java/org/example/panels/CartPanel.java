package org.example.panels;

import org.example.Main;
import org.example.util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

public class CartPanel extends JPanel {
    private int itemCount = 0;
    private long totalPrice = 0;
    private int rowHeight = 6;
    private int arraySize = 0;

    public CartPanel(Main main) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(createMainPanel(main));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

    }

    private JPanel createMainPanel(Main main) {


        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(getClass().getResource("/images/back2.png")).getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel totalPriceLabel = new JLabel("Total: $" + totalPrice);
        if (main.cart.size() <= 0) {
            JLabel backButton = new JLabel("Back to Menu");
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("Cart is Empty"), gbc);
            backButton.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));

            backButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    main.showScreen(Screen.MENU);
                }
            });
            gbc.insets = new Insets(15, 5, 15, 5);

            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(backButton, gbc);


            return panel;
        }
        arraySize = main.getCartSize();

        for (int i = 0; i < arraySize; i++) {
            final int currentItemIndex = i;
            String item = main.getCart().get(i).getItem();
            ArrayList<String> itemDetails = main.getCart().get(i).getItemDetails();
            Double pricePerItem = main.getCart().get(i).getPricePerItem();
            Integer quantity = main.getCart().get(i).getQuantity();


            ImageIcon originalIcon = new ImageIcon(CustomizePizzaPanel.class.getResource(item.equals("pizza") ? "/images/pizzaM3.jpg" : "/images/drink.jpg"));

            int newWidth = 150;
            int newHeight = 150;


            Image scaledImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel pizzaImage = new JLabel(scaledIcon);

            gbc.gridx = 0;
            gbc.gridy = i * rowHeight + 1;
            gbc.gridheight = rowHeight;
            gbc.insets = new Insets(5, 25, 5, 25);

            panel.add(pizzaImage, gbc);

            for (int j = 0; j < itemDetails.size(); j++) {
                JLabel itemDetailslabel = new JLabel(itemDetails.get(j).equals("None")?"":itemDetails.get(j));
                gbc.gridx = 1;
                gbc.gridy = itemCount + 1;
                gbc.gridheight = 1;

                panel.add(itemDetailslabel, gbc);
                itemCount++;
            }

            JButton minusButton = new JButton("-");
            gbc.gridx = 2;
            gbc.gridy = i * rowHeight + 1;
            gbc.gridheight = rowHeight;
            gbc.gridwidth = 1;
            panel.add(minusButton, gbc);


            JLabel quantitylabel = new JLabel("x" + quantity.toString());
            gbc.gridx = 3;
            gbc.gridy = i * rowHeight + 1;
            gbc.gridheight = rowHeight;
            gbc.gridwidth = 1;
            panel.add(quantitylabel, gbc);


            JButton addButton = new JButton("+");
            gbc.gridx = 4;
            gbc.gridy = i * rowHeight + 1;
            gbc.gridheight = rowHeight;
            gbc.gridwidth = 1;
            panel.add(addButton, gbc);


            double price = quantity * pricePerItem;
            totalPrice += price;
            totalPriceLabel.setText("Total: $" + totalPrice);
            JLabel pricelabel = new JLabel("$" + price);
            gbc.gridx = 5;
            gbc.gridy = i * rowHeight + 1;
            gbc.gridheight = rowHeight;

            panel.add(pricelabel, gbc);
            minusButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(main.getCart().get(currentItemIndex).getQuantity()>0) {
                        main.getCart().get(currentItemIndex).decreaseQuantity();
                        quantitylabel.setText("x" + main.getCart().get(currentItemIndex).getQuantity());
                        pricelabel.setText("" + main.getCart().get(currentItemIndex).getQuantity() * main.getCart().get(currentItemIndex).getPricePerItem());
                        totalPrice -= main.getCart().get(currentItemIndex).getPricePerItem();
                        totalPriceLabel.setText("Total: $" + totalPrice);
                    }
                }
            });
            addButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    main.getCart().get(currentItemIndex).increaseQuantity();
                    quantitylabel.setText("x" + main.getCart().get(currentItemIndex).getQuantity());
                    pricelabel.setText("" + main.getCart().get(currentItemIndex).getQuantity() * main.getCart().get(currentItemIndex).getPricePerItem());
                    totalPrice += main.getCart().get(currentItemIndex).getPricePerItem();
                    totalPriceLabel.setText("Total: $" + totalPrice);
                }
            });

        }

        gbc.gridx = 5;
        gbc.gridy = arraySize * rowHeight + 2;
        gbc.gridheight = rowHeight;

        panel.add(totalPriceLabel, gbc);


        JLabel backButton = new JLabel("Back to Menu");

        backButton.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.showScreen(Screen.MENU);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = arraySize * rowHeight + 8;
        panel.add(backButton, gbc);

        JButton submitButton = new JButton("Proceed to Payment/Delivery");

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.showScreen(Screen.PAYMENT_DELIVERY);
            }
        });

        gbc.gridx = 5;
        gbc.gridy = arraySize * rowHeight + 8;
        panel.add(submitButton, gbc);

        return panel;
    }


}
