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

    private ReviewOrderPanel reviewOrderPanel;
    private JComboBox<String> paymentMethodField;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public PaymentDeliveryPanel(Main main) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Payment Method");
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
        deliveryMethodField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.deliveryMethod = (String) deliveryMethodField.getSelectedItem();
            }
        });
        main.deliveryMethod = "Pickup";
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
        paymentMethodField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.paymentMethod = (String) paymentMethodField.getSelectedItem();
            }
        });
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createCashPanel(main), "Cash");
        cardPanel.add(createCheckPanel(main), "Check");
        cardPanel.add(createCardPanel(main), "Card");
        main.paymentMethod = "Cash";
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
                main.showScreen(Screen.MENU);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;

        inputPanel.add(backButton, gbc);
    }

    public JPanel createCardPanel(Main main) {
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

                reviewOrderPanel = new ReviewOrderPanel(main);
                main.frame.add(reviewOrderPanel, Screen.REVIEW_ORDER.toString());
                main.showScreen(Screen.REVIEW_ORDER);
            }
        });

        return panel;
    }

    public JPanel createCheckPanel(Main main) {
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
                reviewOrderPanel = new ReviewOrderPanel(main);
                main.frame.add(reviewOrderPanel, Screen.REVIEW_ORDER.toString());
                main.showScreen(Screen.REVIEW_ORDER);
            }
        });

        return panel;
    }

    public JPanel createCashPanel(Main main) {
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

                reviewOrderPanel = new ReviewOrderPanel(main);
                main.frame.add(reviewOrderPanel, Screen.REVIEW_ORDER.toString());
                main.showScreen(Screen.REVIEW_ORDER);
            }
        });

        return panel;
    }
}
