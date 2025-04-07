package seedu.medinote.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoctorUpdaterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(outContent));

        // Clear the existing list
        ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();
        doctorList.clear();

        // Add mock doctors using constructor only
        doctorList.add(new Doctor("Dr Lim", "Cardiologist", "Available",
                ""));
    }

    @Test
    void updateDoctor_updatesAvailability() {
        DoctorUpdater.updateDoctor("Dr Lim / availability=Busy");
        Doctor doctor = DoctorListManager.getDoctorList().get(0);
        assertEquals("busy", doctor.getAvailability());
    }


    @Test
    void updateDoctor_invalidField_warnsUser() {
        DoctorUpdater.updateDoctor("Dr Lim / unknown=field");
        assertTrue(outContent.toString().contains("\tIncorrect attribute specified!"));
    }

    @Test
    void updateDoctor_notFound_warnsUser() {
        DoctorUpdater.updateDoctor("Dr Ghost / availability=Available");
        assertTrue(outContent.toString().contains("\tDoctor specified does not exist!"));
    }

}
