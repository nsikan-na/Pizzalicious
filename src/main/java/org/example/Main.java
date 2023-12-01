package org.example;

import org.example.panels.*;
import org.example.util.CartItem;
import org.example.util.CurrentUser;
import org.example.util.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Main {
    public JFrame frame;
    public ArrayList<CartItem> cart = new ArrayList<>();

    public String deliveryMethod=null;

    public String paymentMethod=null;

    public CurrentUser currentUser;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {


            Main main = new Main();
//            main.showScreen(Screen.LOGIN);
            main.showScreen(Screen.LOGIN); // remove after dev
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

        CustomizePizzaPanel customizePizzaPanel = new CustomizePizzaPanel(this);
        frame.add(customizePizzaPanel, Screen.CUSTOMIZE_PIZZA.toString());

        CustomizeBeveragePanel customizeBeveragePanel = new CustomizeBeveragePanel(this);
        frame.add(customizeBeveragePanel, Screen.CUSTOMIZE_BEVERAGE.toString());

        PaymentDeliveryPanel paymentDeliveryPanel = new PaymentDeliveryPanel(this);
        frame.add(paymentDeliveryPanel, Screen.PAYMENT_DELIVERY.toString());

        frame.setVisible(true);
    }

    public void showScreen(Screen screen) {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), screen.toString());
    }

    public void addToCart(CartItem cartItem) {
        cart.add(cartItem);
    }

    public void printCart() {
        System.out.println(cart.size());
    }

    public int getCartSize(){
        return cart.size();
    }

    public ArrayList<CartItem> getCart(){
        return cart;
    }
}
