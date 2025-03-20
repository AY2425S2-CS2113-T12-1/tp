package seedu.duke;

import java.util.ArrayList;

public class DoctorListManager {
    private static ArrayList<Doctor> doctorList = new ArrayList<>();

    public static ArrayList<Doctor> getDoctorList() {
        return doctorList;
    }

    public static void listDoctors() {
        DoctorLister.listAllDoctors(doctorList);
    }

    public static void addDoctor(Doctor doctor) {
        doctorList.add(doctor);
    }

    public static void removeDoctor(Doctor doctor) {
        doctorList.remove(doctor);
    }

}
