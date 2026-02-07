package model;

public class Patient extends Person {

    private String diagnosis;

    public Patient(int id, String name, int age, String diagnosis) {
        super(id, name, age, diagnosis);
        this.diagnosis = (diagnosis == null) ? "" : diagnosis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public void work() {
        System.out.println("Patient " + name + " is getting treatment for " + diagnosis);
    }

    @Override
    public String getRole() {
        return "Patient";
    }
}