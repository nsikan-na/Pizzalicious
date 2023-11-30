package org.example.panels;

import org.example.Main;
import org.example.util.Screen;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReceiptPanel extends JPanel {
    private int itemCount = 0;
    private long totalPrice = 0;
    private int rowHeight = 6;
    private int arraySize = 0;
    private boolean isCheck = false;

    public ReceiptPanel(Main navigation) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Receipt");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        add(createMainPanel(navigation), BorderLayout.CENTER);
    }

    private JPanel createMainPanel(Main navigation) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 1;
        gbc.gridy = 0;

        panel.add(new JLabel("Thank You For The Order"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;

        panel.add(new JLabel("Order #: 198"), gbc);

        try {
            InputStream inputStream = getClass().getResourceAsStream("/db/Payment_Delivery_Method.json");
            if (inputStream == null) {
                System.err.println("Unable to find Payment_Delivery_Method.json in the resources");
            }

            try (InputStreamReader reader = new InputStreamReader(inputStream)) {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
                if(jsonObject.get("payment").equals("Check")){
                    isCheck=true;
                }else{
                    isCheck=false;
                }
                gbc.insets = new Insets(5, 5, 5, 5);

                JLabel deliveryLabel = new JLabel("Delivery Method: " + jsonObject.get("delivery"));
                gbc.gridx = 1;
                gbc.gridy = 3;

                panel.add(deliveryLabel, gbc);

                JLabel paymentLabel = new JLabel("Payment Method: " + jsonObject.get("payment"));
                gbc.gridx = 1;
                gbc.gridy = 4;
                panel.add(paymentLabel, gbc);
                gbc.insets = new Insets(25, 5, 25, 5);
                JLabel checkLineLabel = new JLabel("x____________________________________________________________________");
                gbc.gridx = 1;
                gbc.gridy = 5;
                if(jsonObject.get("payment").equals("Check")){
                    panel.add(checkLineLabel, gbc);
                }
                gbc.insets = new Insets(5, 5, 5, 5);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        try {
            InputStream inputStream = getClass().getResourceAsStream("/db/Cart.json");

            if (inputStream == null) {
                System.err.println("Unable to find Cart.json in the resources");
            }

            try (InputStreamReader reader = new InputStreamReader(inputStream)) {
                JSONParser jsonParser = new JSONParser();
                JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
                arraySize = jsonArray.size();

                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    String item = (String) jsonObject.get("item");
                    ArrayList<String> itemDetails = (ArrayList) jsonObject.get("itemDetails");
                    Long pricePerItem = (Long) jsonObject.get("pricePerItem");
                    Long quantity = (Long) jsonObject.get("quantity");

                    System.out.println(jsonObject.toJSONString());


                    ImageIcon originalIcon = new ImageIcon(CustomizePizzaPanel.class.getResource(item.equals("pizza") ? "/images/pizzaM3.jpg" : "/images/drink.jpg"));

                    int newWidth = 150;
                    int newHeight = 150;


                    Image scaledImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);

                    JLabel pizzaImage = new JLabel(scaledIcon);

                    gbc.gridx = 0;
                    gbc.gridy = i * rowHeight + 6;
                    gbc.gridheight = rowHeight;
                    gbc.insets = new Insets(5, 25, 5, 25);

                    panel.add(pizzaImage, gbc);

                    for (int j = 0; j < itemDetails.size(); j++) {
                        JLabel itemDetailslabel = new JLabel(itemDetails.get(j));
                        gbc.gridx = 1;
                        gbc.gridy = itemCount + 6;
                        gbc.gridheight = 1;

                        panel.add(itemDetailslabel, gbc);
                        itemCount++;
                    }

                    JLabel quantitylabel = new JLabel("x" + quantity.toString());
                    gbc.gridx = 3;
                    gbc.gridy = i * rowHeight + 6;
                    gbc.gridheight = rowHeight;
                    gbc.gridwidth = 1;
                    panel.add(quantitylabel, gbc);

                    Long price = quantity * pricePerItem;
                    totalPrice += price;

                    JLabel pricelabel = new JLabel("$" + price.toString());
                    gbc.gridx = 5;
                    gbc.gridy = i * rowHeight + 6;
                    gbc.gridheight = rowHeight;

                    panel.add(pricelabel, gbc);


                }
                JLabel totalPriceLabel = new JLabel("Total: $" + totalPrice);
                gbc.gridx = 5;
                gbc.gridy = arraySize * rowHeight + 7;
                gbc.gridheight = rowHeight;

                panel.add(totalPriceLabel, gbc);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JButton submitButton = new JButton("Start New Order");

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigation.showScreen(Screen.MENU);
            }
        });

        gbc.gridx = 5;
        gbc.gridy = arraySize * rowHeight + 13;
        panel.add(submitButton, gbc);

        return panel;
    }


}
