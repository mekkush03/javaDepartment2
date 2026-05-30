package cs.vsu.console.command.product;

import cs.vsu.console.command.Command;
import cs.vsu.service.ProductService;

import java.util.Scanner;

public class DeleteProductCommand implements Command {

    private final ProductService productService;

    public DeleteProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String name() {
        return "prod delete";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: prod delete <id>");
            return;
        }
        try {
            Long id = Long.parseLong(args[2]);
            productService.delete(id);
            System.out.println("Product deleted: id=" + id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id: " + args[2]);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
