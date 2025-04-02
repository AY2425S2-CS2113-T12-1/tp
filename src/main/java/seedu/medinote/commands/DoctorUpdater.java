package seedu.medinote.commands;

import seedu.medinote.person.Doctor;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.storage.saveData;

public class DoctorUpdater {
    public static void updateDoctor(String doctorDetails) {
        String[] details = doctorDetails.split(" ", 3);

        if (details.length < 3) {
            System.out.println("Invalid format. \nUse: update doctor <NAME> <AVAILABILITY> <PATIENT NAME>");
            return;
        }

        Doctor doctor = DoctorListManager.getDoctorList()
                .stream()// This converts the list of doctors into a stream, allowing operations like filtering.
                .filter(d -> d.getName().equalsIgnoreCase(details[0]))
                .findFirst()
                .orElse(null);

        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        doctor.setAvailability(details[1]);
        doctor.setCurrentPatient(details[2]);
        System.out.println("Doctor information updated successfully.");

        try {
            saveData.saveDoctorsData(DoctorListManager.getDoctorList());
        } catch (Exception e) {
            System.out.println("Error saving doctor data: " + e.getMessage());
        }

    }

}
