package cs.vsu.console.command;

import java.util.Scanner;

public class HelpCommand implements Command {

    @Override
    public String name() {
        return "help";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        System.out.println("Available commands:");
        System.out.println("  help                     - show this help");
        System.out.println("  exit                     - exit the application");
        System.out.println("  dept add                 - add a new department");
        System.out.println("  dept list                - list all departments");
        System.out.println("  dept edit <id>           - edit a department");
        System.out.println("  dept delete <id>         - delete a department");
        System.out.println("  dept empty               - list departments with no products");
        System.out.println("  prod add                 - add a new product");
        System.out.println("  prod list                - list all products");
        System.out.println("  prod list-dept <id>      - list products in a department");
        System.out.println("  prod edit <id>           - edit a product");
        System.out.println("  prod delete <id>         - delete a product");
    }
}
