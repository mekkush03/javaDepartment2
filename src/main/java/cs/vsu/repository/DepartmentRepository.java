package cs.vsu.repository;

import cs.vsu.model.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    Department save(Department dept);

    Optional<Department> findById(Long id);

    List<Department> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);
}
