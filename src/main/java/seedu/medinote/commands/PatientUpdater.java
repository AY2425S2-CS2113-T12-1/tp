package seedu.medinote.commands;

import java.util.ArrayList;

import seedu.medinote.manager.PatientListManager;
import seedu.medinote.person.Patient;

public class PatientUpdater {

    public static void updatePatient(String input) {

        if (input.isBlank()) {
            System.out.println("\tUpdate patient command needs a name!");
            return;
        }

        String[] parameters = reformatArray(input.split("/"));

        if (parameters.length > 3) {
            System.out.println("\tToo many parameters in update command!");
            return;
        }

        ArrayList<Patient> patientList = PatientListManager.getPatientList();
        int patientIndex = searchForPatient(parameters[0], patientList);

        if (patientIndex < 0) {
            System.out.println("\tPatient specified does not exist!");
            return;
        }

        if (parameters.length <= 1) {
            System.out.println("\tNo attributes to update specified!");
            return;
        }
        // at this point, patient exists and has attributes to change
        changeAttributes(parameters, patientIndex, patientList);
    }

    private static void changeAttributes(String[] parameters, int patientIndex, ArrayList<Patient> patientList) {
        // cycles through attribute=new for status/doctor attributes
        for (int i = 1; i < parameters.length; i++) {
            if (!isValidAttributeFormat(parameters[i])) {
                continue;
            }
            if (!isValidAttributeSpecified(parameters[i])) {
                System.out.println("\tIncorrect attribute specified!");
                continue;
            }

            changeThisAttribute(parameters[i], patientIndex, patientList);
        }
    }

    private static void changeThisAttribute(String attributeSetting, int patientIndex
            , ArrayList<Patient> patientList) {
        String[] attributeSplit = attributeSetting.split("=");
        if (attributeSplit[0].trim().equalsIgnoreCase("status")) {
            patientList.get(patientIndex).setTreatmentStatus(attributeSplit[1]);
            System.out.println("\tSuccessfully changed " + patientList.get(patientIndex).getName() +
                    " treatment status to " + attributeSplit[1]);
        } else {
            patientList.get(patientIndex).setDoctorAssigned(attributeSplit[1]);
            System.out.println("\tSuccessfully changed " + patientList.get(patientIndex).getName() +
                    " assigned doctor to " + attributeSplit[1]);
        }
    }

    private static boolean isValidAttributeSpecified(String attributeSetting) {
        String[] attributeSplit = attributeSetting.split("=");
        for (int i = 0; i < attributeSplit.length; i++) {
            attributeSplit[i] = attributeSplit[i].trim();
        }
        return attributeSplit[0].equalsIgnoreCase("status") || attributeSplit[0].equalsIgnoreCase("doctor");
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

    private static int searchForPatient(String parameter, ArrayList<Patient> patientList) {
        int i = 0;
        boolean exists = false;
        for (Patient patient : patientList) {
            if (patient.getName().equalsIgnoreCase(parameter)) {
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
