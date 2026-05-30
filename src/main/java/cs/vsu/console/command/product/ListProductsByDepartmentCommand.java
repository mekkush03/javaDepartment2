package cs.vsu.console.command.product;

import cs.vsu.console.command.Command;
import cs.vsu.model.dto.ProductDto;
import cs.vsu.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class ListProductsByDepartmentCommand implements Command {

    private final ProductService productService;

    public ListProductsByDepartmentCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String name() {
        return "prod list-dept";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: prod list-dept <id>");
            return;
        }
        try {
            Long departmentId = Long.parseLong(args[2]);
            List<ProductDto> products = productService.findByDepartment(departmentId);
            if (products.isEmpty()) {
                System.out.println("No products in department id=" + departmentId);
            } else {
                System.out.println("Products in department id=" + departmentId + ":");
                for (ProductDto prod : products) {
                    System.out.println("  id=" + prod.getId()
                            + ", name=" + prod.getName()
                            + ", price=" + prod.getPrice());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid id: " + args[2]);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
