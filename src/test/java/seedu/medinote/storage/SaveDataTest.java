package seedu.medinote.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.medinote.person.Doctor;
import seedu.medinote.person.Patient;
import seedu.medinote.TestUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SaveDataTest {

    private SaveData dataSaver;

    @BeforeEach
    void setUp() throws IOException {
        dataSaver = new SaveData();
        TestUtil.createTestFiles();
    }

    @AfterEach
    void tearDown() throws IOException {
        TestUtil.deleteTestFiles();
    }

    @Test
    void saveDoctorsData_validData_savesCorrectly() throws IOException {
        // Prepare test data
        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Dr. Smith", "Cardiology",
                "Available", "John Doe"));
        doctors.add(new Doctor("Dr. Lee", "Neurology",
                "Busy", "Sarah Connor"));

        // Execute method
        SaveData.saveDoctorsData(doctors);

        // Verify file content
        String content = Files.readString(Paths.get(SaveData.DOCTOR_FILE_PATH));
        assertTrue(content.contains("Dr. Smith|Cardiology|Available|John Doe|"));
        assertTrue(content.contains("Dr. Lee|Neurology|Busy|Sarah Connor|"));
    }

    @Test
    void savePatientsData_validData_savesCorrectly() throws IOException {
        // Prepare test data
        ArrayList<Patient> patients = new ArrayList<>();
        patients.add(new Patient("John Doe", "Headache",
                "2023-01-01", "None",
                "In Treatment", "Dr. Smith"));
        patients.add(new Patient("Sarah Connor", "Fever",
                "2023-01-02", "Allergies",
                "Waiting", "None"));

        // Execute method
        dataSaver.savePatientsData(patients);

        // Verify file content
        String content = Files.readString(Paths.get(SaveData.PATIENT_FILE_PATH));
        assertTrue(content.contains("John Doe|Headache|2023-01-01|None|In Treatment|Dr. Smith"));
        assertTrue(content.contains("Sarah Connor|Fever|2023-01-02|Allergies|Waiting|None"));
    }

    @Test
    void saveDoctorsData_emptyList_savesEmptyFile() throws IOException {
        // Execute method with empty list
        SaveData.saveDoctorsData(new ArrayList<>());

        // Verify file is empty except for header
        String content = Files.readString(Paths.get(SaveData.DOCTOR_FILE_PATH));
        String[] lines = content.split(System.lineSeparator());
        assertEquals(1, lines.length); // Only header line
    }

}
