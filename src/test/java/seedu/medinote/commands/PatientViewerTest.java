package seedu.medinote.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.medinote.manager.PatientListManager;
import seedu.medinote.person.Patient;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PatientViewerTest {
    private PatientListManager patientListManager;
    private PatientViewer patientViewer;

    @BeforeEach
    public void setUp() {
        patientListManager = new PatientListManager();
        patientViewer = new PatientViewer();
        patientListManager.clearPatientList();
    }

    @Test
    public void testViewPatient_singleValidPatient_successfulOutput() {
        PatientListManager.addPatient(new Patient("Simon Claw", "High Fever", "5 Jan 530pm",
                "Nut allergy", "NA1", "NA1"));
        PatientListManager.addPatient(new Patient("Agent 55", "Bronchitis", "8 April 640am",
                "NA2", "NA2", "NA2"));

        String input = "Simon Claw";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertEquals(2, patientListManager.getPatientList().size());

        patientViewer.viewPatient(input);

        System.setOut(out);

        assertTrue(output.toString().contains("\tDetails of specified patients:"));
        assertTrue(output.toString().contains("\t\t>Name: Simon Claw"));
        assertTrue(output.toString().contains("\t\t\t>Symptoms: High Fever"));
        assertTrue(output.toString().contains("\t\t\t>Time Stamp: 5 Jan 530pm"));
        assertTrue(output.toString().contains("\t\t\t>Medical History: Nut allergy"));
        assertTrue(output.toString().contains("\t\t\t>Treatment Status: NA1"));
        assertTrue(output.toString().contains("\t\t\t>Doctor Assigned: NA1"));
    }

    @Test
    public void testViewPatient_doubleValidPatient_successfulOutput() {
        PatientListManager.addPatient(new Patient("Simon Claw", "High Fever", "5 Jan 530pm",
                "Nut allergy", "NA1", "NA1"));
        PatientListManager.addPatient(new Patient("Agent 55", "Bronchitis", "8 April 640am",
                "NA2", "NA2", "NA2"));

        String input = "Simon Claw / Agent 55";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertEquals(2, patientListManager.getPatientList().size());

        patientViewer.viewPatient(input);

        System.setOut(out);

        assertTrue(output.toString().contains("\tDetails of specified patients:"));
        assertTrue(output.toString().contains("\t\t>Name: Simon Claw"));
        assertTrue(output.toString().contains("\t\t\t>Symptoms: High Fever"));
        assertTrue(output.toString().contains("\t\t\t>Time Stamp: 5 Jan 530pm"));
        assertTrue(output.toString().contains("\t\t\t>Medical History: Nut allergy"));
        assertTrue(output.toString().contains("\t\t\t>Treatment Status: NA1"));
        assertTrue(output.toString().contains("\t\t\t>Doctor Assigned: NA1"));

        assertTrue(output.toString().contains("\t\t>Name: Agent 55"));
        assertTrue(output.toString().contains("\t\t\t>Symptoms: Bronchitis"));
        assertTrue(output.toString().contains("\t\t\t>Time Stamp: 8 April 640am"));
        assertTrue(output.toString().contains("\t\t\t>Medical History: NA2"));
        assertTrue(output.toString().contains("\t\t\t>Treatment Status: NA2"));
        assertTrue(output.toString().contains("\t\t\t>Doctor Assigned: NA2"));
    }

    @Test
    public void testViewPatient_emptyInput_emptyPatientListOutput() {
        String input = "   ";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        patientViewer.viewPatient(input);

        System.setOut(out);

        String expectedOutput = "\tCurrently no viewable patients! Use register to add a patient.";

        assertTrue(output.toString().contains(expectedOutput));
    }

    @Test
    public void testViewPatient_emptyInput_validPatientListOutput() {
        PatientListManager.addPatient(new Patient("Simon Claw", "High Fever", "5 Jan 530pm",
                "Nut allergy", "NA1", "NA1"));
        PatientListManager.addPatient(new Patient("Agent 55", "Bronchitis", "8 April 640am",
                "NA2", "NA2", "NA2"));

        String input = "   ";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertEquals(2, patientListManager.getPatientList().size());

        patientViewer.viewPatient(input);

        System.setOut(out);

        assertTrue(output.toString().contains("\tHere is a list of viewable patients:"));
        assertTrue(output.toString().contains("\t\t> Simon Claw"));
        assertTrue(output.toString().contains("\t\t> Agent 55"));
        assertTrue(output.toString().contains("\tUse command format: patient <NAME>."));
    }

    @Test
    public void testViewPatient_invalidPatient_errorMessageOutput() {
        PatientListManager.addPatient(new Patient("Simon Claw", "High Fever", "5 Jan 530pm",
                "Nut allergy", "NA1", "NA1"));
        PatientListManager.addPatient(new Patient("Agent 55", "Bronchitis", "8 April 640am",
                "NA2", "NA2", "NA2"));

        String input = "Simon Claw / John Pork";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertEquals(2, patientListManager.getPatientList().size());

        patientViewer.viewPatient(input);

        System.setOut(out);

        assertTrue(output.toString().contains("\tDetails of specified patients:"));
        assertTrue(output.toString().contains("\t\t>Name: Simon Claw"));
        assertTrue(output.toString().contains("\t\t\t>Symptoms: High Fever"));
        assertTrue(output.toString().contains("\t\t\t>Time Stamp: 5 Jan 530pm"));
        assertTrue(output.toString().contains("\t\t\t>Medical History: Nut allergy"));
        assertTrue(output.toString().contains("\t\t\t>Treatment Status: NA1"));
        assertTrue(output.toString().contains("\t\t\t>Doctor Assigned: NA1"));

        assertTrue(output.toString().contains("\tCould not find patients named:"));
        assertTrue(output.toString().contains("\t\t> John Pork"));
    }
}
