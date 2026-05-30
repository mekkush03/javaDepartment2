package cs.vsu.repository;

import cs.vsu.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findByDepartmentId(Long departmentId);

    void deleteById(Long id);
}
