package cs.vsu.console.command.department;

import cs.vsu.console.command.Command;
import cs.vsu.model.dto.DepartmentDto;
import cs.vsu.service.DepartmentService;

import java.util.List;
import java.util.Scanner;

public class ListDepartmentsCommand implements Command {

    private final DepartmentService departmentService;

    public ListDepartmentsCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public String name() {
        return "dept list";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        List<DepartmentDto> departments = departmentService.findAll();
        if (departments.isEmpty()) {
            System.out.println("No departments found.");
        } else {
            System.out.println("Departments:");
            for (DepartmentDto dept : departments) {
                System.out.println("  id=" + dept.getId()
                        + ", name=" + dept.getName()
                        + ", hours=" + dept.getWorkingHours());
            }
        }
    }
}
