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
    private PrintStream originalOut;
    private ByteArrayOutputStream systemOutput;

    @BeforeEach
    public void setUp() {
        DoctorListManager doctorListManager = new DoctorListManager();
        DeleteDoctor doctorDeleter = new DeleteDoctor();
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
        DoctorListManager.addDoctor(new Doctor("Mike",
                "Heart", "NA", "NA"));
        DoctorListManager.addDoctor(new Doctor("John",
                "Foot", "Busy", "NA"));
        assertEquals(2, DoctorListManager.getDoctorList().size());

        // delete doctor1, verify
        DeleteDoctor.deleteDoctor("Mike");
        assertEquals(1, DoctorListManager.getDoctorList().size());
        assertEquals("John", DoctorListManager.getDoctorList().get(0).getName());

        // delete doctor 2, verify
        DeleteDoctor.deleteDoctor("John");
        assertEquals(0, DoctorListManager.getDoctorList().size());
    }

    @Test
    public void testDeleteDoctor_emptyDoctorName_unsuccessfulDeletion() {
        DeleteDoctor.deleteDoctor("");

        // verify with expected output
        String expectedOutput = "Please provide a doctor name and try again.";
        assertTrue(systemOutput.toString().contains(expectedOutput));
    }

    @Test
    public void testDeleteDoctor_nameNotFound_unsuccessfulDeletion() {
        DoctorListManager.addDoctor(new Doctor("Bob",
                "Lungs", "NA", "NA"));
        DeleteDoctor.deleteDoctor("John");

        // verify with expected output and check "Mike" is still in doctor list
        String expectedOutput = "Doctor with name \"John\" was not found";
        assertTrue(systemOutput.toString().contains(expectedOutput));
        assertEquals(1, DoctorListManager.getDoctorList().size());
        assertEquals("Bob", DoctorListManager.getDoctorList().get(0).getName());

        // delete added doctor since doctorList is static so will impact other test asserts
        DeleteDoctor.deleteDoctor("Bob");
    }

}
