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
     * Loads doctor data from the storage file with strict format validation
     * Returns an ArrayList of valid Doctor objects
     * This method will:
     * - Skip any malformed lines while logging errors
     * - Handle empty lines gracefully
     * - Preserve all valid doctor records
     * - Provide detailed error reporting
     *
     * @return ArrayList containing all successfully loaded Doctor objects
     * @throws IOException if there's an error accessing the file
     * @throws DataFormatException if any line has incorrect field count
     */
    public ArrayList<Doctor> loadDoctorData() throws IOException, DataFormatException {
        ArrayList<Doctor> doctors = new ArrayList<>();
        boolean hasFormatError = false;
        StringBuilder errorMessages = new StringBuilder();

        if (!Files.exists(Paths.get(SaveData.DOCTOR_FILE_PATH))) {
            return doctors;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(SaveData.DOCTOR_FILE_PATH))) {
            String line;
            int lineNumber = 0;

            // Header handling
            br.mark(1000);
            String firstLine = br.readLine();
            boolean hasHeader = (firstLine != null && firstLine.startsWith("name|"));

            if (!hasHeader) {
                br.reset();
                lineNumber = 0;
            } else {
                lineNumber = 1;
            }

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                try {
                    String[] fields = line.split(FIELD_SEPARATOR, -1);

                    // Strict format validation
                    if (fields.length != DOCTOR_FIELD_COUNT) {
                        hasFormatError = true;
                        errorMessages.append(String.format(
                                "Line %d: Expected %d fields, found %d - %s%n",
                                lineNumber, DOCTOR_FIELD_COUNT, fields.length, line));
                    }

                    // Field content validation
                    if (fields[0].trim().isEmpty()) {
                        throw new IllegalArgumentException("Doctor name cannot be empty");
                    }
                    if (fields[1].trim().isEmpty()) {
                        throw new IllegalArgumentException("Specialisation cannot be empty");
                    }

                    doctors.add(new Doctor(
                            fields[0].trim(),
                            fields[1].trim(),
                            fields[2].trim(),
                            fields[3].trim()
                    ));

                } catch (IllegalArgumentException e) {
                    errorMessages.append(String.format(
                            "Line %d: %s - %s%n", lineNumber, e.getMessage(), line));
                }
            }
        }

        if (hasFormatError) {
            System.err.print(errorMessages.toString());
            throw new DataFormatException("Invalid doctor data format detected");
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
