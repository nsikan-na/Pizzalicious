package org.example.panels;

import org.example.Main;
import org.example.util.Screen;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

public class ReceiptPanel extends JPanel {
    private int itemCount = 0;
    private long totalPrice = 0;
    private int rowHeight = 6;
    private int arraySize = 0;

    public ReceiptPanel(Main main) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Receipt");
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
        setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name: " + main.currentUser.getName());
        nameLabel.setBackground(Color.WHITE);
        nameLabel.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 2;

        panel.add(nameLabel, gbc);

        JLabel phoneLabel = new JLabel("Phone: " + main.currentUser.getPhone());
        phoneLabel.setBackground(Color.WHITE);
        phoneLabel.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(phoneLabel, gbc);

        JLabel streetLabel = new JLabel("Street: " + main.currentUser.getStreet());
        streetLabel.setBackground(Color.WHITE);
        streetLabel.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 4;

        panel.add(streetLabel, gbc);

        JLabel cityLabel = new JLabel("City: " + main.currentUser.getCity());
        cityLabel.setBackground(Color.WHITE);
        cityLabel.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(cityLabel, gbc);

        JLabel stateLabel = new JLabel("State: " + main.currentUser.getState());
        stateLabel.setBackground(Color.WHITE);
        stateLabel.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 6;

        panel.add(stateLabel, gbc);

        JLabel zipLabel = new JLabel("Zip: " + main.currentUser.getZip());
        zipLabel.setBackground(Color.WHITE);
        zipLabel.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(zipLabel, gbc);


        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel thankYouLabel = new JLabel("Thank You For The Order");
        thankYouLabel.setBackground(Color.WHITE);
        thankYouLabel.setOpaque(true);
        panel.add(thankYouLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;

        JLabel orderNumLabel = new JLabel("Order #: 198");
        orderNumLabel.setBackground(Color.WHITE);
        orderNumLabel.setOpaque(true);
        panel.add(orderNumLabel, gbc);


        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel deliveryLabel = new JLabel("Delivery Method: " + main.deliveryMethod);
        deliveryLabel.setBackground(Color.WHITE);
        deliveryLabel.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 8;

        panel.add(deliveryLabel, gbc);

        JLabel paymentLabel = new JLabel("Payment Method: " + main.paymentMethod);
        paymentLabel.setBackground(Color.WHITE);
        paymentLabel.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(paymentLabel, gbc);
        gbc.insets = new Insets(25, 5, 25, 5);
        JLabel checkLineLabel = new JLabel("x____________________________________________________________________");
        checkLineLabel.setBackground(Color.WHITE);
        checkLineLabel.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 10;
        if (main.paymentMethod.equals("Card")) {
            panel.add(checkLineLabel, gbc);
        }
        gbc.insets = new Insets(5, 5, 5, 5);


        arraySize = main.getCartSize();

        for (int i = 0; i < arraySize; i++) {
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
            gbc.gridy = i * rowHeight + 13;
            gbc.gridheight = rowHeight;
            gbc.insets = new Insets(5, 25, 5, 25);

            panel.add(pizzaImage, gbc);

            for (int j = 0; j < itemDetails.size(); j++) {
                JLabel itemDetailslabel = new JLabel(itemDetails.get(j).equals("None") ? "" : itemDetails.get(j));
                itemDetailslabel.setBackground(Color.WHITE);
                itemDetailslabel.setOpaque(true);
                gbc.gridx = 1;
                gbc.gridy = itemCount + 13;
                gbc.gridheight = 1;

                panel.add(itemDetailslabel, gbc);
                itemCount++;
            }

            JLabel quantitylabel = new JLabel("x" + quantity.toString());
            quantitylabel.setBackground(Color.WHITE);
            quantitylabel.setOpaque(true);
            gbc.gridx = 3;
            gbc.gridy = i * rowHeight + 13;
            gbc.gridheight = rowHeight;
            gbc.gridwidth = 1;
            panel.add(quantitylabel, gbc);

            double price = quantity * pricePerItem;
            totalPrice += price;

            JLabel pricelabel = new JLabel("$" + price);
            pricelabel.setBackground(Color.WHITE);
            pricelabel.setOpaque(true);
            gbc.gridx = 5;
            gbc.gridy = i * rowHeight + 13;
            gbc.gridheight = rowHeight;

            panel.add(pricelabel, gbc);


        }
        JLabel totalPriceLabel = new JLabel("Total: $" + totalPrice);
        totalPriceLabel.setBackground(Color.WHITE);
        totalPriceLabel.setOpaque(true);
        gbc.gridx = 5;
        gbc.gridy = arraySize * rowHeight + 14;
        gbc.gridheight = rowHeight;

        panel.add(totalPriceLabel, gbc);


        JButton submitButton = new JButton("Start New Order");

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.cart = new ArrayList<>();
                main.showScreen(Screen.MENU);
            }
        });

        gbc.gridx = 5;
        gbc.gridy = arraySize * rowHeight + 20;
        panel.add(submitButton, gbc);

        return panel;
    }


}
