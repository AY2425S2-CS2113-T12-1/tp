package seedu.medinote.commands;

import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;
import seedu.medinote.person.Patient;
import seedu.medinote.manager.PatientListManager;
import seedu.medinote.storage.SaveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PatientUpdater {

    // Parses and executes the update patient command.
    // Expected format: update patient <NAME> <field=value field=value ...>
    public static void updatePatient(String input) {

        String prefix = "update patient ";
        if (!input.toLowerCase().startsWith(prefix)) {
            System.out.println("\nInvalid command. Use format: update patient <name> <field=value>...");
            return;
        }

        String nameAndRest = input.substring(prefix.length()).trim();

        if (nameAndRest.isBlank()) {
            System.out.println("\nMissing arguments. Use format: update patient <name> <field=value>...");
            return;
        }

        // Extract full name and update fields from remainder
        String[] split = nameAndRest.split(" ");
        StringBuilder nameBuilder = new StringBuilder();
        int i = 0;

        while (i < split.length && !split[i].contains("=")) {
            nameBuilder.append(split[i]).append(" ");
            i++;
        }

        String name = nameBuilder.toString().trim();
        StringBuilder updatesBuilder = new StringBuilder();

        while (i < split.length) {
            updatesBuilder.append(split[i]).append(" ");
            i++;
        }

        String updatesString = updatesBuilder.toString().trim();

        if (name.isEmpty() || updatesString.isEmpty()) {
            System.out.println("\nPlease provide patient name and at least one attribute to update.");
            return;
        }

        Patient target = findPatientByName(name);
        if (target == null) {
            System.out.println("\nPatient \"" + name + "\" not found. If the name contains spaces," +
                    " try using a hyphen like \"John-Tan\".");
            return;
        }

        // Parse updates like "status=In-Progress doctor=Dr-Lim"
        HashMap<String, String> updates = parseKeyValuePairs(updatesString);
        if (updates.isEmpty()) {
            System.out.println("\nNo valid updates provided. Use format: status=... doctor=...");
            return;
        }

        boolean updated = false;

        for (String key : updates.keySet()) {
            String value = updates.get(key);
            switch (key.toLowerCase()) {
            case "status":
                target.setTreatmentStatus(value);
                updated = true;
                break;
            case "doctor":
                target.setDoctorAssigned(value);
                Doctor linkedDoctor = findDoctorByName(value);
                if (linkedDoctor != null) {
                    String actualPatientName = target.getName().replaceFirst("(?i)^patient\s+",
                            "").trim();
                    linkedDoctor.assignPatient(actualPatientName);
                }
                updated = true;
                break;
            default:
                System.out.println("\nUnknown attribute: " + key);
            }
        }

        //Sends the changes to file if anything was updated.
        if (updated) {
            System.out.println("\nPatient \"" + name + "\" updated successfully.");
            try {
                new SaveData().savePatientsData(PatientListManager.getPatientList());
            } catch (IOException e) {
                System.out.println("\nFailed to save updated patient data.");
            }
        } else {
            System.out.println("\nNo valid updates applied.");
        }
    }

    static Doctor findDoctorByName(String name) {
        ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();
        for (Doctor d : doctorList) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }

    static Patient findPatientByName(String inputName) {
        ArrayList<Patient> patientList = PatientListManager.getPatientList();
        for (Patient p : patientList) {
            String storedName = p.getName().trim();
            // Strip "patient " prefix if present
            if (storedName.toLowerCase().startsWith("patient ")) {
                storedName = storedName.substring(8);  // remove 8 characters
            }

            if (storedName.equalsIgnoreCase(inputName)) {
                return p;
            }
        }
        return null;
    }

    //Parses a space-separated string of field=value pairs into a HashMap.
    //Only valid pairs with '=' are included.
    static HashMap<String, String> parseKeyValuePairs(String input) {
        HashMap<String, String> map = new HashMap<>();
        String[] pairs = input.split(" ");

        for (String pair : pairs) {
            String[] parts = pair.split("=", 2);
            map.put(parts[0].trim(), parts[1].trim());
        }

        return map;
    }

}
