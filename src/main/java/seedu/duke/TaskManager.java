package seedu.duke;

import java.util.ArrayList;

public class TaskManager {

    public static void checkCommand(String command, String arguments) {
        switch (command) {
        case "help":
//            showHelp();
            break;
        case "register":
//            registerPatient(arguments);
            break;
        case "oncall":
//            registerDoctor(arguments);
            break;
        case "patient":
//            viewPatient(arguments);
            break;
        case "doctor":
//            viewDoctor(arguments);
            break;
        case "list":
            if (arguments.equals("patient")) {
                PatientLister.listPatients();
            } else if (arguments.equals("doctor")) {
//                listDoctors();
            } else {
                System.out.println("Invalid list command.");
            }
            break;
        case "update":
            if (arguments.startsWith("patient")) {
//                updatePatient(arguments.substring(8));
            } else if (arguments.startsWith("doctor")) {
//                updateDoctor(arguments.substring(7));
            } else {
                System.out.println("Invalid update command.");
            }
            break;
        case "discharge":
//            dischargePatient(arguments);
            break;
        case "delete":
//            deleteDoctor(arguments);
            break;
        case "exit":
            System.out.println("Exiting MediNote...");
            MediNote.setIsMedinoteOn(false);
            return;
        default:
            System.out.println("Invalid command. Type 'help' for a list of commands.");
            break;
        }
    }
}
