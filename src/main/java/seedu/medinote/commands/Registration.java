package seedu.medinote.commands;

import seedu.medinote.person.Doctor;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Patient;
import seedu.medinote.manager.PatientListManager;

public class Registration {

    private static final String LINE_BREAK = "===============================================" +
            "===============================================";
    private static final int NEW_PATIENT_PARAMETER_LENGTH = 4;
    private static final String MISSING_PATIENT_DETAILS = "\tCompulsory patient details are incomplete.";
    private static final String CORRECT_PATIENT_DETAILS_FORMAT =
            "\tExpected Format: register <name> / <symptoms> / <admission time> / <medical history>";
    private static final int NEW_DOCTOR_PARAMETER_LENGTH = 2;
    private static final String MISSING_DOCTOR_DETAILS = "\tCompulsory doctor details are incomplete.";
    private static final String CORRECT_DOCTOR_DETAILS_FORMAT =
            "\tExpected Format: oncall <name> / <specialization>";

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
                // Call view patient and print out details. Omit name
            } else {
                throw new IllegalArgumentException(MISSING_PATIENT_DETAILS + "\n"
                        + CORRECT_PATIENT_DETAILS_FORMAT);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(LINE_BREAK);

    }

    public static void registerDoctor(String doctorDetails) {
        System.out.println(LINE_BREAK);
        try {
            String[] doctorInfo = doctorDetails.split("/");

            for (String info : doctorInfo) {
                if (info.trim().isEmpty()) {
                    throw new IllegalArgumentException(CORRECT_DOCTOR_DETAILS_FORMAT);
                }
            }

            if (doctorInfo.length == NEW_DOCTOR_PARAMETER_LENGTH) {
                Doctor doctor = new Doctor (doctorInfo[0].trim(), doctorInfo[1].trim(), "NA", "NA");
                DoctorListManager.addDoctor(doctor);
                System.out.println("Doctor " + doctorInfo[0].trim() + " successfully registered as doctor!");
                // Call view doctor and print out details. Omit name
            } else {
                throw new IllegalArgumentException(MISSING_DOCTOR_DETAILS + "\n"
                        + CORRECT_DOCTOR_DETAILS_FORMAT);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(LINE_BREAK);

    }
}
