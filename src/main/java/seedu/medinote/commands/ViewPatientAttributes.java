package seedu.medinote.commands;

import seedu.medinote.manager.PatientListManager;
import seedu.medinote.person.Patient;
import java.util.List;

public class ViewPatientAttributes {
    private static final String LINE_BREAK = "===============================================" +
            "===============================================";

    public static void PrintPatientAttributes(String attribute){
        List<Patient> patients = PatientListManager.getPatientList();
        attribute = attribute.trim().toLowerCase();

        if(patients.isEmpty()){
            System.out.println(LINE_BREAK);
            System.out.println("No patients found in the system.");
            System.out.println(LINE_BREAK);
            return;
        }

        switch (attribute){
        case "name":
            for (int i = 0; i < patients.size(); i++) {
                System.out.println(LINE_BREAK);
                System.out.println((i + 1) + ". " + patients.get(i).getName());
                System.out.println(LINE_BREAK);
            }
            break;
        case "symptoms":
            for (int i = 0; i < patients.size(); i++) {
                System.out.println(LINE_BREAK);
                System.out.println((i + 1) + ". " + patients.get(i).getName() + patients.get(i).getSymptoms());
                System.out.println(LINE_BREAK);
            }
            break;
        case "treatment":
            for (int i = 0; i < patients.size(); i++) {
                System.out.println(LINE_BREAK);
                System.out.println((i + 1) + ". " + patients.get(i).getName() +  ": " + patients.get(i).getTreatmentStatus());
                System.out.println(LINE_BREAK);
            }
            break;
        case "timestamp":
            for (int i = 0; i < patients.size(); i++) {
                System.out.println(LINE_BREAK);
                System.out.println((i + 1) + ". " + patients.get(i).getName() +  ": " + patients.get(i).getTimeStamp());
                System.out.println(LINE_BREAK);
            }
            break;
        case "history":
            for (int i = 0; i < patients.size(); i++) {
                System.out.println(LINE_BREAK);
                System.out.println((i + 1) + ". " + patients.get(i).getName() +  ": " + patients.get(i).getMedicalHistory());
                System.out.println(LINE_BREAK);
            }
            break;

        case "doctor":
            for (int i = 0; i < patients.size(); i++) {
                System.out.println(LINE_BREAK);
                System.out.println((i + 1) + ". " + patients.get(i).getName() +  ": " + patients.get(i).getDoctorAssigned());
                System.out.println(LINE_BREAK);
            }
            break;
        default:
            System.out.println(LINE_BREAK);
            System.out.println("Invalid Attribute Input. Available options:  name, symptoms, timestamp, history, treatment, doctor");
            System.out.println(LINE_BREAK);
        }

    }

}
