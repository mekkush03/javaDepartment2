package cs.vsu.model.dto;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private Long departmentId;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, BigDecimal price, Long departmentId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.departmentId = departmentId;
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

    @Override
    public String toString() {
        return "ProductDto{id=" + id + ", name='" + name + "', price=" + price + ", departmentId=" + departmentId + "}";
    }
}
