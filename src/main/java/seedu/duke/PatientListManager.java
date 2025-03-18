package seedu.duke;

import java.util.ArrayList;

public class PatientListManager {
    private static ArrayList<Patient> patientList = new ArrayList<>();

    public static ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public static void listPatients() {
        Patient newPatient = new Patient("jiaen", "dry skin between ring finger and pinky, hair turning white", "today", "allergic to prawn", "not treated", "siqiang");
        patientList.add(newPatient);
        PatientLister.listAllPatients(patientList);
    }
}
