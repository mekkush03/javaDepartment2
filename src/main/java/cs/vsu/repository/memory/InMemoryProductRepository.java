package cs.vsu.repository.memory;

import cs.vsu.model.entity.Product;
import cs.vsu.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong idSeq = new AtomicLong(1);

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(idSeq.getAndIncrement());
        }
        store.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Product> findByDepartmentId(Long departmentId) {
        return store.values().stream()
                .filter(p -> departmentId.equals(p.getDepartmentId()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
