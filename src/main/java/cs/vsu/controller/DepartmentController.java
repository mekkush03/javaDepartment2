package cs.vsu.controller;

import cs.vsu.model.dto.DepartmentDto;
import cs.vsu.model.dto.ProductDto;
import cs.vsu.model.request.CreateDepartmentRequest;
import cs.vsu.model.request.UpdateDepartmentRequest;
import cs.vsu.service.DepartmentService;
import cs.vsu.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final ProductService productService;

    public DepartmentController(DepartmentService departmentService, ProductService productService) {
        this.departmentService = departmentService;
        this.productService = productService;
    }

    @GetMapping
    public List<DepartmentDto> findAll() {
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public DepartmentDto findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDto create(@RequestBody CreateDepartmentRequest request) {
        return departmentService.create(request);
    }

    @PutMapping("/{id}")
    public DepartmentDto update(@PathVariable Long id, @RequestBody UpdateDepartmentRequest request) {
        return departmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        departmentService.delete(id);
    }

    @GetMapping("/empty")
    public List<DepartmentDto> findWithoutProducts() {
        return departmentService.findWithoutProducts();
    }

    @GetMapping("/{id}/products")
    public List<ProductDto> getProducts(@PathVariable Long id) {
        return productService.findByDepartment(id);
    }
}
