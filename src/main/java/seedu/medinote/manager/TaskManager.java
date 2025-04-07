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
            if (arguments.startsWith("patient ")) {
                String[] argumentSplit = arguments.split(" ", 2);
                String parameters = argumentSplit.length > 1 ? argumentSplit[1] : "";
                PatientUpdater.updatePatient(parameters);
            } else if (arguments.startsWith("doctor ")) {
                String[] argumentSplit = arguments.split(" ", 2);
                String parameters = argumentSplit.length > 1 ? argumentSplit[1] : "";
                DoctorUpdater.updateDoctor(parameters);
            } else {
                System.out.println("\tExample format: update patient <NAME> /" +
                        " status=<NEW_STATUS> / doctor=<NEW_DOCTOR>");
                System.out.println("\tstatus: updates the treatment status of patient.");
                System.out.println("\tdoctor: updates the doctor assigned to this patient.");
                System.out.println("\tOR");
                System.out.println("\tExample format: update doctor <NAME> /" +
                        " availability=<AVAILABILITY> / assignment=<NEW_PATIENTS>");
                System.out.println("\tavailability: updates the availability of the doctor.");
                System.out.println("\tassignment: updates the patients assigned to this doctor.");
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
                ViewPatientAttributes.viewPatientAttribute("view patient " + arguments.substring(8));
            } else if (arguments.startsWith("doctor ")) {
                ViewDoctorAttributes.viewDoctorAttribute("view doctor " + arguments.substring(7));
            } else {
                System.out.println("Invalid view command. Try: view <patient Name> <attribute>");
            }
            break;

        case "stats":
            OverallStatistics.showStatistics();
            break;

        case "exit":
            if (!arguments.isBlank()) {
                System.out.println("Invalid command. Type 'help' for a list of commands.");
            }
            break;

        default:
            System.out.println("Invalid command. Type 'help' for a list of commands.");
            break;
        }
    }
}
