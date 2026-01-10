public class Doctor extends Person {

    private String specialization;

    public Doctor(int id, String name, int age, String phone, String specialization) {
        super(id, name, age, phone);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public void work() {
        System.out.println(name + " is treating patients (" + specialization + ")");
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    public void treatPatient() {
        System.out.println(name + " treats a patient");
    }

    public boolean isSeniorDoctor() {
        return age >= 40;
    }

    @Override
    public String toString() {
        return super.toString() + "Specialization: " + specialization;
    }
}

