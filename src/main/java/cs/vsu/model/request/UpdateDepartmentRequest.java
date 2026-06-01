package cs.vsu.model.request;

public class UpdateDepartmentRequest {

    private String name;
    private String workingHours;

    public UpdateDepartmentRequest() {
    }

    public UpdateDepartmentRequest(String name, String workingHours) {
        this.name = name;
        this.workingHours = workingHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}
