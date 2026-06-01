package cs.vsu.console.command.product;

import cs.vsu.console.command.Command;
import cs.vsu.model.dto.ProductDto;
import cs.vsu.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class ListProductsCommand implements Command {

    private final ProductService productService;

    public ListProductsCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String name() {
        return "prod list";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        List<ProductDto> products = productService.findAll();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Products:");
            for (ProductDto prod : products) {
                System.out.println("  id=" + prod.getId()
                        + ", name=" + prod.getName()
                        + ", price=" + prod.getPrice()
                        + ", departmentId=" + prod.getDepartmentId());
            }
        }
    }
}
