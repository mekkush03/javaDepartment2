package cs.vsu.model.entity;

public class Department {

    private Long id;
    private String name;
    private String workingHours;

    public Department() {
    }

    public Department(Long id, String name, String workingHours) {
        this.id = id;
        this.name = name;
        this.workingHours = workingHours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "', workingHours='" + workingHours + "'}";
    }
}
