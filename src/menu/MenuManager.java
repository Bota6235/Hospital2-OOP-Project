package menu;

import database.PatientDAO;
import model.Patient;
import database.DoctorDAO;
import model.Doctor;

import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final PatientDAO patientDAO = new PatientDAO();
    private final DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    public void displayMenu() {
        System.out.println("""
                Hospital Menu
                1. Add patient
                2. View all patients
                3. Update patient
                4. Delete patient
                5. Search patient by name
                6. Add doctor
                7. Update doctor
                8. Delete doctor
                9. Search doctor by name
                10. Search doctor by minimum salary
                11. Search doctor by salary range
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
                case 6 -> addDoctor();
                case 7 -> updateDoctor();
                case 8 -> deleteDoctor();
                case 9 -> searchDoctor();
                case 10 -> searchDoctorByMinSalary();
                case 11 -> searchDoctorBySalaryRange();
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void addPatient() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Diagnosis: ");
        String diagnosis = scanner.nextLine();

        patientDAO.insertPatient(new Patient(0, name, age, diagnosis));
    }

    private void viewPatients() {
        patientDAO.printAllPatients();
    }

    private void updatePatient() {
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

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
        int age = ageInput.isBlank()
                ? existing.getAge()
                : Integer.parseInt(ageInput);

        System.out.print("New diagnosis [" + existing.getDiagnosis() + "]: ");
        String diagnosis = scanner.nextLine();
        if (diagnosis.isBlank()) diagnosis = existing.getDiagnosis();

        patientDAO.updatePatient(new Patient(id, name, age, diagnosis));
    }

    private void deletePatient() {
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

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
        patientDAO.searchByName(name);
    }

    private void searchDoctorByMinSalary() {
        System.out.print("Enter minimum salary: ");
        double minSalary = scanner.nextDouble();
        scanner.nextLine();

        doctorDAO.searchByMinSalary(minSalary);
    }

    private void searchDoctorBySalaryRange() {
        System.out.print("Enter minimum salary: ");
        double min = scanner.nextDouble();

        System.out.print("Enter maximum salary: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        doctorDAO.searchBySalaryRange(min, max);
    }

    private void addDoctor() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        doctorDAO.addDoctor(new Doctor(0, name, age, specialization, salary));
    }

    private void updateDoctor() {
        System.out.print("Doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Doctor existing = doctorDAO.getDoctorById(id);
        if (existing == null) {
            System.out.println("Doctor not found");
            return;
        }

        System.out.print("New name [" + existing.getName() + "]: ");
        String name = scanner.nextLine();
        if (name.isBlank()) name = existing.getName();

        System.out.print("New age [" + existing.getAge() + "]: ");
        String ageInput = scanner.nextLine();
        int age = ageInput.isBlank() ? existing.getAge() : Integer.parseInt(ageInput);

        System.out.print("New specialization [" + existing.getSpecialization() + "]: ");
        String spec = scanner.nextLine();
        if (spec.isBlank()) spec = existing.getSpecialization();

        System.out.print("New salary [" + existing.getSalary() + "]: ");
        String salInput = scanner.nextLine();
        double salary = salInput.isBlank() ? existing.getSalary() : Double.parseDouble(salInput);

        doctorDAO.updateDoctor(new Doctor(id, name, age, spec, salary));
    }

    private void deleteDoctor() {
        System.out.print("Doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        doctorDAO.deleteDoctor(id);
    }

    private void searchDoctor() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        doctorDAO.searchDoctorByName(name);
    }
}