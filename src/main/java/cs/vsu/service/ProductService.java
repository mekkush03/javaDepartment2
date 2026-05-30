package cs.vsu.service;

import cs.vsu.model.dto.ProductDto;
import cs.vsu.model.request.CreateProductRequest;
import cs.vsu.model.request.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    ProductDto create(CreateProductRequest request);

    ProductDto update(Long id, UpdateProductRequest request);

    void delete(Long id);

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    List<ProductDto> findByDepartment(Long departmentId);
}
