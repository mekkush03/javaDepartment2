package cs.vsu.console.command.product;

import cs.vsu.console.command.Command;
import cs.vsu.model.dto.ProductDto;
import cs.vsu.model.request.UpdateProductRequest;
import cs.vsu.service.ProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class EditProductCommand implements Command {

    private final ProductService productService;

    public EditProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String name() {
        return "prod edit";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: prod edit <id>");
            return;
        }
        try {
            Long id = Long.parseLong(args[2]);
            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter new price (leave blank to keep current): ");
            String priceStr = scanner.nextLine().trim();
            System.out.print("Enter new department ID (leave blank to keep current): ");
            String deptIdStr = scanner.nextLine().trim();

            BigDecimal price = priceStr.isEmpty() ? null : new BigDecimal(priceStr);
            Long departmentId = deptIdStr.isEmpty() ? null : Long.parseLong(deptIdStr);

            UpdateProductRequest request = new UpdateProductRequest(
                    name.isEmpty() ? null : name,
                    price,
                    departmentId);
            ProductDto dto = productService.update(id, request);
            System.out.println("Product updated: id=" + dto.getId()
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
