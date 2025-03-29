package seedu.medinote.storage;

import seedu.medinote.person.Doctor;
import seedu.medinote.person.Patient;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class saveData {
    public static final String DOCTOR_FILE_PATH = "./hospitalData/MediNote_Doctor_Data.txt";
    public static final String PATIENT_FILE_PATH = "./hospitalData/MediNote_Patient_Data.txt";
    private static final String FIELD_SEPARATOR = "|";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Saves all doctor information to file in a structured format.
     * Format: name|specialisation|availability|patientsBeingTreated|numPatientsTreated
     *
     * @param doctors The list of doctors to save
     * @throws IOException If there's an error writing to file
     */
    public static void saveDoctorsData(ArrayList<Doctor> doctors) throws IOException {
        ensureFilesExist.ensureDoctorsFileExists();

        try (FileWriter fw = new FileWriter(DOCTOR_FILE_PATH)) {
            for (Doctor doctor : doctors) {
                String record = String.join(FIELD_SEPARATOR,
                        doctor.getName(),
                        doctor.getSpecialisation(),
                        doctor.getAvailability(),
                        doctor.getPatientsBeingTreated(),
                        doctor.getNumPatientsTreated().toString());
                fw.write(record + LINE_SEPARATOR);
            }
        }
    }

    /**
     * Saves all patient information to file in a structured format.
     * Format: name|symptoms|timeStamp|medicalHistory|treatmentStatus|doctorAssigned
     *
     * @param patients The list of patients to save
     * @throws IOException If there's an error writing to file
     */
    public void savePatientsData(ArrayList<Patient> patients) throws IOException {
        ensureFilesExist.ensurePatientsFileExists();

        try (FileWriter fw = new FileWriter(PATIENT_FILE_PATH)) {
            for (Patient patient : patients) {
                String record = String.join(FIELD_SEPARATOR,
                        patient.getName(),
                        patient.getSymptoms(),
                        patient.getTimeStamp(),
                        patient.getMedicalHistory(),
                        patient.getTreatmentStatus(),
                        patient.getDoctorAssigned());
                fw.write(record + LINE_SEPARATOR);
            }
        }
    }

}
