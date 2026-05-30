package cs.vsu;

import cs.vsu.config.AppFactory;
import cs.vsu.config.AppMode;
import cs.vsu.config.RepositoryType;

public class Main {
    public static void main(String[] args) {
        new AppFactory(RepositoryType.IN_MEMORY, AppMode.CONSOLE).run();
    }
}
