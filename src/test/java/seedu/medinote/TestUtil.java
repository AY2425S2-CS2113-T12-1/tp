package seedu.medinote;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtil {
    public static void deleteTestFiles() throws IOException {
        Path doctorsPath = Paths.get("./hospitalData/MediNote_Doctor_Data.txt");
        Path patientsPath = Paths.get("./hospitalData/MediNote_Patient_Data.txt");

        Files.deleteIfExists(doctorsPath);
        Files.deleteIfExists(patientsPath);
    }

    public static void createTestFiles() throws IOException {
        Path doctorsPath = Paths.get("./hospitalData/MediNote_Doctor_Data.txt");
        Path patientsPath = Paths.get("./hospitalData/MediNote_Patient_Data.txt");

        Files.createDirectories(doctorsPath.getParent());
        if (!Files.exists(doctorsPath)) {
            Files.createFile(doctorsPath);
        }
        if (!Files.exists(patientsPath)) {
            Files.createFile(patientsPath);
        }
    }

}
