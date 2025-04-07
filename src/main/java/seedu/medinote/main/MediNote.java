package seedu.medinote.main;

import java.util.Scanner;

import seedu.medinote.storage.LoadData;
import seedu.medinote.ui.Ui;

import seedu.medinote.storage.EnsureFilesExist;
import seedu.medinote.manager.TaskManager;
import seedu.medinote.storage.SaveData;
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
            EnsureFilesExist.ensureDoctorsFileExists();
            EnsureFilesExist.ensurePatientsFileExists();

            // Load existing data
            LoadData dataLoader = new LoadData();
            DoctorListManager.getDoctorList().addAll(dataLoader.loadDoctorData());
            PatientListManager.getPatientList().addAll(dataLoader.loadPatientData());

        } catch (Exception e) {
            System.out.println("Error initializing data: " + e.getMessage());
        }
    }

    private static void saveAllData() {
        try {
            SaveData.saveDoctorsData(DoctorListManager.getDoctorList());
            new SaveData().savePatientsData(PatientListManager.getPatientList());
            System.out.println("All data saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

}
