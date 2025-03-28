package seedu.medinote.commands;

import java.util.ArrayList;

import seedu.medinote.person.Doctor;
import seedu.medinote.manager.DoctorListManager;

public class DeleteDoctor {
    public static void deleteDoctor(String doctorName) {
        doctorName = doctorName.trim();
        if(doctorName.isEmpty()) {
            System.out.println("Please provide a doctor name and try again.");
            return;
        }

        ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();
        for(Doctor doctor : doctorList) {
            if(doctor.getName().equalsIgnoreCase(doctorName)) {
                DoctorListManager.removeDoctor(doctor);
                System.out.println("Doctor " + doctorName + " has been deleted.");
                return;
            }
        }

        System.out.println("Doctor with name \"" + doctorName + "\" was not found");
    }
}
