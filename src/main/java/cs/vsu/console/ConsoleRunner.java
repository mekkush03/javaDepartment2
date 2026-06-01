package cs.vsu.console;

import cs.vsu.console.command.Command;
import cs.vsu.console.command.ExitCommand;
import cs.vsu.console.command.HelpCommand;
import cs.vsu.console.command.department.AddDepartmentCommand;
import cs.vsu.console.command.department.DeleteDepartmentCommand;
import cs.vsu.console.command.department.EditDepartmentCommand;
import cs.vsu.console.command.department.ListDepartmentsCommand;
import cs.vsu.console.command.department.ListEmptyDepartmentsCommand;
import cs.vsu.console.command.product.AddProductCommand;
import cs.vsu.console.command.product.DeleteProductCommand;
import cs.vsu.console.command.product.EditProductCommand;
import cs.vsu.console.command.product.ListProductsByDepartmentCommand;
import cs.vsu.console.command.product.ListProductsCommand;
import cs.vsu.service.DepartmentService;
import cs.vsu.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleRunner implements Runnable {

    private final Map<String, Command> commands;

    public ConsoleRunner(DepartmentService deptService, ProductService prodService) {
        List<Command> commandList = List.of(
                new HelpCommand(),
                new ExitCommand(),
                new AddDepartmentCommand(deptService),
                new DeleteDepartmentCommand(deptService),
                new EditDepartmentCommand(deptService),
                new ListDepartmentsCommand(deptService),
                new ListEmptyDepartmentsCommand(deptService),
                new AddProductCommand(prodService),
                new DeleteProductCommand(prodService),
                new EditProductCommand(prodService),
                new ListProductsByDepartmentCommand(prodService),
                new ListProductsCommand(prodService)
        );
        this.commands = new HashMap<>();
        for (Command cmd : commandList) {
            commands.put(cmd.name(), cmd);
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kiosk Management System. Type 'help' for available commands.");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] tokens = line.split("\\s+");
            String commandKey = resolveCommandKey(tokens);
            if (commandKey == null) {
                System.out.println("Unknown command: '" + line + "'. Type 'help' for available commands.");
                continue;
            }
            Command command = commands.get(commandKey);
            try {
                command.execute(scanner, tokens);
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }
    }

    private String resolveCommandKey(String[] tokens) {
        if (tokens.length == 0) {
            return null;
        }
        // Try two-token key first (e.g. "dept add", "prod list-dept")
        if (tokens.length >= 2) {
            String twoToken = tokens[0] + " " + tokens[1];
            if (commands.containsKey(twoToken)) {
                return twoToken;
            }
        }
        // Try single-token key (e.g. "help", "exit")
        if (commands.containsKey(tokens[0])) {
            return tokens[0];
        }
        return null;
    }
}
