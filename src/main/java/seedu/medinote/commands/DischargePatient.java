package seedu.medinote.commands;

import java.util.ArrayList;

import seedu.medinote.person.Patient;
import seedu.medinote.manager.PatientListManager;
import seedu.medinote.storage.saveData;

public class DischargePatient {
    public static void dischargePatient(String patientName) {
        patientName = patientName.trim();

        if(patientName.isEmpty()) {
            System.out.println("Please provide a patient name and try again.");
            return;
        }

        ArrayList<Patient> patientList = PatientListManager.getPatientList();

        for(Patient patient : patientList) {
            String currPatientName = patient.getName();
            if(currPatientName.equalsIgnoreCase(patientName)) {
                PatientListManager.removePatient(patient);
                System.out.println("Patient " + patientName + " has been discharged " +
                        "and removed from their doctor's assignment.");
                try {
                    new saveData().savePatientsData(patientList);
                    System.out.println("Patient data saved successfully.");
                } catch (Exception e) {
                    System.out.println("Error saving patient data: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Patient with name \"" + patientName + "\" was not found");

    }

}
