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
     */
    public ArrayList<Doctor> loadDoctorData() throws IOException {
        ArrayList<Doctor> doctors = new ArrayList<>();

        if (!Files.exists(Paths.get(SaveData.DOCTOR_FILE_PATH))) {
            return doctors;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(SaveData.DOCTOR_FILE_PATH))) {
            String line;
            int lineNumber = 0;

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

                    if (fields.length != DOCTOR_FIELD_COUNT) {
                        System.err.println("Malformed data in MediNote_Doctor_Data.txt at line " + lineNumber
                                + "(expected " + PATIENT_FIELD_COUNT + " fields): " + line);
                        continue;
                    }

                    doctors.add(new Doctor(
                            fields[0].trim(), // name
                            fields[1].trim(), // specialty
                            fields[2].trim(), // availableHours
                            fields[3].trim(), // patientsBeingTreated
                            fields[4].trim()
                    ));

                } catch (Exception e) {
                    System.err.println("Error in MediNote_Doctor_Data.txt at line " + lineNumber
                            + ": " + line + " (" + e.getMessage() + ")");
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
                        System.err.println("Malformed data in MediNote_Patient_Data.txt at line " + lineNumber
                                + "(expected " + PATIENT_FIELD_COUNT + " fields): " + line);
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
                    System.err.println("Error in MediNote_Patient_Data.txt at line " + lineNumber
                            + ": " + line + " (" + e.getMessage() + ")");
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
