package cs.vsu.config;

import cs.vsu.console.ConsoleRunner;
import cs.vsu.repository.DepartmentRepository;
import cs.vsu.repository.ProductRepository;
import cs.vsu.repository.jdbc.JdbcDepartmentRepository;
import cs.vsu.repository.jdbc.JdbcProductRepository;
import cs.vsu.repository.memory.InMemoryDepartmentRepository;
import cs.vsu.repository.memory.InMemoryProductRepository;
import cs.vsu.service.DepartmentService;
import cs.vsu.service.ProductService;
import cs.vsu.service.impl.DepartmentServiceImpl;
import cs.vsu.service.impl.ProductServiceImpl;
import cs.vsu.util.DbConfig;
import cs.vsu.util.WebServer;

public class AppFactory {

    private final RepositoryType repositoryType;
    private final AppMode appMode;
    private final DbConfig dbConfig;

    public AppFactory(RepositoryType repositoryType, AppMode appMode) {
        this(repositoryType, appMode, null);
    }

    public AppFactory(RepositoryType repositoryType, AppMode appMode, DbConfig dbConfig) {
        this.repositoryType = repositoryType;
        this.appMode = appMode;
        this.dbConfig = dbConfig;
    }

    public void run() {
        DepartmentRepository deptRepo = createDepartmentRepository();
        ProductRepository prodRepo = createProductRepository();
        DepartmentService deptService = new DepartmentServiceImpl(deptRepo, prodRepo);
        ProductService prodService = new ProductServiceImpl(prodRepo, deptRepo);
        createRunner(deptService, prodService).run();
    }

    private DepartmentRepository createDepartmentRepository() {
        return switch (repositoryType) {
            case IN_MEMORY -> new InMemoryDepartmentRepository();
            case JDBC -> new JdbcDepartmentRepository(requireDbConfig());
        };
    }

    private ProductRepository createProductRepository() {
        return switch (repositoryType) {
            case IN_MEMORY -> new InMemoryProductRepository();
            case JDBC -> new JdbcProductRepository(requireDbConfig());
        };
    }

    private Runnable createRunner(DepartmentService deptService, ProductService prodService) {
        return switch (appMode) {
            case CONSOLE -> new ConsoleRunner(deptService, prodService);
            case API -> () -> {
                try {
                    WebServer.start(deptService, prodService, 8080);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };
        };
    }

    private DbConfig requireDbConfig() {
        if (dbConfig == null) {
            throw new IllegalStateException("DbConfig must be provided for JDBC repository type");
        }
        return dbConfig;
    }
}
