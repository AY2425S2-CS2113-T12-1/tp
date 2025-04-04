package seedu.medinote.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import seedu.medinote.manager.PatientListManager;
import seedu.medinote.person.Patient;

public class DischargePatientTest {
    private PatientListManager patientListManager;
    private DischargePatient patientDischarger;
    private PrintStream originalOut;
    private ByteArrayOutputStream systemOutput;

    @BeforeEach
    public void setUp() {
        patientListManager = new PatientListManager();
        patientDischarger = new DischargePatient();
        originalOut = System.out;
        systemOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutput));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testDischargePatient_twoPatients_successfulDischarges() {
        // create and add to patient list, verify
        patientListManager.addPatient(new Patient("Mike",
                "cough", "12:15", "NA", "NA", "NA"));
        patientListManager.addPatient(new Patient("John",
                "headache", "15:38", "NA", "NA", "NA"));
        assertEquals(2, patientListManager.getPatientList().size());

        // delete doctor1, verify
        patientDischarger.dischargePatient("Mike");
        assertEquals(1, patientListManager.getPatientList().size());
        assertEquals("John", patientListManager.getPatientList().get(0).getName());

        // delete doctor 2, verify
        patientDischarger.dischargePatient("John");
        assertEquals(0, patientListManager.getPatientList().size());
    }

    @Test
    public void testDischargePatient_emptyPatientName_unsuccessfulDischarge() {
        patientDischarger.dischargePatient("");

        // verify with expected output
        String expectedOutput = "Please provide a patient name and try again.";
        assertTrue(systemOutput.toString().contains(expectedOutput));
    }

    @Test
    public void testDischargePatient_nameNotFound_unsuccessfulDischarge() {
        patientListManager.addPatient(new Patient("Bob",
                "Lungs", "NA", "NA", "NA", "NA"));
        patientDischarger.dischargePatient("John");

        // verify with expected output and check "Mike" is still in doctor list
        String expectedOutput = "Patient with name \"John\" was not found";
        assertTrue(systemOutput.toString().contains(expectedOutput));
        assertEquals(1, patientListManager.getPatientList().size());
        assertEquals("Bob", patientListManager.getPatientList().get(0).getName());

        // delete added doctor since doctorList is static so will impact other test asserts
        patientDischarger.dischargePatient("Bob");
    }

}
