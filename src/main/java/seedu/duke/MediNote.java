package seedu.duke;

import java.util.Scanner;

public class MediNote {



    public static void main(String[] args) {
        final Scanner userLineScanner = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equals("exit")) {
            userInput = userLineScanner.nextLine().trim();
            TaskManager.checkCommand(userInput);
        }
    }
}

