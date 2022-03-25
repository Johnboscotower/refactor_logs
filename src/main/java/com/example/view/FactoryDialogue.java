package com.example.view;

import org.apache.log4j.Logger;

public class FactoryDialogue {
    static final Logger logJava = Logger.getLogger(FactoryDialogue.class);

    public int getProductType() {
        while (true) {
        System.out.println("Which type of product you'd like to add?");
        System.out.println("1- Home Appliance");
        System.out.println("2- DesignFurniture");
        System.out.println("0- Return");
        int option = CheckValid.validInt();
        if(option >= 0 && option <= 2) {
            return option;
        }
        logJava.warn("Didn't select a valid option/ Home appliance / Design Furniture");
        System.out.println("Please choose a valid option");
        }
    }

    public int changeAttributeDialogue() {
        logJava.info("Change attribute of the product");
        System.out.println("Which attribute you'd like to change?\n0 to exit.");
        return CheckValid.validInt();
    }

    public void noValidOption() {
        System.out.println("Please choose a valid option");
        logJava.warn("Didn't select a valid option");
    }

    public int getProductID() {
        System.out.println("Product id: ");
        logJava.info("Return Product ID successfully");
        return CheckValid.validInt();
    }

    public String getProductName() {
        logJava.info("Return Product name successfully");
        System.out.println("Product name: ");
        return CheckValid.validString();
    }

    public void modifyProductName(int card){
        System.out.println(card + " - Modify name");
    }

    public double getProductPrice() {
        logJava.info("Return Product price successfully");
        System.out.println("Product price: ");
        return CheckValid.validDouble();
    }

    public void modifyProductPrice(int card){
        System.out.println(card + " - Modify price");
    }

    public String getProductColor() {
        logJava.info("Return Product color successfully");
        System.out.println("Product color: ");
        return CheckValid.validString();
    }

    public void modifyProductColor(int card){
        System.out.println(card + " - Modify color");
    }

    public String getProductModel() {
        logJava.info("Return Product model successfully");
        System.out.println("Product model: ");
        return CheckValid.validString();
    }

    public void modifyProductModel(int card){
        System.out.println(card + " - Modify model");
    }

    public String getProductMaterial() {
        logJava.info("Return Product material successfully");
        System.out.println("Product material: ");
        return CheckValid.validString();
    }

    public void modifyProductMaterial(int card){
        System.out.println(card + " - Modify material");
    }

    public String getProductBrand() {
        logJava.info("Return Product brand successfully");
        System.out.println("Product brand: ");
        return CheckValid.validString();
    }

    public void modifyProductBrand(int card){
        System.out.println(card + " - Modify brand");
    }
}
