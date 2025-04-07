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
     * Loads doctor data from the storage file and returns an ArrayList of valid Doctor objects.
     * This method will:
     * - Skip any malformed lines while logging errors
     * - Handle empty lines gracefully
     * - Preserve all valid doctor records
     * - Provide detailed error reporting
     *
     * @return ArrayList containing all successfully loaded Doctor objects
     * @throws IOException if there's an error accessing the file
     */
    public ArrayList<Doctor> loadDoctorData() throws IOException {
        ArrayList<Doctor> doctors = new ArrayList<>();

        if (!Files.exists(Paths.get(saveData.DOCTOR_FILE_PATH))) {
            return doctors;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(saveData.DOCTOR_FILE_PATH))) {
            String line;
            int lineNumber = 0;

            // Fixed header handling
            br.mark(1000);
            String firstLine = br.readLine();
            boolean hasHeader = (firstLine != null && firstLine.startsWith("name|"));

            if (!hasHeader) {
                br.reset();
                lineNumber = 0;  // Reset counter if no header
            } else {
                lineNumber = 1;  // Header is line 1
            }

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                try {
                    String[] fields = line.split(FIELD_SEPARATOR, -1);

                    if (fields.length != DOCTOR_FIELD_COUNT) {
                        System.err.printf("Invalid doctor data at line %d (expected %d fields): %s%n",
                                lineNumber, DOCTOR_FIELD_COUNT, line);
                        continue;
                    }

                    // Field validation
                    if (fields[0].trim().isEmpty()) {
                        throw new IllegalArgumentException("Doctor name cannot be empty");
                    }
                    if (fields[1].trim().isEmpty()) {
                        throw new IllegalArgumentException("Specialisation cannot be empty");
                    }

                    doctors.add(new Doctor(
                            fields[0].trim(), // name
                            fields[1].trim(), // specialisation
                            fields[2].trim(), // availability
                            fields[3].trim()  // patientsBeingTreated
                    ));

                } catch (Exception e) {
                    System.err.printf("Skipping line %d: %s (%s)%n",
                            lineNumber, line, e.getMessage());
                }
            }
        }
        System.out.printf("Loaded %d valid doctor records%n", doctors.size());
        return doctors;
    }

    /**
     * Loads patient data from the storage file and returns an ArrayList of valid Patient objects.
     * This method will:
     * - Skip any malformed lines while logging errors
     * - Handle empty lines gracefully
     * - Preserve all valid patient records
     * - Provide detailed error reporting
     *
     * @return ArrayList containing all successfully loaded Patient objects
     * @throws IOException if there's an error accessing the file
     */
    public ArrayList<Patient> loadPatientData() throws IOException {
        ArrayList<Patient> patients = new ArrayList<>();

        if (!Files.exists(Paths.get(saveData.PATIENT_FILE_PATH))) {
            return patients;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(saveData.PATIENT_FILE_PATH))) {
            String line;
            int lineNumber = 0;

            // Header handling - FIXED
            br.mark(1000);
            String firstLine = br.readLine();
            boolean hasHeader = (firstLine != null && firstLine.startsWith("name|"));

            if (!hasHeader) {
                br.reset();
                lineNumber = 0;  // Reset counter if no header
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
