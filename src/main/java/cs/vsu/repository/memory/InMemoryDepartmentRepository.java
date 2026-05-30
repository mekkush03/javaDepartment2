package cs.vsu.repository.memory;

import cs.vsu.model.entity.Department;
import cs.vsu.repository.DepartmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryDepartmentRepository implements DepartmentRepository {

    private final Map<Long, Department> store = new ConcurrentHashMap<>();
    private final AtomicLong idSeq = new AtomicLong(1);

    @Override
    public Department save(Department dept) {
        if (dept.getId() == null) {
            dept.setId(idSeq.getAndIncrement());
        }
        store.put(dept.getId(), dept);
        return dept;
    }

    @Override
    public Optional<Department> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Department> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return store.containsKey(id);
    }
}
