package seedu.duke;

public class Registration {

    public static void registerPatient(String patientDetails) {
        try {
            String[] patientInfo = patientDetails.split("/");

            for (String info : patientInfo) {
                if (info.trim().isEmpty()) {
                    throw new IllegalArgumentException("Compulsory patient details are empty. Please re-enter.");
                }
            }

            if (patientInfo.length == 4) {
                Patient patient = new Patient(patientInfo[0].trim(), patientInfo[1].trim(),
                        patientInfo[2].trim(), patientInfo[3].trim(), "NA", "NA");
                PatientListManager.addPatient(patient);
                System.out.println("Patient " + patientInfo[0].trim() + " successfully registered as patient!");
            } else {
                throw new IllegalArgumentException("Compulsory patient details are not entered.\n" +
                        "Expected Format: reigster name/symptoms/timestamp/medical history. Please re-enter.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void registerDoctor(String doctorDetails) {
        try {
            String[] doctorInfo = doctorDetails.split("/");

            for (String info : doctorInfo) {
                if (info.trim().isEmpty()) {
                    throw new IllegalArgumentException("Compulsory doctor details are empty. Please re-enter.");
                }
            }

            if (doctorInfo.length == 2) {
                Doctor doctor = new Doctor (doctorInfo[0].trim(), doctorInfo[1].trim(), "NA", "NA");
                DoctorListManager.addDoctor(doctor);
                System.out.println("Doctor " + doctorInfo[0].trim() + " successfully registered as doctor!");
            } else {
                throw new IllegalArgumentException("Compulsory doctor details are not entered.\n" +
                        "Expected Format: oncall name/specialisation. Please re-enter.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
