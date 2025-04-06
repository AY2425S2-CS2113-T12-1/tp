package seedu.medinote.commands;

import java.util.ArrayList;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;

public class ViewDoctorFrequencies {
    private static final ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();

    public static void viewMostFrequentSpecialisation() {
        assert doctorList != null: "Doctor list should not be null.";
        assert (!doctorList.isEmpty()): "Doctor list should not be empty.";

        ArrayList<String> specialisations = new ArrayList<>(); // arraylist of unique specialisations
        ArrayList<Integer> counts = new ArrayList<>(); // arraylist of counts corresponding to each specialization

        // counts through list of doctors
        for (Doctor doctor : doctorList) {
            assert doctor != null : "Doctor object should not be null.";
            String specialisation = doctor.getSpecialisation();
            assert specialisation != null : "Specialisation should not be null.";

            int index = specialisations.indexOf(specialisation);

            if (index != -1) { //if specialisation exists, increment count
                counts.set(index, counts.get(index) + 1);
            } else { // new specialisation, add it to list and set count to 1
                specialisations.add(specialisation);
                counts.add(1);
            }
        }

        assert !counts.isEmpty() : "Counts list should not be empty.";

        // finds highest frequency
        int maxCount = 0;
        for (int count : counts) {
            assert count > 0 : "Count should be greater than 0.";
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
        System.out.println("Each of these specialisations had " + maxCount + " doctors.");
    }

    public static void viewMostFrequentDoctor() {
        assert doctorList != null: "Doctor list should not be null.";
        assert (!doctorList.isEmpty()): "Doctor list should not be empty.";

        int maxPatients = 0;
        ArrayList<String> mostVisitedDoctors = new ArrayList<>();

        for(Doctor doctor : doctorList) {
            assert doctor != null : "Doctor object should not be null.";
            int numPatients = doctor.getNumPatientsTreated();
            assert numPatients >= 0 : "Number of patients treated should not be negative.";

            if(numPatients > maxPatients) {
                maxPatients = numPatients;
                mostVisitedDoctors.clear();
            }
            if(numPatients == maxPatients) {
                mostVisitedDoctors.add(doctor.getName());
            }
        }

        assert !mostVisitedDoctors.isEmpty() : "There should be at least one doctor with highest " +
                "patient count.";

        System.out.println("The doctor(s) with the most patients treated and currently treating are: ");

        for(String doctorName : mostVisitedDoctors) {
            assert doctorName != null : "Doctor name in mostVisitedDoctors list should not be null.";
            System.out.println(doctorName);
        }

        System.out.println("These doctor(s) each have " + maxPatients + " total visits.");
    }
}
