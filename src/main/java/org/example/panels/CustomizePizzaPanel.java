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
        add(createPizzaPanel(), BorderLayout.LINE_START);
        add(createBeveragesPanel(), BorderLayout.LINE_END);
    }
    private JPanel createPizzaPanel() {
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

//        pizzaPanel.add(pizzaImageLabel, gbc);

        gbc.gridy = 1;
//        pizzaPanel.add(pizzaTextLabel, gbc);

        return pizzaPanel;
    }
    private JPanel createBeveragesPanel() {
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

        return beveragePanel;
    }
}