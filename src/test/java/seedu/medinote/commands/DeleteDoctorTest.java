package seedu.medinote.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;

public class DeleteDoctorTest {
    private DoctorListManager doctorListManager;
    private DeleteDoctor doctorDeleter;
    private PrintStream originalOut;
    private ByteArrayOutputStream systemOutput;

    @BeforeEach
    public void setUp() {
        doctorListManager = new DoctorListManager();
        doctorDeleter = new DeleteDoctor();
        originalOut = System.out;
        systemOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutput));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testDeleteDoctor_twoDoctors_successfulDeletions() {
        // create and add to doctor list, verify
        doctorListManager.addDoctor(new Doctor("Mike",
                "Heart", "NA", "NA"));
        doctorListManager.addDoctor(new Doctor("John",
                "Foot", "Busy", "NA"));
        assertEquals(2, doctorListManager.getDoctorList().size());

        // delete doctor1, verify
        doctorDeleter.deleteDoctor("Mike");
        assertEquals(1, doctorListManager.getDoctorList().size());
        assertEquals("John", doctorListManager.getDoctorList().get(0).getName());

        // delete doctor 2, verify
        doctorDeleter.deleteDoctor("John");
        assertEquals(0, doctorListManager.getDoctorList().size());
    }

    @Test
    public void testDeleteDoctor_emptyDoctorName_unsuccessfulDeletion() {
        doctorDeleter.deleteDoctor("");

        // verify with expected output
        String expectedOutput = "Please provide a doctor name and try again.";
        assertTrue(systemOutput.toString().contains(expectedOutput));
    }

    @Test
    public void testDeleteDoctor_nameNotFound_unsuccessfulDeletion() {
        doctorListManager.addDoctor(new Doctor("Bob",
                "Lungs", "NA", "NA"));
        doctorDeleter.deleteDoctor("John");

        // verify with expected output and check "Mike" is still in doctor list
        String expectedOutput = "Doctor with name \"John\" was not found";
        assertTrue(systemOutput.toString().contains(expectedOutput));
        assertEquals(1, doctorListManager.getDoctorList().size());
        assertEquals("Bob", doctorListManager.getDoctorList().get(0).getName());

        // delete added doctor since doctorList is static so will impact other test asserts
        doctorDeleter.deleteDoctor("Bob");
    }

}
