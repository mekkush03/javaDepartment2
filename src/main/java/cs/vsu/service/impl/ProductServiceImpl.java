package cs.vsu.service.impl;

import cs.vsu.component.exception.NotFoundException;
import cs.vsu.model.dto.ProductDto;
import cs.vsu.model.entity.Product;
import cs.vsu.model.request.CreateProductRequest;
import cs.vsu.model.request.UpdateProductRequest;
import cs.vsu.repository.DepartmentRepository;
import cs.vsu.repository.ProductRepository;
import cs.vsu.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final DepartmentRepository departmentRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                               DepartmentRepository departmentRepository) {
        this.productRepository = productRepository;
        this.departmentRepository = departmentRepository;
    }

    private static ProductDto toDto(Product p) {
        return new ProductDto(p.getId(), p.getName(), p.getPrice(), p.getDepartmentId());
    }

    private static Product toEntity(CreateProductRequest req) {
        return new Product(null, req.getName(), req.getPrice(), req.getDepartmentId());
    }

    @Override
    public ProductDto create(CreateProductRequest request) {
        if (request.getDepartmentId() != null && !departmentRepository.existsById(request.getDepartmentId())) {
            throw new NotFoundException("Department not found with id: " + request.getDepartmentId());
        }
        Product entity = toEntity(request);
        Product saved = productRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public ProductDto update(Long id, UpdateProductRequest request) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        if (request.getName() != null) {
            existing.setName(request.getName());
        }
        if (request.getPrice() != null) {
            existing.setPrice(request.getPrice());
        }
        if (request.getDepartmentId() != null) {
            if (!departmentRepository.existsById(request.getDepartmentId())) {
                throw new NotFoundException("Department not found with id: " + request.getDepartmentId());
            }
            existing.setDepartmentId(request.getDepartmentId());
        }
        Product saved = productRepository.save(existing);
        return toDto(saved);
    }

    @Override
    public void delete(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        return toDto(product);
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductServiceImpl::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByDepartment(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new NotFoundException("Department not found with id: " + departmentId);
        }
        return productRepository.findByDepartmentId(departmentId).stream()
                .map(ProductServiceImpl::toDto)
                .collect(Collectors.toList());
    }
}
