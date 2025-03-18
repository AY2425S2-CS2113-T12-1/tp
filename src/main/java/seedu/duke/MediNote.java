package seedu.duke;

import java.util.Scanner;

public class MediNote {
    private static boolean isMedinoteOn = true;

    public static void setIsMedinoteOn(boolean state) {
        isMedinoteOn = false;
    }




    public static void main(String[] args) {
        final Scanner userLineScanner = new Scanner(System.in);
        //showHelp();

        while (isMedinoteOn) {
            String userInput = userLineScanner.nextLine().trim();
            String[] words = userInput.split(" ", 2);
            String command = words[0].toLowerCase();
            String arguments = words.length > 1 ? words[1] : "";
            TaskManager.checkCommand(command, arguments);
        }
    }
}


