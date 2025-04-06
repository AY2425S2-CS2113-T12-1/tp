package seedu.medinote.manager;

import seedu.medinote.commands.RegisterPatient;
import seedu.medinote.commands.RegisterDoctor;
import seedu.medinote.commands.PatientViewer;
import seedu.medinote.commands.DoctorViewer;
import seedu.medinote.commands.PatientUpdater;
import seedu.medinote.commands.DoctorUpdater;
import seedu.medinote.commands.DischargePatient;
import seedu.medinote.commands.DeleteDoctor;
import seedu.medinote.commands.ViewDoctorFrequencies;
import seedu.medinote.commands.ViewDoctorAttributes;
import seedu.medinote.commands.ViewPatientAttributes;
import seedu.medinote.commands.OverallStatistics;
import seedu.medinote.ui.Ui;

public class TaskManager {

    public static void checkCommand(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0].toLowerCase();
        String arguments = words.length > 1 ? words[1] : "";
        switch (command) {
        case "help":
            Ui.printHelpTable();
            break;
        case "register":
            RegisterPatient.registerPatient(arguments);
            break;
        case "oncall":
            RegisterDoctor.registerDoctor(arguments);
            break;
        case "patient":
            PatientViewer.viewPatient(arguments);
            break;
        case "doctor":
            DoctorViewer.viewDoctor(arguments);
            break;
        case "list":
            if (arguments.equals("patient")) {
                PatientListManager.listPatients();
            } else if (arguments.equals("doctor")) {
                DoctorListManager.listDoctors();
            } else {
                System.out.println("Invalid list command.");
            }
            break;
        case "update":
            if (arguments.startsWith("patient")) {
                PatientUpdater.updatePatient(arguments.substring(8));
            } else if (arguments.startsWith("doctor")) {
                DoctorUpdater.updateDoctor(arguments.substring(7));
            } else {
                System.out.println("Invalid update command.");
            }
            break;
        case "discharge":
            if (arguments.startsWith("patient")) {
                DischargePatient.dischargePatient(arguments.substring((7)));
            } else {
                System.out.println("Invalid discharge patient command.");
            }
            break;
        case "delete":
            if (arguments.startsWith("doctor")) {
                DeleteDoctor.deleteDoctor(arguments.substring(6));
            } else {
                System.out.println("Invalid delete doctor command.");
            }
            break;
        case "popular":
            if (arguments.equals("doctor type")) {
                ViewDoctorFrequencies.viewMostFrequentSpecialisation();
            } else if (arguments.equals("visited doctor")) {
                ViewDoctorFrequencies.viewMostFrequentDoctor();
            } else {
                System.out.println("Invalid view frequencies command.");
            }
            break;
        case "view":
            if (arguments.startsWith("patient ")) {
                ViewPatientAttributes.printPatientAttributes(arguments.substring(8));
            } else if (arguments.startsWith("doctor ")) {
                ViewDoctorAttributes.printDoctorAttributes(arguments.substring(7));
            } else {
                System.out.println("Invalid view command. Try: view <patient Name> <attribute>");
            }
            break;

        case "stats":
            OverallStatistics.showStatistics();
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
