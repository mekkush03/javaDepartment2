package cs.vsu.console.command.department;

import cs.vsu.console.command.Command;
import cs.vsu.model.dto.DepartmentDto;
import cs.vsu.model.request.UpdateDepartmentRequest;
import cs.vsu.service.DepartmentService;

import java.util.Scanner;

public class EditDepartmentCommand implements Command {

    private final DepartmentService departmentService;

    public EditDepartmentCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public String name() {
        return "dept edit";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: dept edit <id>");
            return;
        }
        try {
            Long id = Long.parseLong(args[2]);
            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter new working hours (leave blank to keep current): ");
            String workingHours = scanner.nextLine().trim();

            UpdateDepartmentRequest request = new UpdateDepartmentRequest(
                    name.isEmpty() ? null : name,
                    workingHours.isEmpty() ? null : workingHours);
            DepartmentDto dto = departmentService.update(id, request);
            System.out.println("Department updated: id=" + dto.getId()
                    + ", name=" + dto.getName()
                    + ", hours=" + dto.getWorkingHours());
        } catch (NumberFormatException e) {
            System.out.println("Invalid id: " + args[2]);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
