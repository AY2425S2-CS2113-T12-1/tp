package seedu.medinote.commands;

import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.person.Doctor;

import java.util.ArrayList;

public class DoctorUpdater {

    public static void updateDoctor(String input) {

        if (input.isBlank()) {
            System.out.println("\tUpdate doctor command needs a name!");
            return;
        }

        String[] parameters = reformatArray(input.split("/"));

        if (parameters.length > 3) {
            System.out.println("\tToo many parameters in update command!");
            return;
        }

        ArrayList<Doctor> doctorList = DoctorListManager.getDoctorList();
        int doctorIndex = searchForDoctor(parameters[0], doctorList);

        if (doctorIndex < 0) {
            System.out.println("\tDoctor specified does not exist!");
            return;
        }

        if (parameters.length <= 1) {
            System.out.println("\tNo attributes to update specified!");
            return;
        }
        // at this point, doctor exists and has attributes to change
        changeAttributes(parameters, doctorIndex, doctorList);
    }

    private static void changeAttributes(String[] parameters, int doctorIndex, ArrayList<Doctor> doctorList) {
        // cycles through attribute=new for status/doctor attributes
        for (int i = 1; i < parameters.length; i++) {
            if (!isValidAttributeFormat(parameters[i])) {
                continue;
            }
            if (!isValidAttributeSpecified(parameters[i])) {
                System.out.println("\tIncorrect attribute specified!");
                continue;
            }

            changeThisAttribute(parameters[i], doctorIndex, doctorList);
        }
    }

    private static void changeThisAttribute(String attributeSetting, int doctorIndex
            , ArrayList<Doctor> doctorList) {
        String[] attributeSplit = attributeSetting.split("=");
        if (attributeSplit[0].trim().equalsIgnoreCase("availability")) {
            doctorList.get(doctorIndex).setAvailability(attributeSplit[1]);
            System.out.println("\tSuccessfully changed " + doctorList.get(doctorIndex).getName() +
                    " availability to " + attributeSplit[1]);
        } else {
            doctorList.get(doctorIndex).setCurrentPatient(attributeSplit[1]);
            System.out.println("\tSuccessfully changed " + doctorList.get(doctorIndex).getName() +
                    " current patients to " + attributeSplit[1]);
        }
    }

    private static boolean isValidAttributeSpecified(String attributeSetting) {
        String[] attributeSplit = attributeSetting.split("=");
        for (int i = 0; i < attributeSplit.length; i++) {
            attributeSplit[i] = attributeSplit[i].trim();
        }
        return attributeSplit[0].equalsIgnoreCase("availability") || attributeSplit[0].equalsIgnoreCase("assignment");
    }

    private static boolean isValidAttributeFormat(String attributeSetting) {
        if (!attributeSetting.contains("=")) {
            System.out.println("\tAttribute specified is missing =");
            return false;
        }
        String[] attributeSplit = attributeSetting.split("=");
        if (attributeSplit.length > 2) {
            System.out.println("\tToo many = used!");
            return false;
        } else if (attributeSplit.length == 1) {
            System.out.println("\tAttribute is missing key information");
            return false;
        } else {
            return true;
        }
    }

    private static int searchForDoctor(String parameter, ArrayList<Doctor> doctorList) {
        int i = 0;
        boolean exists = false;
        for (Doctor doctor : doctorList) {
            if (doctor.getName().equalsIgnoreCase(parameter)) {
                exists = true;
                break;
            } else {
                i++;
            }
        }

        if (exists) {
            return i;
        } else {
            return -1;
        }
    }

    private static String[] reformatArray(String[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = parameters[i].toLowerCase().trim();
            parameters[i] = reformatInfoParameters(parameters[i]);
        }
        return parameters;
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

}
