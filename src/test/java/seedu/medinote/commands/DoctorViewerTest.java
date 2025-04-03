package seedu.medinote.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoctorViewerTest {
    private DoctorListManager doctorListManager;
    private DoctorViewer doctorViewer;

    @BeforeEach
    public void setUp() {
        doctorListManager = new DoctorListManager();
        doctorViewer = new DoctorViewer();
        doctorListManager.clearDoctorList();
    }

    @Test
    public void testViewDoctor_singleValidDoctor_successfulOutput() {
        DoctorListManager.addDoctor(new Doctor("Tim Cheese", "Cardiologist", "NA1", "NA1"));
        DoctorListManager.addDoctor(new Doctor("John Pork", "Optometrist", "NA2", "NA2"));

        String input = "John Pork";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertEquals(2, doctorListManager.getDoctorList().size());

        doctorViewer.viewDoctor(input);

        System.setOut(out);

        assertTrue(output.toString().contains("\tDetails of specified doctors:"));
        assertTrue(output.toString().contains("\t\t>Name: John Pork"));
        assertTrue(output.toString().contains("\t\t\t>Specialisation: Optometrist"));
        assertTrue(output.toString().contains("\t\t\t>Availability: NA2"));
        assertTrue(output.toString().contains("\t\t\t>Currently treating: NA2"));
    }

    @Test
    public void testViewDoctor_doubleValidDoctor_successfulOutput() {
        DoctorListManager.addDoctor(new Doctor("Tim Cheese", "Cardiologist", "NA1", "NA1"));
        DoctorListManager.addDoctor(new Doctor("John Pork", "Optometrist", "NA2", "NA2"));

        String input = "John Pork / Tim Cheese";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertEquals(2, doctorListManager.getDoctorList().size());

        doctorViewer.viewDoctor(input);

        System.setOut(out);

        assertTrue(output.toString().contains("\tDetails of specified doctors:"));
        assertTrue(output.toString().contains("\t\t>Name: John Pork"));
        assertTrue(output.toString().contains("\t\t\t>Specialisation: Optometrist"));
        assertTrue(output.toString().contains("\t\t\t>Availability: NA1"));
        assertTrue(output.toString().contains("\t\t\t>Currently treating: NA1"));

        assertTrue(output.toString().contains("\t\t>Name: Tim Cheese"));
        assertTrue(output.toString().contains("\t\t\t>Specialisation: Cardiologist"));
        assertTrue(output.toString().contains("\t\t\t>Availability: NA2"));
        assertTrue(output.toString().contains("\t\t\t>Currently treating: NA2"));
    }

    @Test
    public void testViewDoctor_emptyInput_emptyDoctorListOutput() {
        String input = "   ";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        doctorViewer.viewDoctor(input);

        System.setOut(out);

        String expectedOutput = "\tCurrently no viewable doctors! Use oncall to add a doctor.";

        assertTrue(output.toString().contains(expectedOutput));
    }

    @Test
    public void testViewDoctor_emptyInput_validDoctorListOutput() {
        DoctorListManager.addDoctor(new Doctor("Tim Cheese", "Cardiologist", "NA1", "NA1"));
        DoctorListManager.addDoctor(new Doctor("John Pork", "Optometrist", "NA2", "NA2"));

        String input = "   ";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertEquals(2, doctorListManager.getDoctorList().size());

        doctorViewer.viewDoctor(input);

        System.setOut(out);

        assertTrue(output.toString().contains("\tHere is a list of viewable doctors:"));
        assertTrue(output.toString().contains("\t\t> Tim Cheese"));
        assertTrue(output.toString().contains("\t\t> John Pork"));
        assertTrue(output.toString().contains("\tUse command format: doctor <NAME>."));
    }

    @Test
    public void testViewDoctor_invalidDoctor_errorMessageOutput() {
        DoctorListManager.addDoctor(new Doctor("Tim Cheese", "Cardiologist", "NA1", "NA1"));
        DoctorListManager.addDoctor(new Doctor("John Pork", "Optometrist", "NA2", "NA2"));

        String input = "John Pork / Simon Claw";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertEquals(2, doctorListManager.getDoctorList().size());

        doctorViewer.viewDoctor(input);

        System.setOut(out);

        assertTrue(output.toString().contains("\tDetails of specified doctors:"));
        assertTrue(output.toString().contains("\t\t>Name: John Pork"));
        assertTrue(output.toString().contains("\t\t\t>Specialisation: Optometrist"));
        assertTrue(output.toString().contains("\t\t\t>Availability: NA2"));
        assertTrue(output.toString().contains("\t\t\t>Currently treating: NA2"));

        assertTrue(output.toString().contains("\tCould not find doctors named:"));
        assertTrue(output.toString().contains("\t\t> Simon Claw"));
    }
}
