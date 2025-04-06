package seedu.medinote.commands;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterPatientTest {

    @Test
    public void testRegisterPatient_correctNewPatient_successfulRegistration() {
        String input = "Patient 1 / cough / 13:42 / peanut allergy";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        RegisterPatient regPatient = new RegisterPatient();
        regPatient.registerPatient(input);

        System.setOut(out);

        String expectedOutput = "\tPatient Patient 1 has been successfully registered as a patient!";

        assertTrue(output.toString().contains(expectedOutput));
    }


    @Test
    public void testRegisterPatient_emptyInput_unsuccessfulRegistration() {
        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        RegisterPatient regPatient = new RegisterPatient();
        regPatient.registerPatient("");

        System.setOut(out);

        String expectedOutput =
                "\tExpected Format: register <name> / <symptoms> / <admission time> / <medical history>";

        assertTrue(output.toString().contains(expectedOutput));
    }

    @Test
    public void testRegisterPatient_insufficientInputs_unsuccessfulRegistration() {
        String input = "Patient 1 / cough ";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        RegisterPatient regPatient = new RegisterPatient();
        regPatient.registerPatient(input);

        System.setOut(out);

        String expectedOutput =
                "\tCompulsory patient details are incomplete." + "\n"
                + "\tExpected Format: register <name> / <symptoms> / <admission time> / <medical history>";

        assertTrue(output.toString().contains(expectedOutput));
    }

}
