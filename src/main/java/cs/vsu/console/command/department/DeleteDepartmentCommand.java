package cs.vsu.console.command.department;

import cs.vsu.console.command.Command;
import cs.vsu.service.DepartmentService;

import java.util.Scanner;

public class DeleteDepartmentCommand implements Command {

    private final DepartmentService departmentService;

    public DeleteDepartmentCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public String name() {
        return "dept delete";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: dept delete <id>");
            return;
        }
        try {
            Long id = Long.parseLong(args[2]);
            departmentService.delete(id);
            System.out.println("Department deleted: id=" + id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id: " + args[2]);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
