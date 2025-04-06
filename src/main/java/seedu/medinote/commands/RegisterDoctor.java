package seedu.medinote.commands;

import seedu.medinote.person.Doctor;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.storage.saveData;

import java.util.ArrayList;
import java.util.Scanner;

public class RegisterDoctor {

    private static final String LINE_BREAK = "===============================================" +
            "===============================================";
    private static final int NEW_DOCTOR_PARAMETER_LENGTH = 2;
    private static final String MISSING_DOCTOR_DETAILS = "\tCompulsory doctor details are incomplete.";
    private static final String CORRECT_DOCTOR_DETAILS_FORMAT =
            "\tExpected Format: oncall <name> / <specialisation>";
    private static final String DUPLICATE_DOCTOR_MESSAGE = "\tDoctor has the same name as an existing doctor.\n" +
            "\tPlease give the doctor another name below (without ANY parameters):";
    private static final String SUCCESSFUL_REGISTRATION_MESSAGE = " has been successfully registered as a doctor!";
    private static final String FAILED_REGISTRATION_MESSAGE = "\tName already exists. " +
            "Please input a different name, without parameters:";
    private static final String NAME_MESSAGE = "\tPlease ONLY enter the new name, without parameters!";

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
                for (int i = 0; i < doctorInfo.length; i++) {
                    doctorInfo[i] = reformatDoctorInfoParameters(doctorInfo[i].trim());
                }
                Doctor doctor = new Doctor (doctorInfo[0].trim(), doctorInfo[1].trim(),
                        "NA", "NA");

                if (isNameInList(doctorInfo[0].trim())) {
                    System.out.println(DUPLICATE_DOCTOR_MESSAGE);

                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        doctorInfo[0] = scanner.nextLine().trim();
                        System.out.println(LINE_BREAK);
                        doctorInfo[0] = reformatDoctorInfoParameters(doctorInfo[0]);

                        if (!isNameInList(doctorInfo[0])) {
                            if (doctorInfo[0].contains("/")) {
                                System.out.println(NAME_MESSAGE);
                            } else {
                                doctor = new Doctor(doctorInfo[0], doctorInfo[1].trim(), "NA", "NA");
                                DoctorListManager.addDoctor(doctor);
                                System.out.println("\tDoctor " + doctorInfo[0] +
                                        SUCCESSFUL_REGISTRATION_MESSAGE);
                                break;
                            }
                        } else {
                            System.out.println(FAILED_REGISTRATION_MESSAGE);
                        }
                    }
                } else {
                    DoctorListManager.addDoctor(doctor);
                    System.out.println("\tDoctor " + doctorInfo[0].trim() + SUCCESSFUL_REGISTRATION_MESSAGE);
                }

                try {
                    saveData.saveDoctorsData(DoctorListManager.getDoctorList());
                } catch (Exception e) {
                    System.out.println("\tError saving doctor data: " + e.getMessage());
                }
            } else {
                throw new IllegalArgumentException(MISSING_DOCTOR_DETAILS + "\n"
                        + CORRECT_DOCTOR_DETAILS_FORMAT);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(LINE_BREAK);

    }

    private static String reformatDoctorInfoParameters(String info) {
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
        ArrayList<Doctor> doctors = DoctorListManager.getDoctorList();
        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
