import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Person> people = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        people.add(new Doctor(1, "Dr. Kapi", 56, "+77012007510", "Cardiology"));
        people.add(new Patient(2, "Sabina", 32, "+77012008227", "Heart attack"));

        boolean running = true;

        while (running) {
            showMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addDoctor();
                    case 2 -> addPatient();
                    case 3 -> viewAll();
                    case 4 -> demonstratePolymorphism();
                    case 5 -> viewDoctorsOnly();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Input error: " + e.getMessage());
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter numbers only.");
                scanner.nextLine();
            }

            if (running) {
                System.out.println("\nPress Enter to continue");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("1. Add doctor");
        System.out.println("2. Add patient");
        System.out.println("3. View all");
        System.out.println("4. Demonstrate polymorphism");
        System.out.println("5. View doctors only");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    private static void addPerson() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();
    }

    private static void addDoctor() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Specialization: ");
            String spec = scanner.nextLine();

            Person doctor = new Doctor(id, name, age, phone, spec);
            people.add(doctor);

            System.out.println("Doctor added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }

    private static void addPatient() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Disease: ");
            String disease = scanner.nextLine();

            Person patient = new Patient(id, name, age, phone, disease);
            people.add(patient);

            System.out.println("Patient added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void viewAll() {
        for (Person p : people) {
            System.out.println(p);
            if (p.isAdult()){
                System.out.println("Status: Adult");
            } else {
                System.out.println("Status: Minor");
            }
            if (p instanceof Patient pat) {
                if (pat.isCritical()) {
                    System.out.println("Critical condition!!!");
                }
            }
        }
    }

    private static void demonstratePolymorphism() {
        for (Person p : people) {
            p.work();

            if (p instanceof Doctor d) {
                d.treatPatient();
            } else if (p instanceof Patient pat) {
                pat.takeMedicine();
            }
        }
    }

    private static void viewDoctorsOnly() {
        for (Person p : people) {
            if (p instanceof Doctor) {
                Doctor d = (Doctor) p;
                System.out.println(d);
                System.out.println(
                        d.isSeniorDoctor() ? "Level: Senior Doctor" : "Level: Junior Doctor"
                );
            }
        }
    }
}