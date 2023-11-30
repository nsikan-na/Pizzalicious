package org.example.panels;

import org.example.Main;
import org.example.util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPanel extends JPanel {
    public MenuPanel(Main navigation) {
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

        JLabel cartLabel = new JLabel("Cart");
        cartLabel.setHorizontalAlignment(JLabel.RIGHT);
        topPanel.add(cartLabel, BorderLayout.EAST);
        cartLabel.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));

        cartLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.CART);
            }
        });

        JLabel titleLabel = new JLabel("Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(createPizzaPanel(navigation), BorderLayout.LINE_START);
        add(createBeveragesPanel(navigation), BorderLayout.LINE_END);
    }
    private JPanel createPizzaPanel(Main navigation) {
        JPanel pizzaPanel = new JPanel(new GridBagLayout());

        ImageIcon originalPizzaIcon = new ImageIcon(CustomizePizzaPanel.class.getResource("/images/pizzaM3.jpg"));

        int newWidth = 350;
        int newHeight = 350;

        Image scaledPizzaImage = originalPizzaIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledPizzaIcon = new ImageIcon(scaledPizzaImage);

        JLabel pizzaImageLabel = new JLabel(scaledPizzaIcon);

        JLabel pizzaTextLabel = new JLabel("Pizza");
        pizzaTextLabel.setFont(new Font("Arial", Font.BOLD, 24));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 500, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        pizzaPanel.add(pizzaImageLabel, gbc);

        gbc.gridy = 1;
        pizzaPanel.add(pizzaTextLabel, gbc);
        pizzaPanel.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));
        pizzaPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.CUSTOMIZE_PIZZA);
            }
        });
        return pizzaPanel;
    }
    private JPanel createBeveragesPanel(Main navigation) {
        JPanel beveragePanel = new JPanel(new GridBagLayout());

        ImageIcon originalBeveragePanelIcon = new ImageIcon(CustomizePizzaPanel.class.getResource("/images/drink.jpg"));

        int newWidth = 350;
        int newHeight = 350;

        Image scaledBeverageImage = originalBeveragePanelIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledBeverageIcon = new ImageIcon(scaledBeverageImage);

        JLabel beverageImageLabel = new JLabel(scaledBeverageIcon);

        JLabel beverageTextLabel = new JLabel("Beverages");
        beverageTextLabel.setFont(new Font("Arial", Font.BOLD, 24));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 500);

        gbc.anchor = GridBagConstraints.CENTER;

        beveragePanel.add(beverageImageLabel, gbc);

        gbc.gridy = 1;
        beveragePanel.add(beverageTextLabel, gbc);
        beveragePanel.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));
        beveragePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.CUSTOMIZE_BEVERAGE);
            }
        });
        return beveragePanel;
    }
}