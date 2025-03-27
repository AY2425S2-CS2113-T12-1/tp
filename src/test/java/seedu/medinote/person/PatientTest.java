package seedu.medinote.person;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientTest {

    @Test
    public void getAttributes_validAttributes_success() {
        Patient testPatient =
                new Patient("test", "test", "test", "test", "test", "test");

        assertEquals("test", testPatient.getName());
        assertEquals("test", testPatient.getSymptoms());
        assertEquals("test", testPatient.getTimeStamp());
        assertEquals("test", testPatient.getMedicalHistory());
        assertEquals("test", testPatient.getTreatmentStatus());
        assertEquals("test", testPatient.getDoctorAssigned());
    }

    @Test
    public void setTreatmentStatus_newTreatmentStatus_success() {
        Patient testPatient =
                new Patient("NA", "NA", "NA", "NA", "NA", "NA");

        assertEquals("NA", testPatient.getTreatmentStatus());
        testPatient.setTreatmentStatus("Undergoing Treatment");
        assertEquals("Undergoing Treatment", testPatient.getTreatmentStatus());
    }

    @Test
    public void setDoctorAssigned_newAssignedDoctor_success() {
        Patient testPatient =
                new Patient("NA", "NA", "NA", "NA", "NA", "NA");

        assertEquals("NA", testPatient.getDoctorAssigned());
        testPatient.setDoctorAssigned("Siqiang");
        assertEquals("Siqiang", testPatient.getDoctorAssigned());
    }

}
