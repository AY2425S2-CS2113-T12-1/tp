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
            if(patient.getDoctorAssigned().equalsIgnoreCase(doctor.getName())) {
                // remove patient from doctor's attribute
                doctor.setCurrentPatient("na");
            }
        }
    }

}
