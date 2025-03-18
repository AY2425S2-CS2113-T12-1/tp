package seedu.duke;

import java.util.ArrayList;

public class PatientLister {
    public static void listPatients() {
        ArrayList<Patient> patientList = PatientListManager.getPatientList();
    }
}
