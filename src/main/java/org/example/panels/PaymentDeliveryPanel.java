package org.example.panels;

import org.example.Main;

import javax.swing.*;
import java.awt.*;


public class PaymentDeliveryPanel extends JPanel {
    public PaymentDeliveryPanel(Main navigation) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Payment");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);



        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Delivery Method:"), gbc);

        String[] deliveryMethodOptions = {"Pickup", "Delivery"};
        JComboBox<String> deliveryMethodField = new JComboBox<>(deliveryMethodOptions);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(deliveryMethodField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Payment Method:"), gbc);

        String[] paymentMethodOptions = {"Cash", "Card", "Check"};
        JComboBox<String> paymentMethodField = new JComboBox<>(paymentMethodOptions);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(paymentMethodField, gbc);


        this.add(inputPanel, BorderLayout.CENTER);




    }
}
