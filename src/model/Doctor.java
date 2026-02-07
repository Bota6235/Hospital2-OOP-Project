package model;

public class Doctor extends Person implements Treatable {

    private final String specialization;

    public Doctor(int id, String name, int age, String specialization){
        super(id, name, age, specialization);
        this.specialization = specialization;
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
    public void treat(){
        System.out.println(name + " treats a patient");
    }

    @Override
    public String toString() {
        return super.toString() + "Specialization: " + specialization;
    }
}