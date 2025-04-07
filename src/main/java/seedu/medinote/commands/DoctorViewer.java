package seedu.medinote.commands;

import java.util.ArrayList;

import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;

public class DoctorViewer {

    private static final String LINE_BREAK = "===============================================" +
            "===============================================";
    private static final String VIEWABLE_DOCTORS_HEADER = "\tHere is a list of viewable doctors:";
    private static final String VIEW_DOCTOR_FORMAT_SUGGESTION =
            "\tUse command format: doctor <NAME_1> / ... / <NAME_X>";
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
        String[] targetNamesArray = targetNames.split("/");

        for (int i = 0; i < targetNamesArray.length; i++) {
            targetNamesArray[i] = targetNamesArray[i].trim();
            targetNamesArray[i] = reformatInfoParameters(targetNamesArray[i]);
        }

        ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();
        ArrayList<Integer> nameIndexList = findValidViewableDoctorIndexes(targetNamesArray, doctorList);
        printViewableDoctors(targetNamesArray, nameIndexList, doctorList);
    }

    private static String reformatInfoParameters(String info) {
        String[] splitInfo = info.split(" +");
        return joinWords(splitInfo);
    }

    private static String joinWords(String[] separatedWords) {
        String sentence = separatedWords[0];
        for (int i = 1; i < separatedWords.length; i++) {
            sentence = sentence.concat(" " + separatedWords[i]);
        }
        return sentence;
    }

    private static ArrayList<Integer> findValidViewableDoctorIndexes(String[] targetNamesArray,
                                                                     ArrayList<Doctor> doctorList) {
        ArrayList<Integer> nameIndexList = new ArrayList<>();
        for (String targetName : targetNamesArray) {
            int indexInDoctorList = 0;
            boolean doesDoctorExist = false;
            for (Doctor doctor : doctorList) {
                if (doctor.getName().equalsIgnoreCase(targetName)) {
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
        assert nameIndexList.size() == targetNamesArray.length
                : "nameIndexList and targetNamesArray are not the same size";
        return nameIndexList;
    }

    private static void printViewableDoctors(String[] targetNamesArray,
                                             ArrayList<Integer> nameIndexList,
                                             ArrayList<Doctor> doctorList) {
        System.out.println(LINE_BREAK);
        boolean isPrintableHeaderUsed = false;
        for (Integer integer : nameIndexList) {
            if (integer >= 0) {
                if (!isPrintableHeaderUsed) {
                    System.out.println("\tDetails of specified doctors:");
                }
                printThisDoctor(doctorList.get(integer));
            }
        }
        int count = 0;
        for (int i = 0; i < nameIndexList.size(); i++) {
            if (nameIndexList.get(i) < 0) {
                if (targetNamesArray[i].isEmpty()) {
                    continue;
                }

                if (count == 0) {
                    System.out.println("\tCould not find doctors named:");
                    count++;
                }
                System.out.println("\t\t> " + targetNamesArray[i]);
            }
        }
        System.out.println(LINE_BREAK);
    }

    private static void printThisDoctor(Doctor doctor) {
        assert doctor != null : "doctor should not be null";
        System.out.println(NAME_POINT + doctor.getName());
        System.out.println(SPECIALISATION_POINT + doctor.getSpecialisation());
        System.out.println(AVAILABILITY_POINT + doctor.getAvailability());
        System.out.println(CURRENTLY_TREATING_POINT + doctor.getPatientsBeingTreated());
        System.out.println(); // Add extra line break to match test expectations
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
        assert !doctorList.isEmpty() : "doctorList should not be empty";
        System.out.println(LINE_BREAK);
        System.out.println(VIEWABLE_DOCTORS_HEADER);
        for (Doctor doctor : doctorList) {
            System.out.println("\t\t> " + doctor.getName());
        }
        System.out.println(VIEW_DOCTOR_FORMAT_SUGGESTION);
        System.out.println(LINE_BREAK);
    }

}
