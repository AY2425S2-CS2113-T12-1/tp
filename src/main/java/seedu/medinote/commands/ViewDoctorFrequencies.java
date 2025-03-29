package seedu.medinote.commands;

import java.util.ArrayList;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;

public class ViewDoctorFrequencies {
    private static final ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();

    public static void viewMostFrequentSpecialisation() {
        ArrayList<String> specialisations = new ArrayList<>(); // arraylist of unique specialisations
        ArrayList<Integer> counts = new ArrayList<>(); // arraylist of counts corresponding to each specialization

        // counts through list of doctors
        for (Doctor doctor : doctorList) {
            String specialisation = doctor.getSpecialisation();
            int index = specialisations.indexOf(specialisation);

            if (index != -1) { //if specialisation exists, increment count
                counts.set(index, counts.get(index) + 1);
            } else { // new specialisation, add it to list and set count to 1
                specialisations.add(specialisation);
                counts.add(1);
            }
        }

        // finds highest frequency
        int maxCount = 0;
        for (int count : counts) {
            if (count > maxCount) {
                maxCount = count;
            }
        }

        // prints specialisations with the max frequency
        System.out.println("The type(s) of doctor most commonly present are: ");
        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i) == maxCount) {
                System.out.println(specialisations.get(i));
            }
        }
        System.out.println("Each of these specialisations had " + maxCount + " dcotors.");
    }

    public static void viewMostFrequentDoctor() {
        int maxPatients = 0;
        ArrayList<String> mostVisitedDoctors = new ArrayList<>();

        for(Doctor doctor : doctorList) {
            if(doctor.getNumPatientsTreated() > maxPatients) {
                maxPatients = doctor.getNumPatientsTreated();
                mostVisitedDoctors.clear();
            }
            if(doctor.getNumPatientsTreated() == maxPatients) {
                mostVisitedDoctors.add(doctor.getName());
            }
        }

        System.out.println("The doctor(s) with the most patients treated and currently treating are: ");

        for(String doctorName : mostVisitedDoctors) {
            System.out.println(doctorName);
        }

        System.out.println("These doctor(s) each have " + maxPatients + " total visits.");
    }
}
