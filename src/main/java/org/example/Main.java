package org.example;

import org.example.panels.*;
import org.example.util.Screen;

import javax.swing.*;
import java.awt.*;


public class Main {
    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
//            main.showScreen(Screen.LOGIN);
            main.showScreen(Screen.MENU); // remove after dev
        });
    }

    public Main() {
        frame = new JFrame("Pizza Project");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());

        LoginPanel mainMenuPanel = new LoginPanel(this);
        frame.add(mainMenuPanel, Screen.LOGIN.toString());

        CreateANewUserPanel createANewUserPanel = new CreateANewUserPanel(this);
        frame.add(createANewUserPanel, Screen.CREATE_A_NEW_USER.toString());

        MenuPanel menuPanel = new MenuPanel(this);
        frame.add(menuPanel, Screen.MENU.toString());

        CustomizePizzaPanel customizePizzaPanel = new CustomizePizzaPanel(this);
        frame.add(customizePizzaPanel,Screen.CUSTOMIZE_PIZZA.toString() );

//        CustomizeBeveragePanel customizeBeveragePanel = new CustomizeBeveragePanel(this);
//        frame.add(customizeBeveragePanel, Screen.CUSTOMIZE_BEVERAGE.toString());

        frame.setVisible(true);
    }

    public void showScreen(Screen screen) {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), screen.toString());
    }
}
