package com.example.view;
import com.example.controller.Mediator;
import org.apache.log4j.Logger;

public class UserInterface extends ControlledUI{

    static final Logger logJava = Logger.getLogger(UserInterface.class);


    public UserInterface(Mediator mediator) {
        super(mediator);
    }

    public void start() {
        System.out.println("  --- Welcome ---");
        System.out.println("Please choose an option.\n");
        mainMenu();
    }

    public void mainMenu(){
        logJava.info("Option from Main Menu");
        while (true) {
            System.out.println("1- Add new product");
            System.out.println("2- Show all products");
            System.out.println("3- Select product");
            System.out.println("4- Generate report");
            System.out.println("5- E-Mail report");
            System.out.println("0- Exit");
            int option = CheckValid.validInt();
            if(option >= 0 && option <= 5) {
                mediator.notify("showMenu", String.valueOf(option));
                break;
            }
            System.out.println("Please choose a valid option");
            logJava.warn("Invalid option from Main Menu");
        }

    }

    public void selectProduct() {
        while (true) {
            System.out.println("1- Change product stock");
            System.out.println("2- Update product data");
            System.out.println("3- Remove product from inventory");
            System.out.println("0- return to main menu");
            int option = CheckValid.validInt();
            if(option >= 0 && option <= 3) {
                mediator.notify("selectProduct", String.valueOf(option));
                logJava.info("User select a valid product option");
                break;
            }
            System.out.println("Please choose a valid option");
            logJava.info("User didn't select a valid Product option");
        }
    }

    public String generateReport() {
        System.out.println("Write file name: ");
        logJava.info("Generate report");
        return CheckValid.validString() + ".pdf";

    }

    public String generateEmail() {
        logJava.info("Generate email");
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Username: ");
        logJava.info("Entering Username Email");
        stringBuilder.append(CheckValid.validString());
        System.out.println("Password: ");
        logJava.info("Entering  Password");
        stringBuilder.append(CheckValid.validString());
        stringBuilder.append(" ");
        return stringBuilder.toString();

    }

    public boolean confirm() {
        logJava.info("Confirm email");
        System.out.println("Are you sure? y/n");
        return CheckValid.validBoolean();
    }

    public int enterQuantity() {
        logJava.info("Enter quantity of the product");
        System.out.println("Enter quantity: ");
        return CheckValid.validInt();
    }

    public int validateIdUI() {
        logJava.info("Enter product ID");
        System.out.println("Enter product id \n0 to cancel");
        int productID = CheckValid.validInt();
        return productID;
    }

    public void validateIdUINeg(int id) {
        System.out.println(id + " is not a valid id");
        logJava.warn("ID is not valid");
    }

    public void validateIdRepeated(int id) {
        System.out.println(id + " is already being used");
        logJava.warn("ID is duplicate");
    }

    public void successful() {
        System.out.println("Operation successful");
        logJava.info("Operation Successful");
    }

    public void displayProduct(String string) {
        logJava.info("Products are display");
        System.out.println(string);

    }
}
