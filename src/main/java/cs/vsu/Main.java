package cs.vsu;

import cs.vsu.config.AppFactory;
import cs.vsu.config.AppMode;
import cs.vsu.config.RepositoryType;
import cs.vsu.util.DbConfig;

public class Main {
    public static void main(String[] args) {
        DbConfig dbConfig = new DbConfig(
                "jdbc:postgresql://localhost:5432/kiosk",
                "postgres",
                "postgres"
        );
        new AppFactory(RepositoryType.JDBC, AppMode.CONSOLE, dbConfig).run();
    }
}
