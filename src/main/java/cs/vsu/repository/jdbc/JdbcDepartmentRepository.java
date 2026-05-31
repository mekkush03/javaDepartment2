package cs.vsu.repository.jdbc;

import cs.vsu.model.entity.Department;
import cs.vsu.repository.DepartmentRepository;
import cs.vsu.util.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcDepartmentRepository implements DepartmentRepository {

    private final DbConfig dbConfig;

    public JdbcDepartmentRepository(DbConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    @Override
    public Department save(Department dept) {
        if (dept.getId() == null) {
            String sql = "INSERT INTO department (name, working_hours) VALUES (?, ?)";
            try (Connection conn = dbConfig.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, dept.getName());
                ps.setString(2, dept.getWorkingHours());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        dept.setId(keys.getLong(1));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            String sql = "UPDATE department SET name = ?, working_hours = ? WHERE id = ?";
            try (Connection conn = dbConfig.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, dept.getName());
                ps.setString(2, dept.getWorkingHours());
                ps.setLong(3, dept.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return dept;
    }

    @Override
    public Optional<Department> findById(Long id) {
        String sql = "SELECT id, name, working_hours FROM department WHERE id = ?";
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Department> findAll() {
        String sql = "SELECT id, name, working_hours FROM department";
        List<Department> result = new ArrayList<>();
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM department WHERE id = ?";
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT 1 FROM department WHERE id = ?";
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Department mapRow(ResultSet rs) throws SQLException {
        Department d = new Department();
        d.setId(rs.getLong("id"));
        d.setName(rs.getString("name"));
        d.setWorkingHours(rs.getString("working_hours"));
        return d;
    }
}
