package seedu.medinote.commands;

import seedu.medinote.person.Doctor;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.storage.saveData;

public class RegisterDoctor {

    private static final String LINE_BREAK = "===============================================" +
            "===============================================";
    private static final int NEW_DOCTOR_PARAMETER_LENGTH = 2;
    private static final String MISSING_DOCTOR_DETAILS = "\tCompulsory doctor details are incomplete.";
    private static final String CORRECT_DOCTOR_DETAILS_FORMAT =
            "\tExpected Format: oncall <name> / <specialisation>";


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
                System.out.println("\tDoctor " + doctorInfo[0].trim() + " successfully registered as doctor!");

                try {
                    saveData.saveDoctorsData(DoctorListManager.getDoctorList());
                } catch (Exception e) {
                    System.out.println("\tError saving doctor data: " + e.getMessage());
                }
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
