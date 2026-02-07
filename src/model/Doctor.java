package model;

public class Doctor extends Person implements Treatable {

    private final String specialization;
    private final double salary;

    public Doctor(int id, String name, int age, String specialization, double salary) {
        super(id, name, age, specialization);
        this.specialization = specialization;
        this.salary = salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public void work() {
        System.out.println(name + " is treating patients (" + specialization + ")");
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    @Override
    public void treat() {
        System.out.println(name + " treats a patient");
    }
}
