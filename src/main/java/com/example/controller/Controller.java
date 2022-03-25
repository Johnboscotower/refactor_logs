package com.example.controller;

import com.example.model.CreateReport;
import com.example.model.SendMail;
import com.example.model.database.InventoryInt;
import com.example.model.factory.FactoryController;
import com.example.model.factory.Product;
import com.example.view.UserInterface;
import com.example.model.database.Inventory;
import com.example.view.CheckValid;
import org.apache.log4j.Logger;

import java.util.Map;


public class Controller implements Mediator{
    Logger logJava = Logger.getLogger(Controller.class);

    UserInterface userInterface;
    InventoryInt inventory;
    FactoryController factoryController;
    int productID;

    public Controller() {
        this.userInterface = new UserInterface(this);
        this.inventory = new Inventory();
        this.factoryController = new FactoryController();
        userInterface.start();
    }

    @Override
    public void notify(String sender, String event) {
        switch (sender) {
            case"showMenu":
                handleShowMenu(event);
                break;
            case"selectProduct":
                handleSelectProduct(event);
                break;
            default:
        }
        userInterface.mainMenu();
    }

    private void handleShowMenu(String event) {
        switch (event) {
            case "1":
                int id = handleValidateID(false);
                if (id == 0) {return;}
                Product product = factoryController.startFactory(id);
                inventory.addProduct(product, userInterface.enterQuantity());
                logJava.info("User select to add a product");
                break;
            case "2":
                userInterface.displayProduct(Formatter.formatInventory((Inventory) inventory));
                logJava.info("Product select to show products");
                break;
            case "3":
                if (handleValidateID(true) == 0) {return;}
                userInterface.displayProduct(Formatter.formatProduct(inventory.getProduct(productID)));
                userInterface.selectProduct();
                logJava.info("User select a product");
                break;
            case "4":
                String filename = userInterface.generateReport();
                String content = Formatter.formatInventory((Inventory) inventory);
                CreateReport.createReport(filename, content);
                logJava.info("User select generate report");
                break;
            case "5":
                logJava.info("User select Email Report");
                String[] data = userInterface.generateEmail().split(" ");
                int sent = SendMail.sendMail(CreateReport.createReport("report.pdf", Formatter.formatInventory((Inventory) inventory)), data[0], data[1]);
                if (sent == -1) {
                    System.out.println("Email couldn't be sent. Please check username and password.");
                    logJava.warn("Email couldn't be sent");
                }
                if (sent == -2) {
                    System.out.println("Email couldn't be sent");
                    logJava.error("Email Error");
                }
                break;
            default:
                exit();
        }
        userInterface.mainMenu();
    }

    private void handleSelectProduct(String event) {
        switch (event) {
            case "1":
                int quantity = userInterface.enterQuantity();
                inventory.setProductStock(productID, quantity);
                userInterface.successful();
                userInterface.selectProduct();
                logJava.info("Product Select");
                break;
            case "2":
                factoryController.modifyProduct(inventory.getProduct(productID));
                logJava.info("Product Modify");
                break;
            case "3":
                if (userInterface.confirm()) {
                    inventory.removeProduct(productID);
                    userInterface.successful();
                    logJava.info("Product remove");
                    return;
                }
                break;
            default:
                userInterface.mainMenu();
        }
        userInterface.selectProduct();
    }

    private int handleValidateID(boolean b) {
        while (true) {
            int id = userInterface.validateIdUI();
            if(id == 0) {
                return 0;
            }
            if (b) {
                if(inventory.validID(id)) {
                    productID = id;
                    return id;
                }
                userInterface.validateIdUINeg(id);
            }
            else {
                if(!inventory.validID(id) && !b) {
                    return id;
                }
                userInterface.validateIdRepeated(id);
            }
        }
    }

    private void exit() {
        CheckValid.closeScanner();
        System.exit(0);
    }
}

class Formatter{
    static StringBuilder stringBuilder = new StringBuilder();

    public static String formatProduct(Product product) {
        stringBuilder.setLength(0);
        stringBuilder.append("ID: \t");
        stringBuilder.append(product.getId());
        stringBuilder.append("\nName: \t");
        stringBuilder.append(product.getName());
        for (int i = 0; i < product.getAttributes().length; i++) {
            stringBuilder.append("\n");
            stringBuilder.append(product.getAttributesHeader()[i]);
            stringBuilder.append(": \t");
            stringBuilder.append(product.getAttributes()[i]);
        }
        stringBuilder.append("\nPrice: \t$");
        stringBuilder.append(product.getPrice());
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public static String formatInventory(Inventory inventory){
        stringBuilder.setLength(0);
        stringBuilder.append("ID ");
        stringBuilder.append("\t\tName");
        stringBuilder.append("\t\t\tPrice ($)");
        stringBuilder.append("\tStock");
        stringBuilder.append("\t\tDetails ");
        stringBuilder.append("\n");
        for (Map.Entry<Product, Integer> entry : inventory.getInventoryList().entrySet()) {
            stringBuilder.append(entry.getKey().getId());
            stringBuilder.append("\t");
            int space = 12 - entry.getKey().getName().length();
            String fill = new String(new char[Math.max(space, 0)]).replace("\0", " ");
            stringBuilder.append(entry.getKey().getName());
            stringBuilder.append(fill);
            stringBuilder.append("\t");
            stringBuilder.append(entry.getKey().getPrice());
            stringBuilder.append("\t\t");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\t\t\t");
            for (int i = 0; i < entry.getKey().getAttributes().length; i++) {
                stringBuilder.append(entry.getKey().getAttributesHeader()[i]);
                stringBuilder.append(": ");
                space = 9 - entry.getKey().getAttributes()[i].length();
                fill = new String(new char[Math.max(space, 0)]).replace("\0", " ");
                stringBuilder.append(entry.getKey().getAttributes()[i]);
                stringBuilder.append(fill);
                stringBuilder.append("\t");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}