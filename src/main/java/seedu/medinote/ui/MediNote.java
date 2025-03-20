package seedu.medinote.ui;

import java.util.Scanner;

import seedu.medinote.manager.TaskManager;

public class MediNote {

    public static void main(String[] args) {
        final Scanner userLineScanner = new Scanner(System.in);
        String userInput = "";
        assert false : "test assertion failed";

        while (!userInput.equals("exit")) {
            userInput = userLineScanner.nextLine().trim();
            TaskManager.checkCommand(userInput);
        }
    }
}
