package org.example.panels;

import org.example.Main;
import org.example.util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PaymentDeliveryPanel extends JPanel {
    private JComboBox<String> paymentMethodField;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public PaymentDeliveryPanel(Main navigation) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Payment");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 600, 5, 5);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Delivery Method:"), gbc);

        String[] deliveryMethodOptions = {"Pickup", "Delivery"};
        JComboBox<String> deliveryMethodField = new JComboBox<>(deliveryMethodOptions);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        inputPanel.add(deliveryMethodField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 600, 5, 5);
        inputPanel.add(new JLabel("Payment Method:"), gbc);

        String[] paymentMethodOptions = {"Cash", "Card", "Check"};
        paymentMethodField = new JComboBox<>(paymentMethodOptions);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        inputPanel.add(paymentMethodField, gbc);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createCashPanel(navigation), "Cash");
        cardPanel.add(createCheckPanel(navigation), "Check");
        cardPanel.add(createCardPanel(navigation), "Card");

        this.add(inputPanel, BorderLayout.LINE_START);
        this.add(cardPanel, BorderLayout.CENTER);

        paymentMethodField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPaymentMethod = (String) paymentMethodField.getSelectedItem();
                cardLayout.show(cardPanel, selectedPaymentMethod);
            }
        });

        JLabel backButton = new JLabel("Back to Menu");

        backButton.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.MENU);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;

        inputPanel.add(backButton, gbc);
    }

    public JPanel createCardPanel(Main navigation) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[] accountTypeOptions = {"VISA", "MASTERCARD"};
        JComboBox<String> accountTypeField = new JComboBox<>(accountTypeOptions);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Account Type:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 300);
        panel.add(accountTypeField, gbc);

        JTextField nameField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 300);
        panel.add(nameField, gbc);

        JTextField expirationDateField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(new JLabel("Exp Date:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 300);
        panel.add(expirationDateField, gbc);

        JTextField cardNumberField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(new JLabel("Card #:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 300);
        panel.add(cardNumberField, gbc);

        JTextField cvvField = new JTextField(10);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(new JLabel("Cvv:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 300);
        panel.add(cvvField, gbc);

        JButton submitButton = new JButton("Review Order");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 5, 5, 300);
        panel.add(submitButton, gbc);
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.REVIEW_ORDER);
            }
        });

        return panel;
    }

    public JPanel createCheckPanel(Main navigation) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JTextField accountNumberField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, -500, 5, 5);
        panel.add(new JLabel("Account #:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, -250, 5, 5);
        panel.add(accountNumberField, gbc);

        JTextField routingNumberField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, -500, 5, 5);

        panel.add(new JLabel("Routing #:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, -250, 5, 5);
        panel.add(routingNumberField, gbc);

        JButton submitButton = new JButton("Review Order");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(5, -250, 5, 5);
        panel.add(submitButton, gbc);
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.REVIEW_ORDER);
            }
        });

        return panel;
    }

    public JPanel createCashPanel(Main navigation) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        JButton submitButton = new JButton("Review Order");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 5, 5, 300);
        panel.add(submitButton, gbc);
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.REVIEW_ORDER);
            }
        });

        return panel;
    }
}
