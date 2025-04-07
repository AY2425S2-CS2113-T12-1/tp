package seedu.medinote.commands;

import seedu.medinote.person.Patient;
import seedu.medinote.manager.PatientListManager;

import java.util.ArrayList;

public class ViewPatientAttributes {

    private static final String LINE_BREAK = "==================================================";

    // Prints each patient's individual attribute value for the requested attribute
    // Accepted attributes: name, symptoms, timestamp, history, treatment, doctor
    public static void viewPatientAttribute(String input) {
        String[] parts = input.trim().split(" ", 3);

        if (parts.length < 3) {
            System.out.println(LINE_BREAK);
            System.out.println("Please specify an attribute to view. Usage: view patient <attribute>");
            System.out.println(LINE_BREAK);
            return;
        }

        String attribute = parts[2].toLowerCase();
        ArrayList<Patient> patientList = PatientListManager.getPatientList();

        if (patientList.isEmpty()) {
            System.out.println(LINE_BREAK);
            System.out.println("No patients available to view.");
            System.out.println(LINE_BREAK);
            return;
        }

        System.out.println(LINE_BREAK);
        System.out.println("Viewing attribute: " + attribute);
        System.out.println(LINE_BREAK);

        for (Patient p : patientList) {
            String label = p.getName();
            String result;

            switch (attribute) {
            case "name":
                result = p.getName();
                break;
            case "symptoms":
                result = p.getSymptoms();
                break;
            case "timestamp":
                result = p.getTimeStamp();
                break;
            case "history":
                result = p.getMedicalHistory();
                break;
            case "treatment":
                result = p.getTreatmentStatus();
                break;
            case "doctor":
                result = p.getDoctorAssigned();
                break;
            default:
                System.out.println(LINE_BREAK);
                System.out.println("Unknown attribute: " + attribute);
                System.out.println(LINE_BREAK);
                return;
            }

            System.out.println(label + ": " + result);
        }

        System.out.println(LINE_BREAK);
    }
}
