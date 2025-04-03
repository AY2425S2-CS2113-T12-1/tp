package seedu.medinote.commands;

import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;

import java.util.List;

public class ViewDoctorAttributes {
    private static final String LINE_BREAK = "===============================================" +
            "===============================================";

    public static void printDoctorAttributes(String attribute) {
        List<Doctor> doctors = DoctorListManager.getDoctorList();
        attribute = attribute.trim().toLowerCase();

        if (doctors.isEmpty()) {
            System.out.println(LINE_BREAK);
            System.out.println("No doctors found in the system.");
            System.out.println(LINE_BREAK);
            return;
        }

        switch (attribute) {
        case "name":
            for (int i = 0; i < doctors.size(); i++) {
                System.out.println(LINE_BREAK);
                System.out.println((i + 1) + ". " + doctors.get(i).getName());
                System.out.println(LINE_BREAK);
            }
            break;
        case "specialisation":
            for (int i = 0; i < doctors.size(); i++) {
                System.out.println(LINE_BREAK);
                System.out.print((i + 1) + ". " + doctors.get(i).getName() + ": ");
                System.out.println(doctors.get(i).getSpecialisation());
                System.out.println(LINE_BREAK);
            }
            break;
        case "availability":
            for (int i = 0; i < doctors.size(); i++) {
                System.out.println(LINE_BREAK);
                System.out.print((i + 1) + ". " + doctors.get(i).getName() + ": ");
                System.out.println(doctors.get(i).getAvailability());
                System.out.println(LINE_BREAK);
            }
            break;
        case "patients":
            for (int i = 0; i < doctors.size(); i++) {
                System.out.println(LINE_BREAK);
                System.out.print((i + 1) + ". " + doctors.get(i).getName() + ": ");
                System.out.println(doctors.get(i).getPatientsBeingTreated());
                System.out.println(LINE_BREAK);
            }
            break;
        case "count":
            for (int i = 0; i < doctors.size(); i++) {
                System.out.println(LINE_BREAK);
                System.out.print((i + 1) + ". " + doctors.get(i).getName() + ": ");
                System.out.println(doctors.get(i).getNumPatientsTreated() + " number of patients treated");
                System.out.println(LINE_BREAK);
            }
            break;
        default:
            System.out.println(LINE_BREAK);
            System.out.print("Invalid Attribute Input. Available options: ");
            System.out.println("name, specialisation, availability, patients, count");
            System.out.println(LINE_BREAK);
        }
    }

}
