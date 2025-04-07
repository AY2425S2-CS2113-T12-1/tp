package seedu.medinote.commands;

import java.util.ArrayList;

import seedu.medinote.person.Patient;
import seedu.medinote.manager.PatientListManager;
import seedu.medinote.storage.SaveData;

public class DischargePatient {
    public static void dischargePatient(String patientName) {
        assert patientName != null : "Patient name should not be null.";
        patientName = patientName.trim();

        if(patientName.isEmpty()) {
            System.out.println("Please provide a patient name and try again.");
            return;
        }

        ArrayList<Patient> patientList = PatientListManager.getPatientList();
        assert patientList != null : "Patient list should not be null.";

        for(Patient patient : patientList) {
            assert patient != null : "Patient object should not be null.";
            String currPatientName = patient.getName();
            assert currPatientName != null : "Patient name should not be null.";

            if(currPatientName.equalsIgnoreCase(patientName)) {
                PatientListManager.removePatient(patient);
                System.out.println("Patient " + patientName + " has been discharged " +
                        "and removed from their doctor's assignment.");
                try {
                    new SaveData().savePatientsData(patientList);
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
