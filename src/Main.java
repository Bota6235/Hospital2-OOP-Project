import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Person> people = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        people.add(new Doctor(1, "Dr. Kapi", 56, "+77010000001", "Cardiology"));
        people.add(new Patient(2, "Sabina", 32, "+77010000002", "Heart attack"));

        boolean running = true;

        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPerson();
                case 2 -> addDoctor();
                case 3 -> addPatient();
                case 4 -> viewAll();
                case 5 -> demonstratePolymorphism();
                case 6 -> viewDoctorsOnly();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice");
            }

            if (running) {
                System.out.println("\nPress Enter to continue");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("1. Add Person");
        System.out.println("2. Add Doctor");
        System.out.println("3. Add Patient");
        System.out.println("4. View All (Polymorphic)");
        System.out.println("5. Demonstrate Polymorphism");
        System.out.println("6. View Doctors Only");
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

        people.add(new Person(id, name, age, phone));
    }

    private static void addDoctor() {
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
    }

    private static void addPatient() {
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

        people.add(new Patient(id, name, age, phone, disease));
    }

    private static void viewAll() {
        for (Person p : people) {
            System.out.println(p);
        }
    }

    private static void demonstratePolymorphism() {
        for (Person p : people) {
            p.work();
        }
    }

    private static void viewDoctorsOnly() {
        for (Person p : people) {
            if (p instanceof Doctor) {
                Doctor d = (Doctor) p;
                System.out.println(d);
            }
        }
    }
}
