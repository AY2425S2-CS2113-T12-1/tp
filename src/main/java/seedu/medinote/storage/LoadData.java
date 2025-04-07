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
                        System.err.println("Line " + lineNumber + ": Expected " + DOCTOR_FIELD_COUNT +
                                " fields, found " + fields.length + " - " + line + System.lineSeparator());
                        continue;
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
                    errorMessages.append("Line " + lineNumber + ": " + e.getMessage() + " - "
                            + line + System.lineSeparator());
                }
            }
        }

        if (hasFormatError && doctors.isEmpty()) {
            System.err.print(errorMessages.toString());
            throw new DataFormatException("Invalid doctor data format: No valid records loaded");
        } else if (hasFormatError) {
            System.err.print(errorMessages.toString());
            System.err.println("Some doctor records were skipped due to format issues.");
        }

        System.out.println("Loaded " + doctors.size() + " valid doctor records");
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
            } else {
                lineNumber = 1;  // Header is line 1
            }

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                try {
                    String[] fields = line.split(FIELD_SEPARATOR, -1);

                    if (fields.length != PATIENT_FIELD_COUNT) {
                        System.err.println("Malformed data at line " + lineNumber + " (expected " +
                                PATIENT_FIELD_COUNT + " fields): " + line);
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
                    System.err.println("Error at line " + lineNumber + ": " + line + " (" + e.getMessage() + ")");
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
