package menu;

import database.PatientDAO;
import model.Patient;
import util.Treatable;

import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final PatientDAO patientDAO = new PatientDAO();

    @Override
    public void displayMenu() {
        System.out.println("""
                Hospital Menu
                1. Add patient
                2. View all patients
                3. Update patient
                4. Delete patient
                5. Search patient by name
                6. Polymorphism demo
                0. Exit
                """);
        System.out.print("Choice: ");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPatient();
                case 2 -> viewPatients();
                case 3 -> updatePatient();
                case 4 -> deletePatient();
                case 5 -> searchPatient();
                case 6 -> polymorphismDemo();
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void addPatient() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt(); scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Diagnosis: ");
        String diagnosis = scanner.nextLine();

        Patient patient = new Patient(0, name, age, phone, diagnosis);
        patientDAO.insertPatient(patient);
    }

    private void viewPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        patients.forEach(System.out::println);
    }

    private void updatePatient() {
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt(); scanner.nextLine();

        Patient existing = patientDAO.getPatientById(id);
        if (existing == null) {
            System.out.println("Patient not found");
            return;
        }

        System.out.print("New name [" + existing.getName() + "]: ");
        String name = scanner.nextLine();
        if (name.isBlank()) name = existing.getName();

        System.out.print("New age [" + existing.getAge() + "]: ");
        String ageInput = scanner.nextLine();
        int age = ageInput.isBlank() ? existing.getAge() : Integer.parseInt(ageInput);

        System.out.print("New diagnosis [" + existing.getDisease() + "]: ");
        String disease = scanner.nextLine();
        if (disease.isBlank()) disease = existing.getDisease();

        Patient updated = new Patient(id, name, age, existing.getPhone(), disease);
        patientDAO.updatePatient(updated);
    }

    private void deletePatient() {
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt(); scanner.nextLine();

        Patient patient = patientDAO.getPatientById(id);
        if (patient == null) {
            System.out.println("Patient not found");
            return;
        }

        System.out.println(patient);
        System.out.print("Are you sure? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            patientDAO.deletePatient(id);
        }
    }

    private void searchPatient() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        patientDAO.searchByName(name).forEach(System.out::println);
    }

    private void polymorphismDemo() {
        for (Patient p : patientDAO.getAllPatients()) {
            p.work();
            if (p instanceof Treatable t) {
                t.treat();
            }
        }
    }
}
