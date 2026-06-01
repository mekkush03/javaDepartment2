package cs.vsu.console.command;

import java.util.Scanner;

public class ExitCommand implements Command {

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public void execute(Scanner scanner, String[] args) {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
