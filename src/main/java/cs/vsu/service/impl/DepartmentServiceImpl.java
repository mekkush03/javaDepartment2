package cs.vsu.service.impl;

import cs.vsu.component.exception.NotFoundException;
import cs.vsu.model.dto.DepartmentDto;
import cs.vsu.model.entity.Department;
import cs.vsu.model.entity.Product;
import cs.vsu.model.request.CreateDepartmentRequest;
import cs.vsu.model.request.UpdateDepartmentRequest;
import cs.vsu.repository.DepartmentRepository;
import cs.vsu.repository.ProductRepository;
import cs.vsu.service.DepartmentService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ProductRepository productRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                  ProductRepository productRepository) {
        this.departmentRepository = departmentRepository;
        this.productRepository = productRepository;
    }

    private static DepartmentDto toDto(Department dept) {
        return new DepartmentDto(dept.getId(), dept.getName(), dept.getWorkingHours());
    }

    private static Department toEntity(CreateDepartmentRequest req) {
        return new Department(null, req.getName(), req.getWorkingHours());
    }

    @Override
    public DepartmentDto create(CreateDepartmentRequest request) {
        Department entity = toEntity(request);
        Department saved = departmentRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public DepartmentDto update(Long id, UpdateDepartmentRequest request) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + id));
        if (request.getName() != null) {
            existing.setName(request.getName());
        }
        if (request.getWorkingHours() != null) {
            existing.setWorkingHours(request.getWorkingHours());
        }
        Department saved = departmentRepository.save(existing);
        return toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new NotFoundException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentDto findById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + id));
        return toDto(department);
    }

    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream()
                .map(DepartmentServiceImpl::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDto> findWithoutProducts() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<Product> allProducts = productRepository.findAll();
        Set<Long> departmentIdsWithProducts = allProducts.stream()
                .filter(p -> p.getDepartmentId() != null)
                .map(Product::getDepartmentId)
                .collect(Collectors.toSet());
        return allDepartments.stream()
                .filter(d -> !departmentIdsWithProducts.contains(d.getId()))
                .map(DepartmentServiceImpl::toDto)
                .collect(Collectors.toList());
    }
}
