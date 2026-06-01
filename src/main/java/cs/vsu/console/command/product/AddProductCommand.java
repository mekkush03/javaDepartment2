package cs.vsu.console.command.product;

import cs.vsu.console.command.Command;
import cs.vsu.model.dto.ProductDto;
import cs.vsu.model.request.CreateProductRequest;
import cs.vsu.service.ProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class AddProductCommand implements Command {

    private final ProductService productService;

    public AddProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String name() {
        return "prod add";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter price: ");
        String priceStr = scanner.nextLine().trim();
        System.out.print("Enter department ID: ");
        String deptIdStr = scanner.nextLine().trim();

        try {
            BigDecimal price = new BigDecimal(priceStr);
            Long departmentId = Long.parseLong(deptIdStr);
            CreateProductRequest request = new CreateProductRequest(name, price, departmentId);
            ProductDto dto = productService.create(request);
            System.out.println("Product created: id=" + dto.getId()
                    + ", name=" + dto.getName()
                    + ", price=" + dto.getPrice()
                    + ", departmentId=" + dto.getDepartmentId());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
