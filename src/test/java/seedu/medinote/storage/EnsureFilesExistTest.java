package seedu.medinote.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.medinote.TestUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EnsureFilesExistTest {

    @BeforeEach
    @AfterEach
    void cleanUp() throws IOException {
        TestUtil.deleteTestFiles();
    }

    @Test
    void ensureDoctorsFileExists_fileCreatedSuccessfully() throws IOException {
        // Verify file doesn't exist initially
        Path doctorsPath = Paths.get(SaveData.DOCTOR_FILE_PATH);
        assertFalse(Files.exists(doctorsPath));

        // Execute method
        EnsureFilesExist.ensureDoctorsFileExists();

        // Verify file exists and has header
        assertTrue(Files.exists(doctorsPath));
        String content = Files.readString(doctorsPath);
        assertTrue(content.startsWith("name|specialisation|availability" +
                "|patientsBeingTreated|numPatientsTreated"));
    }

    @Test
    void ensurePatientsFileExists_fileCreatedSuccessfully() throws IOException {
        // Verify file doesn't exist initially
        Path patientsPath = Paths.get(SaveData.PATIENT_FILE_PATH);
        assertFalse(Files.exists(patientsPath));

        // Execute method
        EnsureFilesExist.ensurePatientsFileExists();

        // Verify file exists and has header
        assertTrue(Files.exists(patientsPath));
        String content = Files.readString(patientsPath);
        assertTrue(content.startsWith("name|symptoms|timeStamp" +
                "|medicalHistory|treatmentStatus|doctorAssigned"));
    }

}
