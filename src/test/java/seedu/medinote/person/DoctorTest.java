package seedu.medinote.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoctorTest {

    @Test
    public void getAttributes_validAttributes_success() {
        Doctor testDoctor = new Doctor("Doctor Test", "Specialisation Test", "Availability Test", "Patients Test");

        assertEquals("Doctor Test", testDoctor.getName());
        assertEquals("Specialisation Test", testDoctor.getSpecialisation());
        assertEquals("Availability Test", testDoctor.getAvailability());
        assertEquals("Patients Test", testDoctor.getPatientsBeingTreated());
    }

    @Test
    public void setAvailability_notAvailable_success() {
        Doctor testDoctor = new Doctor("NA", "NA", "NA", "NA");
        assertEquals("NA", testDoctor.getAvailability());
        testDoctor.setAvailability("Not Available");
        assertEquals("Not Available", testDoctor.getAvailability());
    }

    @Test
    public void setCurrentPatient_newCurrentPatient_success() {
        Doctor testDoctor = new Doctor("NA", "NA", "NA", "NA");
        assertEquals("NA", testDoctor.getPatientsBeingTreated());
        testDoctor.setCurrentPatient("Siqiang");
        assertEquals("Siqiang", testDoctor.getPatientsBeingTreated());
    }

}
