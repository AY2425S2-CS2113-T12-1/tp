package seedu.medinote.storage;

import seedu.medinote.person.Doctor;
import seedu.medinote.person.Patient;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class loadData {
    private static final String FIELD_SEPARATOR = "\\|"; // Escape pipe for regex
    private static final int DOCTOR_FIELD_COUNT = 5;
    private static final int PATIENT_FIELD_COUNT = 6;

    /**
     * Loads doctor data from file and returns an ArrayList of Doctor objects.
     *
     * @return ArrayList of Doctor objects
     * @throws IOException         If there's an error reading the file
     * @throws DataFormatException If the data format is invalid
     */
    public ArrayList<Doctor> loadDoctorData() throws IOException, DataFormatException {
        ArrayList<Doctor> doctors = new ArrayList<>();

        // Check if file exists and is not empty
        if (!Files.exists(Paths.get(saveData.DOCTOR_FILE_PATH))) {
            return doctors; // Return empty list if file doesn't exist
        }

        try (BufferedReader br = new BufferedReader(new FileReader(saveData.DOCTOR_FILE_PATH))) {
            String line;
            boolean hasHeader = false;

            // Check if first line is header
            br.mark(1000); // Mark the current position
            String firstLine = br.readLine();
            if (firstLine != null && firstLine.startsWith("name|")) {
                hasHeader = true;
            } else {
                br.reset(); // Reset to the beginning if no header
            }

            while ((line = br.readLine()) != null) {
                line = line.trim();

                String[] fields = line.split(FIELD_SEPARATOR, -1); // -1 keeps empty fields
                if (fields.length != DOCTOR_FIELD_COUNT) {
                    throw new DataFormatException("Invalid doctor data format in line: " + line);
                }

                Doctor doctor = new Doctor(
                        fields[0].trim(), // name
                        fields[1].trim(), // specialisation
                        fields[2].trim(), // availability
                        fields[3].trim()  // patientsBeingTreated
                );
                doctors.add(doctor);
            }
        }
        return doctors;

    }

    /**
     * Loads patient data from file and returns an ArrayList of Patient objects.
     *
     * @return ArrayList of Patient objects
     * @throws IOException         If there's an error reading the file
     * @throws DataFormatException If the data format is invalid
     */
    public ArrayList<Patient> loadPatientData() throws IOException, DataFormatException {
        ArrayList<Patient> patients = new ArrayList<>();

        // Check if file exists and is not empty
        if (!Files.exists(Paths.get(saveData.PATIENT_FILE_PATH))) {
            return patients; // Return empty list if file doesn't exist
        }

        try (BufferedReader br = new BufferedReader(new FileReader(saveData.PATIENT_FILE_PATH))) {
            String line;
            boolean hasHeader = false;

            // Check if first line is header
            br.mark(1000); // Mark the current position
            String firstLine = br.readLine();
            if (firstLine != null && firstLine.startsWith("name|")) {
                hasHeader = true;
            } else {
                br.reset(); // Reset to the beginning if no header
            }

            while ((line = br.readLine()) != null) {
                line = line.trim();

                String[] fields = line.split(FIELD_SEPARATOR, -1); // -1 keeps empty fields
                if (fields.length != PATIENT_FIELD_COUNT) {
                    throw new DataFormatException("Invalid patient data format in line: " + line);
                }

                Patient patient = new Patient(
                        fields[0].trim(), // name
                        fields[1].trim(), // symptoms
                        fields[2].trim(), // timeStamp
                        fields[3].trim(), // medicalHistory
                        fields[4].trim(), // treatmentStatus
                        fields[5].trim()  // doctorAssigned
                );
                patients.add(patient);
            }
        }
        return patients;

    }

    /**
     * Custom exception for invalid data format
     */
    public static class DataFormatException extends Exception {
        public DataFormatException(String message) {
            super(message);
        }
    }

}
