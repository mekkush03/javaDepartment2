package cs.vsu.console.command.department;

import cs.vsu.console.command.Command;
import cs.vsu.model.dto.DepartmentDto;
import cs.vsu.service.DepartmentService;

import java.util.List;
import java.util.Scanner;

public class ListEmptyDepartmentsCommand implements Command {

    private final DepartmentService departmentService;

    public ListEmptyDepartmentsCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public String name() {
        return "dept empty";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        List<DepartmentDto> departments = departmentService.findWithoutProducts();
        if (departments.isEmpty()) {
            System.out.println("All departments have products.");
        } else {
            System.out.println("Departments without products:");
            for (DepartmentDto dept : departments) {
                System.out.println("  id=" + dept.getId()
                        + ", name=" + dept.getName()
                        + ", hours=" + dept.getWorkingHours());
            }
        }
    }
}
