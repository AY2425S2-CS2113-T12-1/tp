package seedu.duke;

import java.util.ArrayList;

public class PatientDischarger {
    public static void dischargePatient(String patientName) {
        patientName = patientName.trim();
        if(patientName.isEmpty()) {
            System.out.println("Please provide a patient name and try again.");
            return;
        }

        ArrayList<Patient> patientList = PatientListManager.getPatientList();
        for(Patient patient : patientList) {
            if(patient.getName().equalsIgnoreCase(patientName)) {
                PatientListManager.removePatient(patient);
                System.out.println("Patient " + patientName + " has been discharged.");
                return;
            }
        }

        System.out.println("Patient with name \"" + patientName + "\" was not found");
    }
}
