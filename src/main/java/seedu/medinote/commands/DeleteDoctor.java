package seedu.medinote.commands;

import java.util.ArrayList;
import seedu.medinote.person.Doctor;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.storage.saveData;

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
                try {
                    saveData.saveDoctorsData(doctorList);
                    System.out.println("Doctor data saved successfully.");
                } catch (Exception e) {
                    System.out.println("Error saving doctor data: " + e.getMessage());
                }
                return;
            }
        }

        System.out.println("Doctor with name \"" + doctorName + "\" was not found");

    }

}
