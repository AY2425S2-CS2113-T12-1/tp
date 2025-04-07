package seedu.medinote.main;

import java.util.Scanner;
import seedu.medinote.ui.Ui;

import seedu.medinote.storage.ensureFilesExist;
import seedu.medinote.storage.loadData;
import seedu.medinote.manager.TaskManager;
import seedu.medinote.storage.saveData;
import seedu.medinote.manager.DoctorListManager;
import seedu.medinote.manager.PatientListManager;

public class MediNote {

    public static void main(String[] args) {
        // Initialize storage files and load data
        initializeStorage();
        Ui.printGreetings();
        final Scanner userLineScanner = new Scanner(System.in);
        String userInput;

        while (userLineScanner.hasNextLine()) {
            userInput = userLineScanner.nextLine().trim();
            if (userInput.equals("exit")) {
                break;
            }
            TaskManager.checkCommand(userInput);
        }

        // Save data before exiting
        saveAllData();
        System.out.println("Exiting MediNote...");

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
