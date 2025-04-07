package seedu.medinote.person;

public class Doctor {
    private String name;
    private String specialisation;
    private String availability;
    private String patientBeingTreated;
    private Integer numPatientsTreated; // includes patients currently treating

    public Doctor(String name, String specialisation, String availability, String patientBeingTreated) {
        this.name = name;
        this.specialisation = specialisation;
        this.availability = availability;
        this.patientBeingTreated = patientBeingTreated;
        if (patientBeingTreated.trim().isEmpty() || patientBeingTreated.equalsIgnoreCase("na")) {
            this.numPatientsTreated = 0;
        } else {
            this.numPatientsTreated = 1;
        }
    }

    // Constructor used during loading from file
    public Doctor(String name, String specialisation, String availability,
                  String patientBeingTreated, String numPatientsTreatedStr) {
        this.name = name;
        this.specialisation = specialisation;
        this.availability = availability;
        this.patientBeingTreated = patientBeingTreated;

        try {
            this.numPatientsTreated = Integer.parseInt(numPatientsTreatedStr.trim());
        } catch (NumberFormatException e) {
            this.numPatientsTreated = 0; // Fallback in case of malformed number
        }
    }

    public String getName() {
        return name;
    }

    public void assignPatient(String name) {
        if (patientBeingTreated.trim().isEmpty() || patientBeingTreated.equalsIgnoreCase("none")
                || patientBeingTreated.equalsIgnoreCase("na")) {
            patientBeingTreated = name;
        } else if (!patientBeingTreated.contains(name)) {
            patientBeingTreated += ", " + name;
        }
        numPatientsTreated++;
    }


    public String getSpecialisation() {
        return specialisation;
    }

    public String getAvailability() {
        return availability;
    }

    public String getPatientBeingTreated() {
        if (patientBeingTreated == null || patientBeingTreated.trim().isEmpty()) {
            return "None";
        }
        return patientBeingTreated; // Remove the String.join() call
    }

    public Integer getNumPatientsTreated() {
        return numPatientsTreated;
    }


    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setCurrentPatient(String patientName) {
        patientBeingTreated = patientName.trim();
        if (!(patientName.trim().isEmpty() || patientName.equalsIgnoreCase("na"))) {
            numPatientsTreated++;
        }

    }

}
