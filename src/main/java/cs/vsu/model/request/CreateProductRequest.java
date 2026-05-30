package cs.vsu.model.request;

import java.math.BigDecimal;

public class CreateProductRequest {

    private String name;
    private BigDecimal price;
    private Long departmentId;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String name, BigDecimal price, Long departmentId) {
        this.name = name;
        this.price = price;
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
