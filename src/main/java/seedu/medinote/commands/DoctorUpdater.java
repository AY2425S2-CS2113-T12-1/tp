package seedu.medinote.commands;

import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;
import seedu.medinote.storage.SaveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DoctorUpdater {

    // Parses and executes the update doctor command.
    // Expected format: update doctor <NAME> <field=value field=value ...>
    public static void updateDoctor(String input) {

        String prefix = "update doctor ";
        if (!input.toLowerCase().startsWith(prefix)) {
            System.out.println("\nInvalid command. Use format: update doctor <name> <field=value>...");
            return;
        }

        String nameAndRest = input.substring(prefix.length()).trim();

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
            System.out.println("\nPlease provide doctor name and at least one attribute to update.");
            return;
        }

        Doctor target = findDoctorByName(name);
        if (target == null) {
            System.out.println("\nDoctor \"" + name + "\" not found. If the name contains spaces, try using a hyphen " +
                    "like \"Dr-Lim\".");
            return;
        }

        // Parse updates like "availability=Busy treating=Mr-A"
        HashMap<String, String> updates = parseKeyValuePairs(updatesString);
        if (updates.isEmpty()) {
            System.out.println("\nNo valid updates provided. Use format: availability=... treating=...");
            return;
        }

        boolean updated = false;

        for (String key : updates.keySet()) {
            String value = updates.get(key);
            switch (key.toLowerCase()) {
            case "availability":
                target.setAvailability(value);
                updated = true;
                break;
            case "treating":
                target.assignPatient(value);
                updated = true;
                break;
            default:
                System.out.println("\nUnknown attribute: " + key);
            }
        }

        //Sends the changes to file if anything was updated.
        if (updated) {
            System.out.println("\nDoctor \"" + name + "\" updated successfully.");
            try {
                SaveData.saveDoctorsData(DoctorListManager.getDoctorList());
            } catch (IOException e) {
                System.out.println("\nFailed to save updated doctor data.");
            }
        } else {
            System.out.println("\nNo valid updates applied.");
        }
    }

    private static Doctor findDoctorByName(String name) {
        ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();
        for (Doctor d : doctorList) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }

    //Parses a space-separated string of field=value pairs into a HashMap.
    //Only valid pairs with '=' are included.
    private static HashMap<String, String> parseKeyValuePairs(String input) {
        HashMap<String, String> map = new HashMap<>();
        String[] pairs = input.split(" ");

        for (String pair : pairs) {
            String[] parts = pair.split("=", 2);
            map.put(parts[0].trim(), parts[1].trim());
        }

        return map;
    }

}
