package seedu.medinote.commands;

import seedu.medinote.person.Doctor;
import seedu.medinote.manager.DoctorListManager;

import java.util.ArrayList;

public class ViewDoctorAttributes {

    private static final String LINE_BREAK = "==================================================";

    // Prints each doctor's attribute value for the requested attribute
    // Accepted attributes: name, specialization, availability, treating
    public static void viewDoctorAttribute(String input) {
        String[] parts = input.trim().split(" ", 3);

        if (parts.length < 3) {
            System.out.println(LINE_BREAK);
            System.out.println("Please specify an attribute to view. Usage: view doctor <attribute>");
            System.out.println(LINE_BREAK);
            return;
        }

        String attribute = parts[2].toLowerCase();
        ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();

        if (doctorList.isEmpty()) {
            System.out.println(LINE_BREAK);
            System.out.println("No doctors available to view.");
            System.out.println(LINE_BREAK);
            return;
        }

        System.out.println(LINE_BREAK);
        System.out.println("Viewing attribute: " + attribute);
        System.out.println(LINE_BREAK);

        for (Doctor d : doctorList) {
            String label = d.getName();
            String result;

            switch (attribute) {
                case "name":
                    result = d.getName();
                    break;
                case "specialization":
                    result = d.getSpecialisation();
                    break;
                case "availability":
                    result = d.getAvailability();
                    break;
                case "treating":
                    result = d.getPatientsBeingTreated();
                    break;
                default:
                    System.out.println(LINE_BREAK);
                    System.out.println("Unknown attribute: " + attribute);
                    System.out.println(LINE_BREAK);
                    return;
            }

            System.out.println(label + ": " + result);
        }

        System.out.println(LINE_BREAK);
    }
}
