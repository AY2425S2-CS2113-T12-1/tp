package seedu.medinote.commands;

import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.manager.PatientListManager;
import seedu.medinote.person.Doctor;
import seedu.medinote.person.Patient;

import java.util.List;

public class OverallStatistics {
    private static final String LINE_BREAK = "===============================================" +
            "===============================================";

    public static void showStatistics(){
        List<Patient> patients = PatientListManager.getPatientList();
        List<Doctor> doctors = DoctorListManager.getDoctorList();

        long totalPatientNum = patients.size();
        long treatedPatientNum = 0;

        for (Patient patient : patients) {
            if (!patient.getTreatmentStatus().equalsIgnoreCase("NA")) {
                if (!patient.getTreatmentStatus().equalsIgnoreCase("queue")){
                    treatedPatientNum += 1;
                }
            }
        }

        long totalDoctorNum = doctors.size();
        Doctor mostActiveDoctor = null;
        long maxPatientTreatedNum = -1;
        double totalPatientCount = 0;

        for(Doctor doctor : doctors){
            int numTreatedPatients = doctor.getNumPatientsTreated();
            totalPatientCount += numTreatedPatients;

            if(numTreatedPatients > maxPatientTreatedNum){
                maxPatientTreatedNum = numTreatedPatients;
                mostActiveDoctor = doctor;
            }
        }

        double averagePatientPerDoctor = (totalDoctorNum>0) ? (double) totalPatientNum/totalDoctorNum : 0;

        System.out.println(LINE_BREAK);
        System.out.println("\tHospital Summary Statistics:");
        System.out.println("\t> Total number of patients: " + totalPatientNum);
        System.out.println("\t> Number of Patients currently being treated: " + treatedPatientNum);
        System.out.println("\t> Total number of doctors: " + totalDoctorNum);
        if (mostActiveDoctor != null) {
            System.out.print("\t> Most active doctor is: Dr. " + mostActiveDoctor.getName());
            System.out.println(" (" + maxPatientTreatedNum + " patients)");
        } else {
            System.out.println("\t> Most active doctor: None yet");
        }
        System.out.printf("\t> Average patients per doctor: %.2f\n", averagePatientPerDoctor);
        System.out.println(LINE_BREAK);
    }

}
