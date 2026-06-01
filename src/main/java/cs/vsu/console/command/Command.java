package cs.vsu.console.command;

import java.util.Scanner;

public interface Command {

    String name();

    void execute(Scanner scanner, String[] args);
}
