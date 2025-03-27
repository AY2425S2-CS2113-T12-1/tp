package seedu.medinote.commands;

import java.util.ArrayList;

import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;

public class DoctorViewer {

    private static final String LINE_BREAK = "===============================================" +
            "===============================================";
    private static final String VIEWABLE_DOCTORS_HEADER = "\tHere is a list of viewable doctors:";
    private static final String VIEW_DOCTOR_FORMAT_SUGGESTION = "\tUse command format: doctor <NAME>.";
    private static final String NO_DOCTORS_MESSAGE = "\tCurrently no viewable doctors! Use oncall to add a doctor.";
    private static final int INVALID_INDEX_IN_DOCTOR_LIST = -1;
    private static final String NAME_POINT = "\t\t>Name: ";
    private static final String SPECIALISATION_POINT = "\t\t\t>Specialisation: ";
    private static final String AVAILABILITY_POINT = "\t\t\t>Availability: ";
    private static final String CURRENTLY_TREATING_POINT = "\t\t\t>Currently treating: ";

    public static void viewDoctor(String targetNames) {
        if (targetNames.isBlank()) {
            showExistingDoctors();
        } else {
            searchViewableDoctors(targetNames);
        }
    }

    private static void searchViewableDoctors(String targetNames) {
        String[] targetNamesArray = targetNames.split(" +");
        ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();
        ArrayList<Integer> nameIndexList = findValidViewableDoctorIndexes(targetNamesArray, doctorList);
        printViewableDoctors(targetNamesArray, nameIndexList, doctorList);
    }

    private static ArrayList<Integer> findValidViewableDoctorIndexes(String[] targetNamesArray,
                                                                     ArrayList<Doctor> doctorList) {
        ArrayList<Integer> nameIndexList = new ArrayList<>();
        for (String targetName : targetNamesArray) {
            int indexInDoctorList = 0;
            boolean doesDoctorExist = false;
            for (Doctor doctor : doctorList) {
                if (doctor.getName().equals(targetName)) {
                    nameIndexList.add(indexInDoctorList);
                    doesDoctorExist = true;
                    break;
                } else {
                    indexInDoctorList++;
                }
            }
            if (!doesDoctorExist) {
                nameIndexList.add(INVALID_INDEX_IN_DOCTOR_LIST);
            }
        }
        return nameIndexList;
    }

    private static void printViewableDoctors(String[] targetNamesArray,
                                             ArrayList<Integer> nameIndexList,
                                             ArrayList<Doctor> doctorList) {
        System.out.println(LINE_BREAK);
        System.out.println("\tDetails of specified doctors:");
        for (Integer integer : nameIndexList) {
            if (integer >= 0) {
                printThisDoctor(doctorList.get(integer));
            }
        }
        if (nameIndexList.contains(-1)) {
            System.out.println("\tCould not find doctors named:");
        }
        for (int i = 0; i < nameIndexList.size(); i++) {
            if (nameIndexList.get(i) < 0) {
                System.out.println("\t\t> " + targetNamesArray[i]);
            }
        }
        System.out.println(LINE_BREAK);
    }

    private static void printThisDoctor(Doctor doctor) {
        System.out.println(NAME_POINT + doctor.getName());
        System.out.println(SPECIALISATION_POINT + doctor.getSpecialisation());
        System.out.println(AVAILABILITY_POINT + doctor.getAvailability());
        System.out.println(CURRENTLY_TREATING_POINT + doctor.getPatientsBeingTreated());
    }

    private static void showExistingDoctors() {
        ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();
        if (doctorList.isEmpty()) {
            System.out.println(NO_DOCTORS_MESSAGE);
        } else {
            printExistingDoctors(doctorList);
        }
    }

    private static void printExistingDoctors(ArrayList<Doctor> doctorList) {
        System.out.println(LINE_BREAK);
        System.out.println(VIEWABLE_DOCTORS_HEADER);
        for (Doctor doctor : doctorList) {
            System.out.println("\t\t> " + doctor.getName());
        }
        System.out.println(VIEW_DOCTOR_FORMAT_SUGGESTION);
        System.out.println(LINE_BREAK);
    }

}
