package menu;

import model.*;
import util.Treatable;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {

    private ArrayList<Person> people = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public MenuManager() {
        people.add(new Doctor(1, "Dr. Kapi", 56, "+77012007510", "Cardiology"));
        people.add(new Patient(2, "Sabina", 32, "+77012008227", "Heart attack"));
    }

    @Override
    public void displayMenu (){
        System.out.println("""
                1. Add doctor
                2. Add patient
                3. View All
                4. Polymorphism demo
                0. Exit
                """);
        System.out.println("Choice: ");
    }

    @Override
    public void run() {
       boolean running = true;
        while(running){
            displayMenu();

            try{
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice){
                    case 1 -> addDoctor();
                    case 2 -> addPatient();
                    case 3 -> viewAll();
                    case 4 -> polymorphismDemo();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void addDoctor() {
        System.out.println("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Age: ");
        int age = scanner.nextInt(); scanner.nextLine();
        System.out.println("Phone: ");
        String phone = scanner.nextLine();
        System.out.println("Specialization: ");
        String spec = scanner.nextLine();

        people.add(new Doctor(id, name, age, phone, spec));
    }

    private void addPatient() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt(); scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Disease: ");
        String disease = scanner.nextLine();

        people.add(new Patient(id, name, age, phone, disease));
    }

    private void viewAll() {
        people.forEach(System.out::println);
    }
    private void polymorphismDemo() {
        for (Person p : people){
            p.work();
            if (p instanceof Treatable t){
                t.treat();
            }
        }
    }
}