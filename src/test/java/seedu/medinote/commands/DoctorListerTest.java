package seedu.medinote.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.medinote.person.Doctor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class DoctorListerTest {
    private static final String TESTLISTALLDOCTORS_TWODOCTORS_CORRECTLIST_EXPECTED =
            "================================" + System.lineSeparator()
            + "You have 2 doctor(s) in hospital" + System.lineSeparator()
            + "1. Roger:" + System.lineSeparator()
            + ""

    private static final ByteArrayOutputStream outputByteStream = new ByteArrayOutputStream();

    @BeforeAll
    public static void setup() {
        System.setOut(new PrintStream(outputByteStream));
        outputByteStream.reset();
    }

    @Test
    public void testListAllDoctors_twoDoctors_correctList() {
        ArrayList<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor("Roger", "Cardio", "NA", "NA"));
        doctorList.add(new Doctor("Stark", "Eye", "Busy", "NA"));

        DoctorLister doctorLister = new DoctorLister();
        doctorLister.listAllDoctors(doctorList);

        System.setOut(System.out);


    }
}
