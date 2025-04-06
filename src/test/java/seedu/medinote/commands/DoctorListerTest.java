package seedu.medinote.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.medinote.person.Doctor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class DoctorListerTest {
    private static final String TESTLISTALLDOCTORS_TWODOCTORS_EXPECTED =
            "================================" + System.lineSeparator()
            + "You have 2 doctor(s) in hospital" + System.lineSeparator()
            + "1. Roger:" + System.lineSeparator()
            + "\t>Specialisation: Cardio" + System.lineSeparator()
            + "\t>Availability: NA" + System.lineSeparator()
            + "\t>Currently treating: NA" + System.lineSeparator() + System.lineSeparator()
            + "2. Stark:" + System.lineSeparator()
            + "\t>Specialisation: Eye" + System.lineSeparator()
            + "\t>Availability: Busy" + System.lineSeparator()
            + "\t>Currently treating: NA" + System.lineSeparator() + System.lineSeparator()
            + "================================" + System.lineSeparator();
    private static final String TESTLISTALLDOCTORS_NODOCTORS_EXPECTED =
            "================================" + System.lineSeparator()
            + "You have 0 doctor(s) in hospital" + System.lineSeparator()
            + "================================" + System.lineSeparator();

    private static final ByteArrayOutputStream outputByteStream = new ByteArrayOutputStream();

    @BeforeAll
    public static void setup() {
        System.setOut(new PrintStream(outputByteStream));
        outputByteStream.reset();
    }

    @Test
    public void testListAllDoctors_twoDoctors_successfulList() {
        ArrayList<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor("Roger", "Cardio", "NA", "NA"));
        doctorList.add(new Doctor("Stark", "Eye", "Busy", "NA"));

        DoctorLister doctorLister = new DoctorLister();
        doctorLister.listAllDoctors(doctorList);

        System.setOut(System.out);

        assertTrue(outputByteStream.toString().contains(TESTLISTALLDOCTORS_TWODOCTORS_EXPECTED));
    }

    @Test
    public void testListAllDoctors_noDoctors_unsuccessfulList() {
        ArrayList<Doctor> doctorList = new ArrayList<>();

        DoctorLister doctorLister = new DoctorLister();
        doctorLister.listAllDoctors(doctorList);

        System.setOut(System.out);

        assertTrue(outputByteStream.toString().contains(TESTLISTALLDOCTORS_NODOCTORS_EXPECTED));
    }

}
