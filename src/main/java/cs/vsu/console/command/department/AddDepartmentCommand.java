package cs.vsu.console.command.department;

import cs.vsu.console.command.Command;
import cs.vsu.model.dto.DepartmentDto;
import cs.vsu.model.request.CreateDepartmentRequest;
import cs.vsu.service.DepartmentService;

import java.util.Scanner;

public class AddDepartmentCommand implements Command {

    private final DepartmentService departmentService;

    public AddDepartmentCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public String name() {
        return "dept add";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter working hours (e.g. 09:00-18:00): ");
        String workingHours = scanner.nextLine().trim();

        CreateDepartmentRequest request = new CreateDepartmentRequest(name, workingHours);
        DepartmentDto dto = departmentService.create(request);
        System.out.println("Department created: id=" + dto.getId()
                + ", name=" + dto.getName()
                + ", hours=" + dto.getWorkingHours());
    }
}
