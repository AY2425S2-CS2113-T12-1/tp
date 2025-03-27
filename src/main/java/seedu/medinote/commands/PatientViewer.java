package seedu.medinote.commands;

import java.util.ArrayList;

import seedu.medinote.manager.PatientListManager;
import seedu.medinote.person.Patient;

public class PatientViewer {
    private static final String LINE_BREAK = "===============================================" +
            "===============================================";
    private static final String VIEWABLE_PATIENTS_HEADER = "\tHere is a list of viewable patients:";
    private static final String VIEW_PATIENT_FORMAT_SUGGESTION = "\tUse command format: patient <NAME>.";
    private static final String NO_PATIENTS_MESSAGE = "\tCurrently no viewable patients! Use register to add a patient.";
    private static final int INVALID_INDEX_IN_PATIENT_LIST = -1;
    private static final String NAME_POINT = "\t\t>Name: ";
    private static final String SYMPTOMS_POINT = "\t\t\t>Symptoms: ";
    private static final String TIME_STAMP_POINT = "\t\t\t>Time Stamp: ";
    private static final String MEDICAL_HISTORY_POINT = "\t\t\t>Medical History: ";
    private static final String TREATMENT_STATUS_POINT = "\t\t\t>Treatment Status: ";
    private static final String DOCTOR_ASSIGNED_POINT = "\t\t\t>Doctor Assigned: ";

    public static void viewPatient(String targetNames) {
        if (targetNames.isBlank()) {
            showExistingPatients();
        } else {
            searchViewablePatients(targetNames);
        }
    }

    private static void searchViewablePatients(String targetNames) {
        String[] targetNamesArray = targetNames.split("/");

        for (int i = 0; i < targetNamesArray.length; i++) {
            targetNamesArray[i] = targetNamesArray[i].trim();
        }

        ArrayList<Patient> patientList = PatientListManager.getPatientList();
        ArrayList<Integer> nameIndexList = findValidViewablePatientIndexes(targetNamesArray, patientList);
        printViewablePatients(targetNamesArray, nameIndexList, patientList);
    }

    private static ArrayList<Integer> findValidViewablePatientIndexes(String[] targetNamesArray,
                                                                     ArrayList<Patient> patientList) {
        ArrayList<Integer> nameIndexList = new ArrayList<>();
        for (String targetName : targetNamesArray) {
            int indexInPatientList = 0;
            boolean doesPatientExist = false;
            for (Patient patient : patientList) {
                if (patient.getName().equals(targetName)) {
                    nameIndexList.add(indexInPatientList);
                    doesPatientExist = true;
                    break;
                } else {
                    indexInPatientList++;
                }
            }
            if (!doesPatientExist) {
                nameIndexList.add(INVALID_INDEX_IN_PATIENT_LIST);
            }
        }
        return nameIndexList;
    }

    private static void printViewablePatients(String[] targetNamesArray,
                                             ArrayList<Integer> nameIndexList,
                                             ArrayList<Patient> patientList) {
        System.out.println(LINE_BREAK);
        System.out.println("\tDetails of specified patients:");
        for (Integer integer : nameIndexList) {
            if (integer >= 0) {
                printThisPatient(patientList.get(integer));
            }
        }

        int count = 0;
        for (int i = 0; i < nameIndexList.size(); i++) {
            if (nameIndexList.get(i) < 0) {
                if (targetNamesArray[i].isEmpty()) {
                    continue;
                }

                if (count == 0) {
                    System.out.println("\tCould not find patients named:");
                    count++;
                }
                System.out.println("\t\t> " + targetNamesArray[i]);
            }
        }
        System.out.println(LINE_BREAK);
    }

    private static void printThisPatient(Patient patient) {
        System.out.println(NAME_POINT + patient.getName());
        System.out.println(SYMPTOMS_POINT + patient.getSymptoms());
        System.out.println(TIME_STAMP_POINT + patient.getTimeStamp());
        System.out.println(MEDICAL_HISTORY_POINT + patient.getMedicalHistory());
        System.out.println(TREATMENT_STATUS_POINT + patient.getTreatmentStatus());
        System.out.println(DOCTOR_ASSIGNED_POINT + patient.getDoctorAssigned());
    }

    private static void showExistingPatients() {
        ArrayList<Patient> patientList = PatientListManager.getPatientList();
        if (patientList.isEmpty()) {
            System.out.println(NO_PATIENTS_MESSAGE);
        } else {
            printExistingPatients(patientList);
        }
    }

    private static void printExistingPatients(ArrayList<Patient> patientList) {
        System.out.println(LINE_BREAK);
        System.out.println(VIEWABLE_PATIENTS_HEADER);
        for (Patient patient : patientList) {
            System.out.println("\t\t> " + patient.getName());
        }
        System.out.println(VIEW_PATIENT_FORMAT_SUGGESTION);
        System.out.println(LINE_BREAK);
    }

}
