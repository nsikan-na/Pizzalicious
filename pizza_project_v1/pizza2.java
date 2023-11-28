import javax.swing.*;
import java.util.List;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;

 
class PlaceholderTextField extends JTextField {
    private String placeholder;
    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        this.setForeground(Color.GRAY);

        // Set the initial text and color
        setText(placeholder);
        setForeground(Color.GRAY);

        // Set a custom font with a larger size
        Font customFont = getFont().deriveFont(18.5f); // Change to your desired font size
        setFont(customFont);

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.GRAY);
                }
            }
        });
    }
}

public class pizza2 {
    private JFrame frame;
    private JPanel welcomePanel;
    private JPanel menuPanel;
    private JLabel welcomeLabel;
    private JLabel menuLabel;
    private JButton startButton;
    private double totalPrice;
    private double currentPrice;
    private JPanel cartPanel;
    private JPanel cartItemsPanel;
    private List<String> cartItems;
    private JTextField fullNameField;
    private JTextField phoneNumberField;
    private JComboBox<String> paymentMethodComboBox;
    private JTextField cardNumberField;
    private JTextField expirationDateField;
    private JTextField cardholderNameField;
    private JComboBox<String> deliveryMethodComboBox;
    private JTextField addressField;
    private JTextField postalCodeField;
    private JPanel paymentPanel;

    public pizza2() {       
        frame = new JFrame("Pizza Ordering App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 750);
        frame.setLayout(new CardLayout());
        welcomePanel = new JPanel();
        welcomePanel.setLayout(null);
        cartItems = new ArrayList<>();

        PlaceholderTextField usernameField = new PlaceholderTextField("Username");
        usernameField.setBounds(490, 185, 300, 50);
        welcomePanel.add(usernameField);

        PlaceholderTextField passwordField = new PlaceholderTextField("Password");
        passwordField.setBounds(490, 265, 300, 50);
        welcomePanel.add(passwordField);
        
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(550, 350, 180, 40);
        welcomePanel.add(loginButton);

        JButton createButton = new JButton("Create New Account");
        createButton.setBounds(550, 410, 180, 40);
        welcomePanel.add(createButton);

        welcomeLabel = new JLabel(" Welcome to PizzaBee!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Courier", Font.BOLD + Font.ITALIC, 65));
        welcomeLabel.setForeground(Color.RED);
        welcomeLabel.setBounds(70, 40, 1100, 80); 
        welcomePanel.add(welcomeLabel);
        startButton = new JButton("Start  Ordering  As  Guests");
        startButton.setBounds(0, 685, 1275, 30);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMenuPanel();
            }
        });
        welcomePanel.add(startButton);

        frame.add(welcomePanel);
        menuPanel = new JPanel();
        // Add menu items, buttons, and logic here
        menuPanel.setLayout(null);
        menuLabel = new JLabel(" Menu", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Courier", Font.BOLD + Font.ITALIC, 30));
        menuLabel.setForeground(Color.RED);
        menuLabel.setBounds(560, -31, 100, 100);
        menuPanel.add(menuLabel);

        // Create a button to enter payment page
        JButton paymentButton = new JButton("Payment");
        paymentButton.setBounds(1010, 655, 180, 40);
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paymentPage();
            }
        });
        menuPanel.add(paymentButton);

        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setBounds(1010, 585, 180, 40);
        viewCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCartPanel();
            }
        });
        menuPanel.add(viewCartButton);
        
        frame.add(menuPanel, "Menu");
        showWelcomePanel();
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountName = usernameField.getText();
                char[] password = passwordField.getText().toCharArray(); // Use getPassword() for password fields

                // Check login logic here
                if (authenticate(accountName, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed. Please check your credentials.");
                }

                // Clear input fields
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountName = usernameField.getText();
                char[] password = passwordField.getText().toCharArray(); // Use getPassword() for password fields
                // Implement account creation logic here
                //store account information in a database or file
                JOptionPane.showMessageDialog(frame, "Account created!");

                // Clear input fields
                usernameField.setText("");
                passwordField.setText("");
            }
        });   
    }

    public void showWelcomePanel() {       
        // Load and display images of delicious pizzas and snacks
        ImageIcon pizzaImage = new ImageIcon("pizza.jpg"); 
        ImageIcon pizzaImage3 = new ImageIcon("wing.jpg");
        ImageIcon pizzaImage2 = new ImageIcon("pizza2.png");
        ImageIcon pizzaImage4 = new ImageIcon("pizza3.jpg");
        ImageIcon pizzaImage5 = new ImageIcon("pizza4.jpg");
        
        ImageIcon resized = resizeImageIcon(pizzaImage, 420, 365);
        ImageIcon resized2 = resizeImageIcon(pizzaImage2, 420, 365);
        ImageIcon resized3 = resizeImageIcon(pizzaImage3, 420, 365);
        ImageIcon resized4 = resizeImageIcon(pizzaImage4, 120, 120);
        ImageIcon resized5 = resizeImageIcon(pizzaImage5, 120, 130);

        JLabel pizzaJLabel = new JLabel(resized);
        JLabel pizzaJLabel2 = new JLabel(resized2);
        JLabel pizzaJLabel3 = new JLabel(resized3);
        JLabel pizzaJLabel4 = new JLabel(resized4);
        JLabel pizzaJLabel5 = new JLabel(resized5);

        pizzaJLabel.setBounds(0, 160, 420, 365);
        pizzaJLabel2.setBounds(425, 160, 420, 365);
        pizzaJLabel3.setBounds(855, 160, 420, 365);
        pizzaJLabel4.setBounds(1050, 0, 120, 120);
        pizzaJLabel5.setBounds(110, 0, 120, 130);

        welcomePanel.add(pizzaJLabel);
        
        //uncommon the next line to abandon login system
        //welcomePanel.add(pizzaJLabel2);
        welcomePanel.add(pizzaJLabel3);
        welcomePanel.add(pizzaJLabel4);
        welcomePanel.add(pizzaJLabel5);

        // Create a JTextArea and set its position and size
        JTextArea textArea = new JTextArea("Store Address:\n111 Street\nKennesaw, GA, 30144\nPhone Number:470-111-1111");
        textArea.setBounds(1100, 613, 200, 70); // X, Y, Width, Height
        textArea.setEditable(false);
        welcomePanel.add(textArea);

        // Create the cart panel
        cartPanel = new JPanel();
        cartPanel.setLayout(null);
        frame.add(cartPanel, "Cart");
    }
    
    private void paymentPage() {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "Payment");
    
        // Clear the frame's content
        frame.getContentPane().removeAll();
    
        paymentPanel = new JPanel();
        paymentPanel.setLayout(null);  

        JLabel titleLabel = new JLabel("Payment Page");
        titleLabel.setFont(new Font("Courier", Font.BOLD + Font.ITALIC, 40));
        titleLabel.setForeground(Color.RED);
        titleLabel.setBounds(500, 20, 400, 50);
        paymentPanel.add(titleLabel);
    
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(150, 120, 180, 30);
        paymentPanel.add(nameLabel);
        fullNameField = new JTextField();
        fullNameField.setBounds(350, 120, 300, 30);
        paymentPanel.add(fullNameField);
    
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(150, 170, 200, 30);
        paymentPanel.add(phoneLabel);
    
        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(350, 170, 300, 30);
        paymentPanel.add(phoneNumberField);
    
        JLabel paymentMethodLabel = new JLabel("Payment Method:");
        paymentMethodLabel.setBounds(150, 220, 250, 30);
        paymentPanel.add(paymentMethodLabel);
    
        String[] paymentMethods = {"Cash (You may not input card information)", "Credit Card"};
        paymentMethodComboBox = new JComboBox<>(paymentMethods);
        paymentMethodComboBox.setBounds(350, 220, 300, 30);
        paymentPanel.add(paymentMethodComboBox);
    
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberLabel.setBounds(150, 270, 200, 30);
        paymentPanel.add(cardNumberLabel);
    
        cardNumberField = new JTextField();
        cardNumberField.setBounds(350, 270, 300, 30);
        paymentPanel.add(cardNumberField);
    
        JLabel expirationDateLabel = new JLabel("Expiration Date:");
        expirationDateLabel.setBounds(150, 320, 250, 30);
        paymentPanel.add(expirationDateLabel);
    
        expirationDateField = new JTextField();
        expirationDateField.setBounds(350, 320, 300, 30);
        paymentPanel.add(expirationDateField);
    
        JLabel cardholderNameLabel = new JLabel("Cardholder Name:");
        cardholderNameLabel.setBounds(150, 370, 250, 30);
        paymentPanel.add(cardholderNameLabel);
    
        cardholderNameField = new JTextField();
        cardholderNameField.setBounds(350, 370, 300, 30);
        paymentPanel.add(cardholderNameField);
    
        JLabel deliveryMethodLabel = new JLabel("Delivery Method:");
        deliveryMethodLabel.setBounds(150, 420, 250, 30);
        paymentPanel.add(deliveryMethodLabel);
    
        String[] deliveryMethods = {"Pick Up (You may not input address)", "Delivery"};
        deliveryMethodComboBox = new JComboBox<>(deliveryMethods);
        deliveryMethodComboBox.setBounds(350, 420, 300, 30);
        paymentPanel.add(deliveryMethodComboBox);
    
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(150, 470, 180, 30);
        paymentPanel.add(addressLabel);
    
        addressField = new JTextField();
        addressField.setBounds(350, 470, 300, 30);
        paymentPanel.add(addressField);
    
        JLabel postalCodeLabel = new JLabel("Postal Code:");
        postalCodeLabel.setBounds(150, 520, 200, 30);
        paymentPanel.add(postalCodeLabel);
    
        postalCodeField = new JTextField();
        postalCodeField.setBounds(350, 520, 300, 30);
        paymentPanel.add(postalCodeField);
    
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(550, 583, 180, 40);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });       
        paymentPanel.add(submitButton);

        JButton backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setBounds(550, 650, 180, 40);
        backToMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMenuPanel();
                
            }
        });
        paymentPanel.add(backToMenuButton);

        ImageIcon chefImage = new ImageIcon("pizzaC.jpg");
        ImageIcon resized = resizeImageIcon(chefImage, 460, 430);
        JLabel chefLabel = new JLabel(resized);        
        chefLabel.setBounds(730,120,460,430);
        paymentPanel.add(chefLabel);

        ImageIcon waiterImage = new ImageIcon("waiter.png");
        ImageIcon resized2 = resizeImageIcon(waiterImage, 110, 110);
        JLabel waiterLabel = new JLabel(resized2);        
        waiterLabel.setBounds(375,0,110,110);
        paymentPanel.add(waiterLabel);

        ImageIcon cashierImage = new ImageIcon("cashier.png");
        ImageIcon resized3 = resizeImageIcon(cashierImage, 110, 110);
        JLabel cashierLabel = new JLabel(resized3);        
        cashierLabel.setBounds(810,0,110,110);
        paymentPanel.add(cashierLabel);

        frame.add(paymentPanel);
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }
    public void showMenuPanel() {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "Menu");
        // Load and display images of menu items
        ImageIcon pizzaImage = new ImageIcon("pizzaM.jpg");
        ImageIcon pizzaImage2 = new ImageIcon("pizzaM2.jpg");
        ImageIcon pizzaImage3 = new ImageIcon("pizzaM3.jpg");
        ImageIcon pizzaImage4 = new ImageIcon("pizzaM4.jpg");
        ImageIcon pizzaImage5 = new ImageIcon("wingM.jpg");
        ImageIcon pizzaImage6 = new ImageIcon("drink.jpg");
        ImageIcon pizzaImage7 = new ImageIcon("combos.jpg");

        ImageIcon resized = resizeImageIcon(pizzaImage, 210, 210);
        ImageIcon resized2 = resizeImageIcon(pizzaImage2, 210, 210);
        ImageIcon resized3 = resizeImageIcon(pizzaImage3, 210, 210);
        ImageIcon resized4 = resizeImageIcon(pizzaImage4, 210, 210);
        ImageIcon resized5 = resizeImageIcon(pizzaImage5, 210, 210);
        ImageIcon resized6 = resizeImageIcon(pizzaImage6, 210, 210);
        ImageIcon resized7 = resizeImageIcon(pizzaImage7, 280, 210);

        JLabel pizzaLabel = new JLabel(resized);
        JLabel pizzaLabel2 = new JLabel(resized2);
        JLabel pizzaLabel3 = new JLabel(resized3);
        JLabel pizzaLabel4 = new JLabel(resized4);
        JLabel pizzaLabel5 = new JLabel(resized5);
        JLabel pizzaLabel6 = new JLabel(resized6);
        JLabel pizzaLabel7 = new JLabel(resized7);
        
        pizzaLabel.setBounds(50,50,230,230);
        pizzaLabel2.setBounds(360,50,230,230);
        pizzaLabel3.setBounds(670,50,230,230);
        pizzaLabel4.setBounds(980,50,230,230);
        pizzaLabel5.setBounds(50,375,230,230);
        pizzaLabel6.setBounds(360,375,230,230);
        pizzaLabel7.setBounds(670,375,230,230);

        // Create labels with introductions
        JLabel introLabel1 = new JLabel("<html>Pepperoni Pizza<br>&nbsp;&nbsp;&nbsp;from $7.99</html>");
        JLabel introLabel2 = new JLabel("<html>Pineapple Pizza<br>&nbsp;&nbsp;&nbsp;from $8.99</html>");
        JLabel introLabel3 = new JLabel("<html>Vegetarian Pizza<br>&nbsp;&nbsp;&nbsp;from $8.99</html>");
        JLabel introLabel4 = new JLabel("<html>Meat Lover Pizza<br>&nbsp;&nbsp;&nbsp;from $9.99</html>");
        JLabel introLabel5 = new JLabel("<html>Crispy Wings<br>&nbsp;from $8.99</html>");
        JLabel introLabel6 = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Drinks<br>&nbsp;&nbsp;&nbsp;from $2.49</html>");
        JLabel introLabel7 = new JLabel("<html>&nbsp;&nbsp;&nbsp;Combos<br>With discount</html>");

        // Set the text alignment for the introduction labels
        introLabel1.setFont(new Font("Courier", Font.BOLD, 21));
        introLabel1.setForeground(Color.RED);
        introLabel1.setBounds(70,250,220,95);
        introLabel2.setFont(new Font("Courier", Font.BOLD, 21));
        introLabel2.setForeground(Color.RED);
        introLabel2.setBounds(380,250,220,95);
        introLabel3.setFont(new Font("Courier", Font.BOLD, 21));
        introLabel3.setForeground(Color.RED);
        introLabel3.setBounds(690,250,220,95);
        introLabel4.setFont(new Font("Courier", Font.BOLD, 21));
        introLabel4.setForeground(Color.RED);
        introLabel4.setBounds(1000,250,220,95);
        introLabel5.setFont(new Font("Courier", Font.BOLD, 21));
        introLabel5.setForeground(Color.RED);
        introLabel5.setBounds(86,575,220,95);
        introLabel6.setFont(new Font("Courier", Font.BOLD, 21));
        introLabel6.setForeground(Color.RED);
        introLabel6.setBounds(373,575,220,95);
        introLabel7.setFont(new Font("Courier", Font.BOLD, 21));
        introLabel7.setForeground(Color.RED);
        introLabel7.setBounds(706,575,220,95);

        // Create "Add to Cart" buttons
        JButton addToCartButton1 = new JButton("Options");
        JButton addToCartButton2 = new JButton("Options");
        JButton addToCartButton3 = new JButton("Options");
        JButton addToCartButton4 = new JButton("Options");
        JButton addToCartButton5 = new JButton("Options");
        JButton addToCartButton6 = new JButton("Options");
        JButton addToCartButton7 = new JButton("Options");

        // Add action listeners to the "Add to Cart" buttons
        addToCartButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement adding Pepperoni Pizza to cart
                showPizzaOptions("Pepperoni Pizza", 7.99);
            }
        });
        addToCartButton1.setBounds(115, 330, 100, 35);
        addToCartButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement adding Pineapple Pizza to cart
                showPizzaOptions("Pineapple Pizza", 8.99);
            }
        });
        addToCartButton2.setBounds(425, 330, 100, 35);
        addToCartButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement adding Vegetarian Pizza to cart
                showPizzaOptions("Vegetarian Pizza", 8.99);
            }
        });
        addToCartButton3.setBounds(735, 330, 100, 35);
        addToCartButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement adding Meat Lover Pizza to cart
                showPizzaOptions("Meat Lover Pizza", 9.99);
            }
        });
        addToCartButton4.setBounds(1045, 330, 100, 35);
        addToCartButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement adding Crispy Wings to cart
                showWingOptions("Wings", 8.99);
            }
        });
        addToCartButton5.setBounds(115, 655, 100, 35);
        addToCartButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement adding drinks to cart
                showDrinkOptions("Drinks", 2.49);
            }
        });
        addToCartButton6.setBounds(425, 655, 100, 35);
        addToCartButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement adding Crispy Wings to cart
                showComboOptions();
            }
        });
        addToCartButton7.setBounds(735, 655, 100, 35);
        
        // Add components to the menuPanel
        menuPanel.add(pizzaLabel);
        menuPanel.add(introLabel1);
        menuPanel.add(addToCartButton1);
        menuPanel.add(pizzaLabel2);
        menuPanel.add(introLabel2);
        menuPanel.add(addToCartButton2);
        menuPanel.add(pizzaLabel3);
        menuPanel.add(introLabel3);
        menuPanel.add(addToCartButton3);
        menuPanel.add(pizzaLabel4);
        menuPanel.add(introLabel4);
        menuPanel.add(addToCartButton4);
        menuPanel.add(pizzaLabel5);
        menuPanel.add(introLabel5);
        menuPanel.add(addToCartButton5);
        menuPanel.add(pizzaLabel6);
        menuPanel.add(introLabel6);
        menuPanel.add(addToCartButton6);
        menuPanel.add(pizzaLabel7);
        menuPanel.add(introLabel7);
        menuPanel.add(addToCartButton7);
        
        //!!!very important step when user switch page!!!
        if (paymentPanel != null) {
            frame.remove(paymentPanel);
            frame.add(menuPanel);
            //frame.add(cartItemsPanel);
            frame.add(cartPanel);
        }
        
        frame.revalidate();
        frame.repaint();
    }

    private void showCartPanel() {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "Cart");
        cartPanel.removeAll();
        
        ImageIcon cartImage = new ImageIcon("cart.jpg");
        ImageIcon cartImage2 = new ImageIcon("cart2.jpg");

        ImageIcon resized = resizeImageIcon(cartImage, 110, 110);
        ImageIcon resized2 = resizeImageIcon(cartImage2, 110, 110);

        JLabel cartLabel = new JLabel(resized);
        JLabel cartLabel2 = new JLabel(resized2);
        
        cartLabel.setBounds(430,0,110,110);
        cartLabel2.setBounds(780,0,110,110);

        cartPanel.add(cartLabel);
        cartPanel.add(cartLabel2);

        // Add a label for the cart
        cartLabel = new JLabel("Cart", SwingConstants.CENTER);
        cartLabel.setFont(new Font("Courier", Font.BOLD + Font.ITALIC, 30));
        cartLabel.setForeground(Color.RED);
        cartLabel.setBounds(575, 10, 160, 40);
        cartPanel.add(cartLabel);
    
        // Create a button to clear the cart
        JButton clearCartButton = new JButton("Clear Cart");
        clearCartButton.setBounds(170, 640, 180, 40);
        clearCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearCart();
                updateCartItemsPanel();
            }
        });
        cartPanel.add(clearCartButton);
    
        // Create a button to enter the payment page
        JButton paymentButton = new JButton("Payment");
        paymentButton.setBounds(930, 640, 180, 40);
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paymentPage();
            }
        });
        cartPanel.add(paymentButton);
    
        // Create a button to go back to the menu
        JButton backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setBounds(550, 640, 180, 40);
        backToMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMenuPanel();
            }
        });
        cartPanel.add(backToMenuButton);
    
        // Create a scroll pane for the cart items
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(190, 140, 900, 460);
        cartPanel.add(scrollPane);
    
        // Create a panel to hold the cart items and their delete buttons
        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(cartItemsPanel);
    
        // Display cart details in the text area
        if (cartItems.isEmpty()) {
            JLabel emptyCartLabel = new JLabel("Cart is empty.");
            emptyCartLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Adjust the font size and style as needed
            emptyCartLabel.setForeground(Color.RED); // Set the text color to red
            cartItemsPanel.add(emptyCartLabel);
        } else {
            // Cart has items, display each item and a delete button
            for (String item : cartItems) {
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BorderLayout());
        
                // Label for the item
                JLabel itemLabel = new JLabel(item);
                itemLabel.setFont(new Font("Arial", Font.PLAIN, 21)); // Adjust the font size and style for the item label
        
                // Delete button for the item
                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        removeFromCart(item);
                        updateCartItemsPanel();
                    }
                });        
                // Add components to the item panel
                itemPanel.add(itemLabel, BorderLayout.WEST);
                itemPanel.add(deleteButton, BorderLayout.EAST);
        
                // Add the item panel to the cart items panel
                cartItemsPanel.add(itemPanel);
            }        
            // Display total price
            JLabel totalPriceLabel = new JLabel("Total Price: $" + String.format("%.2f", totalPrice));
            totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Adjust the font size and style for the total price label
            totalPriceLabel.setForeground(Color.RED);            
            cartItemsPanel.add(totalPriceLabel);
        }          
        
        //!!!very important step when user switch page!!!
        if (paymentPanel != null) {
            frame.remove(menuPanel);
            frame.add(cartPanel);
            //frame.add(cartItemsPanel);
        }
        
        frame.revalidate(); // Ensure the frame updates correctly
        frame.repaint();
    }    
    private void updateCartItemsPanel() {
        cartItemsPanel.removeAll(); // Remove all components from the cart items panel
    
        if (cartItems.isEmpty()) {
            JLabel emptyCartLabel = new JLabel("Cart is empty.");
            emptyCartLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Adjust the font size and style as needed
            emptyCartLabel.setForeground(Color.RED); // Set the text color to red
            cartItemsPanel.add(emptyCartLabel);
        } else {
            // Add each item and its delete button to the cart items panel
            for (String item : cartItems) {
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BorderLayout());
                JLabel itemLabel = new JLabel(item);
                itemLabel.setFont(new Font("Arial", Font.PLAIN, 21));
                //add the button for delete items in cart
                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        removeFromCart(item);
                        updateCartItemsPanel(); // Refresh the cart items panel after deletion
                    }
                });
    
                itemPanel.add(itemLabel, BorderLayout.WEST);
                itemPanel.add(deleteButton, BorderLayout.EAST);
                cartItemsPanel.add(itemPanel); // Add the item panel to the cart items panel
            }
    
            JLabel totalPriceLabel = new JLabel("Total Price: $" + String.format("%.2f", totalPrice));
            totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 24));
            totalPriceLabel.setForeground(Color.RED);
            cartItemsPanel.add(totalPriceLabel); // Add the total price label to the cart items panel
        }
        // Revalidate and repaint the frame after updating the cart items panel
        frame.revalidate();
        frame.repaint();
    }

    private void handleSubmit() {
        String fullName = fullNameField.getText();
        if (fullName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter your full name", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further processing
        }
        String phoneNumber = phoneNumberField.getText();
        if (phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter your phone number", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further processing
        }    
        String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();
        String cardNumber = cardNumberField.getText();
        if (cardNumber.isEmpty() && paymentMethod.equals("Credit Card")) {
            JOptionPane.showMessageDialog(frame, "Please enter your card number", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further processing
        }
        String expirationDate = expirationDateField.getText();
        if (expirationDate.isEmpty() && paymentMethod.equals("Credit Card")) {
            JOptionPane.showMessageDialog(frame, "Please enter card expiration date", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further processing
        }
        String cardholderName = cardholderNameField.getText();
        if (cardholderName.isEmpty() && paymentMethod.equals("Credit Card")) {
            JOptionPane.showMessageDialog(frame, "Please enter card holder name", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further processing
        }
        String deliveryMethod = (String) deliveryMethodComboBox.getSelectedItem();
        String address = addressField.getText();
        if (address.isEmpty() && deliveryMethod.equals("Delivery")) {
            JOptionPane.showMessageDialog(frame, "Please enter your address", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further processing
        }
        String postalCode = postalCodeField.getText();
        if (postalCode.isEmpty() && deliveryMethod.equals("Delivery")) {
            JOptionPane.showMessageDialog(frame, "Please enter your postal code", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further processing
        }

        // Implement logic to handle the user input, e.g., display a confirmation message
        String confirmationMessage = "Thanks for ordering, " + fullName + "!\n";
        confirmationMessage += "Phone Number: " + phoneNumber + "\n";
        if(paymentMethod.equals("Cash (You may not input card information)")){
            confirmationMessage += "Payment Method: Cash" + "\n";
        }
        else{
            confirmationMessage += "Payment Method: " + paymentMethod + "\n";
        }        
        
        if (deliveryMethod.equals("Credit Card")) {
            confirmationMessage += "Card Number: " + cardNumber + "\n";
            confirmationMessage += "Expiration Date: " + expirationDate + "\n";
            confirmationMessage += "Cardholder Name: " + cardholderName + "\n";
        }
        
        if(deliveryMethod.equals("Pick Up (You may not input address)")){
            confirmationMessage += "Delivery Method: Pick Up" + "\n";
        }
        else{
            confirmationMessage += "Delivery Method: " + deliveryMethod + "\n";
        }
        if (deliveryMethod.equals("Delivery")) {
            confirmationMessage += "Address: " + address + "\n";
            confirmationMessage += "Postal Code: " + postalCode + "\n";
            confirmationMessage += "You food would arrive in 45 minutes\n";
        } else if (deliveryMethod.equals("Pick Up (You may not input address)")) {
            confirmationMessage += "Please pick up your food in 20 minutes.\n";
        }
        for (String item : cartItems) {
            confirmationMessage += item + "\n";
        }
        confirmationMessage += "Total Price: $" + String.format("%.2f", totalPrice) + "\n";
        //collect information and make receipt appear
        JOptionPane.showMessageDialog(frame, confirmationMessage, "Payment Confirmation and Recepit", JOptionPane.INFORMATION_MESSAGE);    
    }

    private void removeFromCart(String itemName) {
        // Implement the logic to remove the item from the cart
        double itemPrice = extractPriceFromItemName(itemName);
        totalPrice -= itemPrice;
        cartItems.remove(itemName);
    }

    private void clearCart() {
        // Clear the cart items list and update the total price
        cartItems.clear();
        totalPrice = 0.0;
    
        // Refresh the cart panel to display the updated cart
        showCartPanel();
    }
    private void showPizzaOptions(String itemName, double basePrice) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2)); // 4 rows, 2 columns for options and price
    
        // Create combo boxes for size and crust options
        JComboBox<String> sizeComboBox = new JComboBox<>(new String[]{"Small", "Medium", "Large"});
        JComboBox<String> crustComboBox = new JComboBox<>(new String[]{"Thin", "Thick"});
    
        // Create a checkbox for extra cheese
        JCheckBox extraCheeseCheckBox = new JCheckBox("Extra Cheese");
    
        // Create a label to display the current price
        JLabel priceLabel = new JLabel("Price: $" + basePrice);
    
        ImageIcon originalIcon = new ImageIcon("pizzai.jpg");
    
        // Resize the image to a specific width and height
        int newWidth = 32; 
        int newHeight = 32; 
        Image resizedImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
    
        // Add components to the panel
        panel.add(new JLabel("Select Size:"));
        panel.add(sizeComboBox);
        panel.add(new JLabel("Select Crust:"));
        panel.add(crustComboBox);
        panel.add(new JLabel("Extra Cheese:"));
        panel.add(extraCheeseCheckBox);
        panel.add(new JLabel("Current Price:"));
        panel.add(priceLabel);
    
        // Add a listener to update the price when options change
        ActionListener optionsChangeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double currentPrice = basePrice;
    
                // Calculate price based on options
                String selectedSize = (String) sizeComboBox.getSelectedItem();
                String selectedCrust = (String) crustComboBox.getSelectedItem();
                boolean hasExtraCheese = extraCheeseCheckBox.isSelected();
    
                if (selectedSize.equals("Medium")) {
                    currentPrice += 3.0;
                } else if (selectedSize.equals("Large")) {
                    currentPrice += 6.0;
                }
    
                if (selectedCrust.equals("Thick")) {
                    currentPrice += 2.0;
                }
    
                if (hasExtraCheese) {
                    currentPrice += 1.0;
                }               
                // Update the price label
                String formattedPrice = String.format("Price: $%.2f", currentPrice);
                priceLabel.setText(formattedPrice);
            }
        };    
        sizeComboBox.addActionListener(optionsChangeListener);
        crustComboBox.addActionListener(optionsChangeListener);
        extraCheeseCheckBox.addActionListener(optionsChangeListener);
    
        int result = JOptionPane.showOptionDialog(
            frame,
            panel,
            "Select Pizza Options",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE, // Use PLAIN_MESSAGE to remove the question mark
            resizedIcon, // Set your custom image as the dialog icon
            new String[] { "Add to Cart", "Cancel" },
            "Add to Cart"
        );
    
        if (result == JOptionPane.YES_OPTION) {
            // Calculate the final price based on selected options
            double finalPrice = basePrice;
            String selectedSize = (String) sizeComboBox.getSelectedItem();
            String selectedCrust = (String) crustComboBox.getSelectedItem();
            boolean hasExtraCheese = extraCheeseCheckBox.isSelected();
    
            if (selectedSize.equals("Medium")) {
                finalPrice += 3.0;
            } else if (selectedSize.equals("Large")) {
                finalPrice += 6.0;
            }
    
            if (selectedCrust.equals("Thick")) {
                finalPrice += 2.0;
            }
    
            if (hasExtraCheese) {
                finalPrice += 1.0;
            }    
            // Build the pizza description with selected options and final price
            String pizzaDescription = itemName + " (" + selectedSize + ", " + selectedCrust;
            if (hasExtraCheese) {
                pizzaDescription += ", Extra Cheese";
            }
            pizzaDescription += ")";
            String finalPrice2 = String.format("%.2f", finalPrice);
            addToCart(pizzaDescription + " - $" + finalPrice2);
        }
    }

    private void showWingOptions(String itemName, double basePrice) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2)); // 4 rows, 2 columns for options and price
    
        // Create combo boxes for size and crust options
        JComboBox<String> amountComboBox = new JComboBox<>(new String[]{"8 pieces", "12 pieces", "20 pieces"});
    
        // Create a checkbox for extra cheese
        JCheckBox extraSpiceCheckBox = new JCheckBox("Extra spice");
    
        // Create a label to display the current price
        JLabel priceLabel = new JLabel("Price: $" + basePrice);
    
        ImageIcon originalIcon = new ImageIcon("wingi.jpg");
    
        // Resize the image to a specific width and height
        int newWidth = 32;
        int newHeight = 32; 
        Image resizedImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
    
        // Add components to the panel
        panel.add(new JLabel("Select Amounts:"));
        panel.add(amountComboBox);
        panel.add(new JLabel("Extra Spice:"));
        panel.add(extraSpiceCheckBox);
        panel.add(new JLabel("Current Price:"));
        panel.add(priceLabel);
    
        // Add a listener to update the price when options change
        ActionListener optionsChangeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double currentPrice = basePrice;
    
                // Calculate price based on options
                String selectedAmount = (String) amountComboBox.getSelectedItem();               
                boolean hasExtraSpice = extraSpiceCheckBox.isSelected();
    
                if (selectedAmount.equals("12 pieces")) {
                    currentPrice += 3.0;
                } else if (selectedAmount.equals("20 pieces")) {
                    currentPrice += 9.0;
                }
    
                if (hasExtraSpice) {
                    currentPrice += 1.0;
                }
                
                // Update the price label
                String formattedPrice = String.format("Price: $%.2f", currentPrice);
                priceLabel.setText(formattedPrice);
            }
        };
        amountComboBox.addActionListener(optionsChangeListener);
        extraSpiceCheckBox.addActionListener(optionsChangeListener);
    
        int result = JOptionPane.showOptionDialog(
            frame,
            panel,
            "Select Wings Options",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE, 
            resizedIcon, 
            new String[] { "Add to Cart", "Cancel" },
            "Add to Cart"
        );
    
        if (result == JOptionPane.YES_OPTION) {
            // Calculate the final price based on selected options
            double finalPrice = basePrice;
            String selectedSize = (String) amountComboBox.getSelectedItem();
            boolean hasExtraSpice = extraSpiceCheckBox.isSelected();
    
            if (selectedSize.equals("12 pieces")) {
                finalPrice += 3.0;
            } else if (selectedSize.equals("20 pieces")) {
                finalPrice += 9.0;
            }
            if (hasExtraSpice) {
                finalPrice += 1.0;
            }
            String finalPrice2 = String.format("%.2f", finalPrice);
    
            // Build the pizza description with selected options and final price
            String wingDescription = itemName + " (" + selectedSize;
            if (hasExtraSpice) {
                wingDescription += ", extra Spice";
            }
            wingDescription += ")";
            addToCart(wingDescription + " - $" + finalPrice2);
        }
    }
    private void showDrinkOptions(String itemName, double basePrice) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2)); // 4 columns, 2 columns for options and price
    
        // Create combo boxes for size and crust options
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Coke", "Sprite", "Crush", "Apple juice", "Watermelon juice", "Root beer"});
        JCheckBox biggerBottleCheckBox = new JCheckBox("Bigger bottle");
    
        // Create a label to display the current price
        JLabel priceLabel = new JLabel("Price: $" + basePrice);
    
        ImageIcon originalIcon = new ImageIcon("drinki.png");
    
        // Resize the image to a specific width and height
        int newWidth = 32; 
        int newHeight = 32; 
        Image resizedImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
    
        // Add components to the panel
        panel.add(new JLabel("Select Drink:"));
        panel.add(typeBox);
        panel.add(new JLabel("Bigger bottle:"));
        panel.add(biggerBottleCheckBox);
        panel.add(new JLabel("Current Price:"));
        panel.add(priceLabel);
    
        // Add a listener to update the price when options change
        ActionListener optionsChangeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double currentPrice = basePrice;
    
                // Calculate price based on options
                String selectedAmount = (String) typeBox.getSelectedItem();               
                boolean biggerBottle = biggerBottleCheckBox.isSelected();
    
                if (selectedAmount.equals("Apple juice")) {
                    currentPrice += 3.0;
                } else if (selectedAmount.equals("Watermelon juice")) {
                    currentPrice += 3.0;
                } else if (selectedAmount.equals("Root beer")) {
                    currentPrice += 1.0;
                } 
    
                if (biggerBottle) {
                    currentPrice += 2.0;
                }
                
                // Update the price label
                String formattedPrice = String.format("Price: $%.2f", currentPrice);
                priceLabel.setText(formattedPrice);
            }
        };
    
        typeBox.addActionListener(optionsChangeListener);
        biggerBottleCheckBox.addActionListener(optionsChangeListener);
    
        int result = JOptionPane.showOptionDialog(
            frame,
            panel,
            "Select Drinks Options",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE, // Use PLAIN_MESSAGE to remove the question mark
            resizedIcon, // Set your custom image as the dialog icon
            new String[] { "Add to Cart", "Cancel" },
            "Add to Cart"
        );
    
        if (result == JOptionPane.YES_OPTION) {
            // Calculate the final price based on selected options
            double finalPrice = basePrice;
            String selectedAmount = (String) typeBox.getSelectedItem();
            boolean biggerBottle = biggerBottleCheckBox.isSelected();
    
            if (selectedAmount.equals("Apple juice")) {
                    finalPrice += 3.0;
                } else if (selectedAmount.equals("Watermelon juice")) {
                    finalPrice += 3.0;
                } else if (selectedAmount.equals("Root beer")) {
                    finalPrice += 1.0;
                } 
    
            if (biggerBottle) {
                finalPrice += 2.0;
            }
           
            // Build the pizza description with selected options and final price
            String drinkDescription = itemName + " (" + selectedAmount;
            if (biggerBottle) {
                drinkDescription += ", bigger bottle";
            }
            drinkDescription += ")";
            addToCart(drinkDescription + " - $" + finalPrice);
        }
    }

    private void showComboOptions() {
        currentPrice = 32.97;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2)); // Single column, 2 columns for options and price
    
        // Create combo boxes for pizza options
        JComboBox<String> pizzaComboBox1 = new JComboBox<>(new String[]{"Pepperoni Pizza", "Pineapple Pizza", "Vegetarian Pizza", "Meat Lover Pizza"});
        JComboBox<String> pizzaComboBox2 = new JComboBox<>(new String[]{"Pepperoni Pizza", "Pineapple Pizza", "Vegetarian Pizza", "Meat Lover Pizza"});
        JComboBox<String> pizzaSize = new JComboBox<>(new String[]{"Small", "Medium", "Large"});
        JComboBox<String> pizzaCrust = new JComboBox<>(new String[]{"Thin", "Thick"});
        JCheckBox extraCheeseCheckBox = new JCheckBox("");

        // Create combo boxes for drink options
        JComboBox<String> drinkComboBox1 = new JComboBox<>(new String[]{"Coke", "Sprite", "Crush", "Apple juice", "Watermelon juice", "Root beer"});
        JComboBox<String> drinkComboBox2 = new JComboBox<>(new String[]{"Coke", "Sprite", "Crush", "Apple juice", "Watermelon juice", "Root beer"});
    
        // Create combo boxes for wing options
        JComboBox<String> wingComboBox = new JComboBox<>(new String[]{"8 pieces", "12 pieces", "20 pieces"});
    
        // Create a label to display the current price
        JLabel priceLabel = new JLabel("Price: $32.97"); // Assuming the base price without discount
        
        // Add components to the panel
        panel.add(new JLabel("Select Pizza 1:"));
        panel.add(pizzaComboBox1);
        panel.add(new JLabel("Select Pizza 2:"));
        panel.add(pizzaComboBox2);
        panel.add(new JLabel("Pizza Size:"));
        panel.add(pizzaSize);
        panel.add(new JLabel("Pizza Crust:"));
        panel.add(pizzaCrust);
        panel.add(new JLabel("Extra cheese on pizza"));
        panel.add(extraCheeseCheckBox);
        panel.add(new JLabel("Select Drink 1:"));
        panel.add(drinkComboBox1);
        panel.add(new JLabel("Select Drink 2:"));
        panel.add(drinkComboBox2);
        panel.add(new JLabel("Select Wings:"));
        panel.add(wingComboBox);
        panel.add(new JLabel("Current Price:"));
        panel.add(priceLabel);
        JLabel OLabel = new JLabel("Enjoy $7 off for combo : D");
        OLabel.setForeground(Color.RED);
        panel.add(OLabel);
    
        // Add a listener to update the price when options change
        ActionListener optionsChangeListener = e -> {
            currentPrice = 32.97;
            // Calculate price based on options
            currentPrice += getPriceFromComboBox(pizzaComboBox1.getSelectedItem().toString());
            currentPrice += getPriceFromComboBox(pizzaComboBox2.getSelectedItem().toString());
            currentPrice += getPriceFromComboBox(pizzaSize.getSelectedItem().toString());
            currentPrice += getPriceFromComboBox(pizzaCrust.getSelectedItem().toString());
            boolean hasExtraCheese = extraCheeseCheckBox.isSelected();
            if(hasExtraCheese){
                currentPrice += 2.0;
            }
            currentPrice += getPriceFromComboBox(drinkComboBox1.getSelectedItem().toString());
            currentPrice += getPriceFromComboBox(drinkComboBox2.getSelectedItem().toString());
            currentPrice += getPriceFromComboBox(wingComboBox.getSelectedItem().toString());
    
            // Update the price label
            String formattedPrice = String.format("Price: $%.2f", currentPrice);
            priceLabel.setText(formattedPrice);
        };
        
        pizzaComboBox1.addActionListener(optionsChangeListener);
        pizzaComboBox2.addActionListener(optionsChangeListener);
        pizzaSize.addActionListener(optionsChangeListener);
        pizzaCrust.addActionListener(optionsChangeListener);
        extraCheeseCheckBox.addActionListener(optionsChangeListener);
        drinkComboBox1.addActionListener(optionsChangeListener);
        drinkComboBox2.addActionListener(optionsChangeListener);
        wingComboBox.addActionListener(optionsChangeListener);
    
        int result = JOptionPane.showOptionDialog(
                frame,
                panel,
                "Select Combo Options",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, // Use PLAIN_MESSAGE to remove the question mark
                null, // Set your custom image as the dialog icon
                new String[]{"Add to Cart", "Cancel"},
                "Add to Cart"
        );
        if (result == JOptionPane.YES_OPTION) {
            // Build the combo description with selected options and apply discount
            String comboDescription = "Combo Deal: ";
            comboDescription += pizzaComboBox1.getSelectedItem() + ", " + pizzaComboBox2.getSelectedItem() + "\n ";
            
            comboDescription += drinkComboBox1.getSelectedItem() + ", " + drinkComboBox2.getSelectedItem() + "\n ";
            comboDescription += wingComboBox.getSelectedItem() + " Wings";
            //set 7$ off as discount and convert to the final price
            String finalPrice = String.valueOf(currentPrice - 7);
            addToCart(comboDescription + " - " + finalPrice);
        }
    }
    
    // Helper method to get the price from the selected item in the combo box
    private double getPriceFromComboBox(String selectedItem) {
        //suitable to use switch
        switch (selectedItem) {          
            case "Pineapple Pizza":
                return 1.0;
            case "Vegetarian Pizza":
                return 1.0;
            case "Meat Lover Pizza":
                return 2.0;    
            case "Medium":
                return 6.0;
            case "Large":
                return 12.0;
            case "Thick":
                return 4.0;            
            case "Apple juice":
            case "Watermelon juice":
                return 3.0;
            case "Root beer":
                return 1.0; 
            case "12 pieces":
                return 3.0;
            case "20 pieces":
                return 9.0;
            default:
                return 0.0;
        }
    }    

    private void addToCart(String itemName) {
        // Calculate and add the price of the selected item to the total
        double itemPrice = extractPriceFromItemName(itemName); // Implement this method
        totalPrice += itemPrice;
    
        cartItems.add(itemName);
    }
    private double extractPriceFromItemName(String itemName) {
        String[] parts = itemName.split(" - ");
        if (parts.length >= 2) {
            String priceStr = parts[parts.length - 1].replace("$", "");
            try {
                return Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                return 0.0; // Handle invalid price format gracefully
            }
        }
        return 0.0; // Handle items without a valid price
    }

    private static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    private boolean authenticate(String username, char[] password) {
        // Replace this with actual authentication logic
        // For this example, let's assume the correct username is "user" and the password is "password"
        String correctUsername = "user";
        char[] correctPassword = "password".toCharArray();
    
        // Check if the username matches and use Arrays.equals to compare the passwords
        return username.equals(correctUsername) && Arrays.equals(password, correctPassword);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new pizza2().frame.setVisible(true);
            }
        });        
    }
}


