package seedu.medinote.commands;

import java.util.ArrayList;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;
import seedu.medinote.storage.SaveData;

public class DeleteDoctor {
    public static void deleteDoctor(String docName) {
        assert docName != null: "Doctor name should not be null.";
        docName = docName.trim();

        if(docName.isEmpty()) {
            System.out.println("Please provide a doctor name and try again.");
            return;
        }

        ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();
        assert doctorList != null: "Doctor list should not be null.";
        assert (!doctorList.isEmpty()): "Doctor list should not be empty.";

        for(Doctor doctor : doctorList) {
            assert doctor != null: "Doctor object should not be empty.";
            String currdoctorName = doctor.getName();
            assert currdoctorName != null : "Doctor name should not be empty.";
            if(currdoctorName.equalsIgnoreCase(docName)) {
                DoctorListManager.removeDoctor(doctor);
                try {
                    SaveData.saveDoctorsData(doctorList);
                    System.out.println("Doctor data saved successfully.");
                } catch (Exception e) {
                    System.out.println("Error saving doctor data: " + e.getMessage());
                }
                return;
            }
        }

        System.out.println("Doctor with name \"" + docName + "\" was not found");

    }

}
