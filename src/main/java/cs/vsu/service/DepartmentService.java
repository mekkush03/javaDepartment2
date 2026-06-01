package cs.vsu.service;

import cs.vsu.model.dto.DepartmentDto;
import cs.vsu.model.request.CreateDepartmentRequest;
import cs.vsu.model.request.UpdateDepartmentRequest;

import java.util.List;

public interface DepartmentService {

    DepartmentDto create(CreateDepartmentRequest request);

    DepartmentDto update(Long id, UpdateDepartmentRequest request);

    void delete(Long id);

    DepartmentDto findById(Long id);

    List<DepartmentDto> findAll();

    List<DepartmentDto> findWithoutProducts();
}
