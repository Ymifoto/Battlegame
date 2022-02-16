package hu.progmasters.handlers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner = new Scanner(System.in);

    public int getInputNumber() {
        int option = -1;
        do {
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        } while (option < 0);
        return option;
    }
}
