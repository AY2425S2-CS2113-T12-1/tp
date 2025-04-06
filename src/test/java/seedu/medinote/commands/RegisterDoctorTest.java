package seedu.medinote.commands;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterDoctorTest {

    @Test
    public void testRegisterDoctor_correctNewDoctor_successfulRegistration() {
        String input = "Doctor 1 / Cardio";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        RegisterDoctor regDoctor = new RegisterDoctor();
        regDoctor.registerDoctor(input);

        System.setOut(out);

        String expectedOutput = "\tDoctor Doctor 1 has been successfully registered as a doctor!";

        assertTrue(output.toString().contains(expectedOutput));
    }


    @Test
    public void testRegisterDoctor_emptyInput_unsuccessfulRegistration() {
        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        RegisterDoctor regDoctor = new RegisterDoctor();
        regDoctor.registerDoctor("");

        System.setOut(out);

        String expectedOutput =
                "\tExpected Format: oncall <name> / <specialisation>";

        assertTrue(output.toString().contains(expectedOutput));
    }

    @Test
    public void testRegisterDoctor_insufficientInputs_unsuccessfulRegistration() {
        String input = "Doctor 1 /";

        PrintStream out = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        RegisterDoctor regDoctor = new RegisterDoctor();
        regDoctor.registerDoctor(input);

        System.setOut(out);

        String expectedOutput = "\tCompulsory doctor details are incomplete." + "\n"
                                + "\tExpected Format: oncall <name> / <specialisation>";

        assertTrue(output.toString().contains(expectedOutput));
    }

}
