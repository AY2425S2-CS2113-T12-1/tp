package seedu.medinote.commands;

import seedu.medinote.person.Patient;
import seedu.medinote.manager.PatientListManager;
import seedu.medinote.storage.saveData;

public class RegisterPatient {

    private static final String LINE_BREAK = "===============================================" +
            "===============================================";
    private static final int NEW_PATIENT_PARAMETER_LENGTH = 4;
    private static final String MISSING_PATIENT_DETAILS = "\tCompulsory patient details are incomplete.";
    private static final String CORRECT_PATIENT_DETAILS_FORMAT =
            "\tExpected Format: register <name> / <symptoms> / <admission time> / <medical history>";

    public static void registerPatient(String patientDetails) {
        System.out.println(LINE_BREAK);

        try {
            String[] patientInfo = patientDetails.split("/");

            for (String info : patientInfo) {
                if (info.trim().isEmpty()) {
                    throw new IllegalArgumentException(CORRECT_PATIENT_DETAILS_FORMAT);
                }
            }

            if (patientInfo.length == NEW_PATIENT_PARAMETER_LENGTH) {
                Patient patient = new Patient(patientInfo[0].trim(), patientInfo[1].trim(),
                        patientInfo[2].trim(), patientInfo[3].trim(), "NA", "NA");
                PatientListManager.addPatient(patient);
                System.out.println("\tPatient " + patientInfo[0].trim() + " successfully registered as patient!");

                try {
                    new saveData().savePatientsData(PatientListManager.getPatientList());
                } catch (Exception e) {
                    System.out.println("\tError saving patient data: " + e.getMessage());
                }
            } else {
                throw new IllegalArgumentException(MISSING_PATIENT_DETAILS + "\n"
                        + CORRECT_PATIENT_DETAILS_FORMAT);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(LINE_BREAK);

    }

}
