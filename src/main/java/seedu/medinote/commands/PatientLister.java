package seedu.medinote.commands;

import java.util.ArrayList;

import seedu.medinote.person.Patient;

public class PatientLister {
    public static final String LINE_BREAK = "===============================================" +
            "===============================================";

    public static void listAllPatients(ArrayList<Patient> patientList) {
        printListPatientHeader(patientList.size());
        int patientIndex = 1;
        for (Patient patient : patientList) {
            printOnePatientDetails(patientIndex, patient);
            patientIndex++;
        }
        System.out.println(LINE_BREAK);
    }

    private static void printOnePatientDetails(int patientIndex, Patient patient) {
        System.out.print(patientIndex + ". ");
        System.out.println(patient.getName() + ":");
        System.out.println("\t>Time of Admission: " + patient.getTimeStamp());
        System.out.println("\t>Symptoms: " + patient.getSymptoms());
        System.out.println("\t>Medical History: " + patient.getMedicalHistory());
        System.out.println("\t>Treatment Status: " + patient.getTreatmentStatus());
        System.out.println("\t>Doctor Assigned: " + patient.getDoctorAssigned() + System.lineSeparator());
    }

    private static void printListPatientHeader(int size) {
        System.out.println(LINE_BREAK);
        System.out.println("You have " + size + " patient(s) in hospital");
    }

}
