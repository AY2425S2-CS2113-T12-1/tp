package seedu.medinote.storage;

import seedu.medinote.person.Doctor;
import seedu.medinote.person.Patient;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LoadData {
    private static final String FIELD_SEPARATOR = "\\|"; // Escape pipe for regex
    private static final int DOCTOR_FIELD_COUNT = 5;
    private static final int PATIENT_FIELD_COUNT = 6;

    /**
     * Loads doctor data from file and returns an ArrayList of Doctor objects.
     *
     * @return ArrayList of Doctor objects
     * @throws IOException         If there's an error reading the file
     * @throws DataFormatException  If there's an error with the format of the data stored
     */
    public ArrayList<Doctor> loadDoctorData() throws IOException, DataFormatException {
        ArrayList<Doctor> doctors = new ArrayList<>();

        // Check if the file exists, if not, return an empty list
        if (!Files.exists(Paths.get(SaveData.DOCTOR_FILE_PATH))) {
            return doctors;
        }

        // Try-with-resources to automatically close the BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(SaveData.DOCTOR_FILE_PATH))) {
            String line;
            int lineNumber = 0;

            // Handle the header row, if present
            br.mark(1000);
            String firstLine = br.readLine();
            boolean hasHeader = (firstLine != null && firstLine.startsWith("name|"));

            if (!hasHeader) {
                br.reset();
            } else {
                lineNumber = 1;  // Header is line 1
            }

            // Read the rest of the lines and parse doctor data
            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                try {
                    // Split line by FIELD_SEPARATOR to get the doctor attributes
                    String[] fields = line.split(FIELD_SEPARATOR, -1);

                    // Check if the number of fields matches the expected count
                    if (fields.length != DOCTOR_FIELD_COUNT) {
                        throw new DataFormatException("Malformed data at line " + lineNumber + " (expected " +
                                DOCTOR_FIELD_COUNT + " fields): " + line);
                    }

                    // Add the doctor object to the list
                    doctors.add(new Doctor(
                            fields[0].trim(), // name
                            fields[1].trim(), // specialty
                            fields[2].trim(), // availableHours
                            fields[3].trim()  // patientsBeingTreated
                    ));

                } catch (Exception e) {
                    throw new DataFormatException("Error at line " + lineNumber + ": " + line + " ("
                            + e.getMessage() + ")");
                }
            }
        }
        return doctors;
    }

    /**
     * Loads patient data from file and returns an ArrayList of Patient objects.
     *
     * @return ArrayList of Patient objects
     * @throws IOException         If there's an error reading the file
     */
    public ArrayList<Patient> loadPatientData() throws IOException {
        ArrayList<Patient> patients = new ArrayList<>();

        if (!Files.exists(Paths.get(SaveData.PATIENT_FILE_PATH))) {
            return patients;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(SaveData.PATIENT_FILE_PATH))) {
            String line;
            int lineNumber = 0;

            // Header handling - FIXED
            br.mark(1000);
            String firstLine = br.readLine();
            boolean hasHeader = (firstLine != null && firstLine.startsWith("name|"));

            if (!hasHeader) {
                br.reset();
            } else {
                lineNumber = 1;  // Header is line 1
            }

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                try {
                    String[] fields = line.split(FIELD_SEPARATOR, -1);

                    if (fields.length != PATIENT_FIELD_COUNT) {
                        System.err.printf("Malformed data at line %d (expected %d fields): %s%n",
                                lineNumber, PATIENT_FIELD_COUNT, line);
                        continue;
                    }

                    patients.add(new Patient(
                            fields[0].trim(), // name
                            fields[1].trim(), // symptoms
                            fields[2].trim(), // timeStamp
                            fields[3].trim(), // medicalHistory
                            fields[4].trim(), // treatmentStatus
                            fields[5].trim()  // doctorAssigned
                    ));

                } catch (Exception e) {
                    System.err.printf("Error at line %d: %s (%s)%n",
                            lineNumber, line, e.getMessage());
                }
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
