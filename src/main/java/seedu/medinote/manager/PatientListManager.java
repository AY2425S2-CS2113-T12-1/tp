package seedu.medinote.manager;

import java.util.ArrayList;

import seedu.medinote.commands.PatientLister;
import seedu.medinote.person.Patient;
import seedu.medinote.person.Doctor;

public class PatientListManager {
    private static ArrayList<Patient> patientList = new ArrayList<>();

    public static ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public static void listPatients() {
        PatientLister.listAllPatients(patientList);
    }

    public static void addPatient(Patient patient) {
        patientList.add(patient);
    }

    public static void removePatient(Patient patient) {
        patientList.remove(patient);
        ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();
        for(Doctor doctor : doctorList) {
            assert doctor != null : "Doctor object should not be null.";
            if(patient.getDoctorAssigned().equalsIgnoreCase(doctor.getName())) {
                assert doctor.getName() != null : "Doctor name should not be empty/null.";
                doctor.setCurrentPatient("na"); // remove patient from doctor's attribute
                assert doctor.getPatientsBeingTreated().equalsIgnoreCase("na") : "Patient was not removed" + 
                    " from doctor's info.";
            }
        }
    }

    public static void clearPatientList() {
        patientList.clear();
    }

}
