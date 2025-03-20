package seedu.medinote.person;

public class Patient {
    private String name;
    private String symptoms;
    private String timeStamp;
    private String medicalHistory;
    private String treatmentStatus;
    private String doctorAssigned;

    public Patient(String name, String symptoms, String timeStamp,
                   String medicalHistory, String treatmentStatus, String doctorAssigned) {
        this.name = name;
        this.symptoms = symptoms;
        this.timeStamp = timeStamp;
        this.medicalHistory = medicalHistory;
        this.treatmentStatus = treatmentStatus;
        this.doctorAssigned = doctorAssigned;
    }

    public String getName() {
        return name;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public String getTreatmentStatus() {
        return treatmentStatus;
    }

    public String getDoctorAssigned() {
        return doctorAssigned;
    }

    public void setTreatmentStatus(String treatmentStatus) {
        this.treatmentStatus = treatmentStatus;
    }

    public void setDoctorAssigned(String doctorAssigned) {
        this.doctorAssigned = doctorAssigned;
    }
}
