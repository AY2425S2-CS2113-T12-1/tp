package seedu.medinote.commands;

import java.util.ArrayList;
import seedu.medinote.person.Doctor;

public class DoctorLister {
    public static final String LINE_BREAK = "================================";

    public static void listAllDoctors(ArrayList<Doctor> doctorList) {
        printListDoctorHeader(doctorList.size());
        int doctorIndex = 1;
        for (Doctor doctor : doctorList) {
            printOneDoctorDetails(doctorIndex, doctor);
            doctorIndex++;
        }
        System.out.println(LINE_BREAK);
    }

    private static void printOneDoctorDetails(int doctorIndex, Doctor doctor) {
        assert doctor != null : "doctor should not be null";
        assert doctorIndex != 0 : "doctorIndex should not be 0";
        System.out.println(doctorIndex + ". " + doctor.getName() + ":");
        System.out.println("\t>Specialisation: " + doctor.getSpecialisation());
        System.out.println("\t>Availability: " + doctor.getAvailability());
        System.out.println("\t>Currently treating: " + doctor.getPatientBeingTreated());
        System.out.println(); // Add extra line break after each doctor
    }

    private static void printListDoctorHeader(int size) {
        System.out.println(LINE_BREAK);
        System.out.println("You have " + size + " doctor(s) in hospital");
    }

}
