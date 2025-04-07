package seedu.medinote.commands;

import seedu.medinote.person.Patient;
import seedu.medinote.manager.PatientListManager;
import seedu.medinote.storage.SaveData;

import java.util.ArrayList;
import java.util.Scanner;

public class RegisterPatient {

    private static final String LINE_BREAK = "===============================================" +
            "===============================================";
    private static final int NEW_PATIENT_PARAMETER_LENGTH = 4;
    private static final String MISSING_PATIENT_DETAILS = "\tCompulsory patient details are incomplete.";
    private static final String CORRECT_PATIENT_DETAILS_FORMAT =
            "\tExpected Format: register <name> / <symptoms> / <admission time> / <medical history>";
    private static final String DUPLICATE_PATIENT_MESSAGE = "\tPatient has the same name as an existing patient.\n" +
            "\tPlease give the patient another name below (without ANY parameters):";
    private static final String SUCCESSFUL_REGISTRATION_MESSAGE = " has been successfully registered as a patient!";
    private static final String FAILED_REGISTRATION_MESSAGE = "\tName already exists. " +
            "Please input a different name, without parameters:";
    private static final String NAME_MESSAGE = "\tPlease ONLY enter the new name, without parameters!";

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
                for (int i = 0; i < patientInfo.length; i++) {
                    patientInfo[i] = reformatPatientInfoParameters(patientInfo[i].trim());
                }
                Patient patient = new Patient(patientInfo[0].trim(), patientInfo[1].trim(),
                        patientInfo[2].trim(), patientInfo[3].trim(), "NA", "NA");

                if (isNameInList(patientInfo[0].trim())) {
                    System.out.println(DUPLICATE_PATIENT_MESSAGE);

                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        patientInfo[0] = scanner.nextLine().trim();
                        System.out.println(LINE_BREAK);
                        patientInfo[0] = reformatPatientInfoParameters(patientInfo[0]);

                        if (!isNameInList(patientInfo[0])) {
                            if (patientInfo[0].contains("/")) {
                                System.out.println(NAME_MESSAGE);
                            } else {
                                patient = new Patient(patientInfo[0], patientInfo[1].trim(), patientInfo[2].trim(),
                                        patientInfo[3].trim(), "NA", "NA");
                                PatientListManager.addPatient(patient);
                                System.out.println("\tPatient " + patientInfo[0] +
                                        SUCCESSFUL_REGISTRATION_MESSAGE);
                                break;
                            }
                        } else {
                            System.out.println(FAILED_REGISTRATION_MESSAGE);
                        }
                    }
                } else {
                    PatientListManager.addPatient(patient);
                    System.out.println("\tPatient " + patientInfo[0].trim() + SUCCESSFUL_REGISTRATION_MESSAGE);
                }

                try {
                    new SaveData().savePatientsData(PatientListManager.getPatientList());
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

    private static String reformatPatientInfoParameters(String info) {
        String[] splitInfo = info.split(" +");
        return joinWords(splitInfo);
    }

    private static String joinWords(String[] separatedWords) {
        String sentence = separatedWords[0];
        for (int i = 1; i < separatedWords.length; i++) {
            sentence = sentence.concat(" " + separatedWords[i]);
        }
        return sentence;
    }

    public static boolean isNameInList(String name) {
        ArrayList<Patient> patients = PatientListManager.getPatientList();
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
