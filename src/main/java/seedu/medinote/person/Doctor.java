package seedu.medinote.person;

public class Doctor {
    private String name;
    private String specialisation;
    private String availability;
    private String patientsBeingTreated;

    public Doctor(String name, String specialisation, String availability, String patientsBeingTreated) {
        this.name = name;
        this.specialisation = specialisation;
        this.availability = availability;
        this.patientsBeingTreated = patientsBeingTreated;
    }

    public String getName() {
        return name;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public String getAvailability() {
        return availability;
    }

    public String getPatientsBeingTreated() {
        return patientsBeingTreated;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setCurrentPatient(String patientName) {
        this.patientsBeingTreated = patientName;
    }

}
