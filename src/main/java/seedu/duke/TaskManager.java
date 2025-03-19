package seedu.duke;


public class TaskManager {

    public static void checkCommand(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0].toLowerCase();
        String arguments = words.length > 1 ? words[1] : "";
        switch (command) {
        case "help":
//            showHelp();
            break;
        case "register":
            Registration.registerPatient(arguments);
            break;
        case "oncall":
            Registration.registerDoctor(arguments);
            break;
        case "patient":
//            viewPatient(arguments);
            break;
        case "doctor":
//            viewDoctor(arguments);
            break;
        case "list":
            if (arguments.startsWith("patient")) {
                PatientUpdater.updatePatient(arguments.substring(8));
            } else if (arguments.startsWith("doctor")) {
                DoctorUpdater.updateDoctor(arguments.substring(7));
            } else {
                System.out.println("Invalid update command.");
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
            if (arguments.startsWith("patient")) {
                PatientDischarger.dischargePatient(arguments.substring((8));
            } else {
                System.out.println("Invalid discharge patient command.");
            }
            break;
        case "delete":
            if(arguments.startsWith("doctor")) {
                DoctorDeleter.deleteDoctor(arguments.substring(7));
            } else {
                System.out.println("Invalid delete doctor command.");
            }
            break;
        case "exit":
            System.out.println("Exiting MediNote...");
            break;
        default:
            System.out.println("Invalid command. Type 'help' for a list of commands.");
            break;
        }
    }
}