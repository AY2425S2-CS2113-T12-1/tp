package seedu.medinote.main;

import java.util.Scanner;
import seedu.medinote.storage.loadData;
import seedu.medinote.manager.TaskManager;
import seedu.medinote.storage.saveData;
import seedu.medinote.storage.ensureFilesExist;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.manager.PatientListManager;

public class MediNote {

    public static void main(String[] args) {
        // Initialize storage files and load data
        initializeStorage();

        final Scanner userLineScanner = new Scanner(System.in);
        String userInput = "";
        //assert false : "test assertion failed";

        while (!userInput.equals("exit")) {
            userInput = userLineScanner.nextLine().trim();
            TaskManager.checkCommand(userInput);
        }

        // Save data before exiting
        saveAllData();

    }

    private static void initializeStorage() {
        try {
            // Ensure files exist
            ensureFilesExist.ensureDoctorsFileExists();
            ensureFilesExist.ensurePatientsFileExists();

            // Load existing data
            loadData dataLoader = new loadData();
            DoctorListManager.getDoctorList().addAll(dataLoader.loadDoctorData());
            PatientListManager.getPatientList().addAll(dataLoader.loadPatientData());

        } catch (Exception e) {
            System.out.println("Error initializing data: " + e.getMessage());
        }

    }

    private static void saveAllData() {
        try {
            saveData.saveDoctorsData(DoctorListManager.getDoctorList());
            new saveData().savePatientsData(PatientListManager.getPatientList());
            System.out.println("All data saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }

    }

}
