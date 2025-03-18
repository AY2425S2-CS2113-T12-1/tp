package seedu.duke;

import java.util.ArrayList;

public class PatientListManager {
    private static ArrayList<Patient> patientList = new ArrayList<>();

    public static ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public static void listPatients() {
        PatientLister.listAllPatients(patientList);
    }
}
