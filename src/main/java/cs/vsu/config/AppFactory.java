package cs.vsu.config;

import cs.vsu.console.ConsoleRunner;
import cs.vsu.repository.DepartmentRepository;
import cs.vsu.repository.ProductRepository;
import cs.vsu.repository.memory.InMemoryDepartmentRepository;
import cs.vsu.repository.memory.InMemoryProductRepository;
import cs.vsu.service.DepartmentService;
import cs.vsu.service.ProductService;
import cs.vsu.service.impl.DepartmentServiceImpl;
import cs.vsu.service.impl.ProductServiceImpl;

public class AppFactory {

    private final RepositoryType repositoryType;
    private final AppMode appMode;

    public AppFactory(RepositoryType repositoryType, AppMode appMode) {
        this.repositoryType = repositoryType;
        this.appMode = appMode;
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
            case JDBC -> throw new UnsupportedOperationException("JDBC repository not implemented yet");
        };
    }

    private ProductRepository createProductRepository() {
        return switch (repositoryType) {
            case IN_MEMORY -> new InMemoryProductRepository();
            case JDBC -> throw new UnsupportedOperationException("JDBC repository not implemented yet");
        };
    }

    private Runnable createRunner(DepartmentService deptService, ProductService prodService) {
        return switch (appMode) {
            case CONSOLE -> new ConsoleRunner(deptService, prodService);
            case API -> throw new UnsupportedOperationException("API mode not implemented yet");
        };
    }
}
