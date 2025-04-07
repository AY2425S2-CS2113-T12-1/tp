package seedu.medinote.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EnsureFilesExist {
    public static void ensureDoctorsFileExists() throws IOException {
        File dir = new File("./hospitalData");

        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(SaveData.DOCTOR_FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
            // Optional: Write header
            Files.write(Paths.get(SaveData.DOCTOR_FILE_PATH),
                    "name|specialisation|availability|patientsBeingTreated|numPatientsTreated\n".getBytes());
        }
    }

    public static void ensurePatientsFileExists() throws IOException {
        File dir = new File("./hospitalData");

        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(SaveData.PATIENT_FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
            // Optional: Write header
            Files.write(Paths.get(SaveData.PATIENT_FILE_PATH),
                    "name|symptoms|timeStamp|medicalHistory|treatmentStatus|doctorAssigned\n".getBytes());
        }
    }
}
