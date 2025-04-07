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
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoadDataTest {
    private LoadData dataLoader;

    @BeforeEach
    void setUp() throws IOException {
        dataLoader = new LoadData();
        TestUtil.createTestFiles();
    }

    @AfterEach
    void tearDown() throws IOException {
        TestUtil.deleteTestFiles();
    }

    @Test
    void loadDoctorData_validData_returnsDoctorList() throws Exception {
        // Prepare test data
        String testData = "name|specialisation|availability" +
                "|patientsBeingTreated|numPatientsTreated\n" +
                "Dr. Smith|Cardiology|Available|John Doe|5\n" +
                "Dr. Lee|Neurology|Busy|Sarah Connor|10\n";

        Files.write(Paths.get(SaveData.DOCTOR_FILE_PATH),
                testData.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

        // Execute method
        ArrayList<Doctor> doctors = dataLoader.loadDoctorData();

        // Verify results
        assertEquals(2, doctors.size());
        assertEquals("Dr. Smith", doctors.get(0).getName());
        assertEquals("Cardiology", doctors.get(0).getSpecialisation());
        assertEquals("Available", doctors.get(0).getAvailability());
        assertEquals("John Doe", doctors.get(0).getPatientsBeingTreated());

        assertEquals("Dr. Lee", doctors.get(1).getName());
        assertEquals("Neurology", doctors.get(1).getSpecialisation());
    }

    @Test
    void loadPatientData_validData_returnsPatientList() throws Exception {
        // Prepare test data
        String testData = "name|symptoms|timeStamp|medicalHistory" +
                "|treatmentStatus|doctorAssigned\n" +
                "John Doe|Headache|2023-01-01|None|In Treatment|Dr. Smith\n" +
                "Sarah Connor|Fever|2023-01-02|Allergies|Waiting|None\n";

        Files.write(Paths.get(SaveData.PATIENT_FILE_PATH), testData.getBytes(),
                StandardOpenOption.TRUNCATE_EXISTING);

        // Execute method
        ArrayList<Patient> patients = dataLoader.loadPatientData();

        // Verify results
        assertEquals(2, patients.size());
        assertEquals("John Doe", patients.get(0).getName());
        assertEquals("Headache", patients.get(0).getSymptoms());
        assertEquals("2023-01-01", patients.get(0).getTimeStamp());
        assertEquals("In Treatment", patients.get(0).getTreatmentStatus());

        assertEquals("Sarah Connor", patients.get(1).getName());
        assertEquals("Fever", patients.get(1).getSymptoms());
        assertEquals("Waiting", patients.get(1).getTreatmentStatus());
    }

    @Test
    void loadDoctorData_invalidFormat_throwsDataFormatException() throws IOException {
        // Prepare invalid test data
        String testData = "name|specialisation|availability" +
                "|patientsBeingTreated\n" + // Missing numPatientsTreated
                "Dr. Smith|Cardiology|Available|John Doe\n";

        Files.write(Paths.get(SaveData.DOCTOR_FILE_PATH), testData.getBytes(),
                StandardOpenOption.TRUNCATE_EXISTING);

        // Execute and verify exception
        assertThrows(LoadData.DataFormatException.class, () -> dataLoader.loadDoctorData());
    }

    @Test
    void loadPatientData_emptyFile_returnsEmptyList() throws Exception {
        // Ensure file is empty
        Files.write(Paths.get(SaveData.PATIENT_FILE_PATH), "".getBytes(),
                StandardOpenOption.TRUNCATE_EXISTING);

        // Execute method
        ArrayList<Patient> patients = dataLoader.loadPatientData();

        // Verify results
        assertTrue(patients.isEmpty());
    }

}
