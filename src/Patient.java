public class Patient extends Person {

    private String disease;

    public Patient(int id, String name, int age,
                   String phone, String disease) {

        super(id, name, age, phone);
        this.disease = disease;
    }

    public String getDisease() {
        return disease;
    }

    @Override
    public void work() {
        System.out.println("Patient " + name +
                " is receiving treatment for " + disease + ".");
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    public void takeMedicine() {
        System.out.println("Patient " + name + " takes medicine.");
    }

    public boolean isCritical() {
        return disease.equalsIgnoreCase("Heart Attack");
    }

    @Override
    public String toString() {
        return super.toString() + " | Disease: " + disease;
    }
}
