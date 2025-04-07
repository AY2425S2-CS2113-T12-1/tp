package seedu.medinote.manager;

import java.util.ArrayList;

import seedu.medinote.commands.DoctorLister;
import seedu.medinote.person.Doctor;
import seedu.medinote.person.Patient;

public class DoctorListManager {
    private static ArrayList<Doctor> doctorList = new ArrayList<>();

    public static ArrayList<Doctor> getDoctorList() {
        return doctorList;
    }

    public static void listDoctors() {
        DoctorLister.listAllDoctors(doctorList);
    }

    public static void addDoctor(Doctor doctor) {
        doctorList.add(doctor);
    }

    public static void removeDoctor(Doctor doctor) {
        doctorList.remove(doctor);
        assert doctor.getName() != null : "Doctor name should not be null.";
        System.out.println("Doctor " + doctor.getName() + " has been deleted.");
        ArrayList<Patient> patientList = PatientListManager.getPatientList();

        for(Patient patient : patientList) {
            if(doctor.getPatientBeingTreated().equalsIgnoreCase(patient.getName())) {
                assert patient.getName() != null : "Patient name should not be null.";
                patient.setDoctorAssigned("None");
                assert patient.getDoctorAssigned().equalsIgnoreCase("None") : "Doctor info was not " +
                        "removed from their patient's information.";
                System.out.println("Patient " + patient.getName() + "'s doctor was removed. Please assign a new " +
                        "doctor to this patient or discharge if visit is over.");
            }
        }
    }

    public static void clearDoctorList() {
        doctorList.clear();
    }

}
