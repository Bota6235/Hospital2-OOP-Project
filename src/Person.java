public class Person {

    protected int id;
    protected String name;
    protected int age;
    protected String phone;

    public Person(int id, String name, int age, String phone) {
        setId(id);
        setName(name);
        setAge(age);
        setPhone(phone);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            this.id = 0;
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown";
        }
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            this.age = 0;
        }
    }

    public void setPhone(String phone) {
        if (phone != null && phone.contains("+")) {
            this.phone = phone;
        } else {
            this.phone = "Not provided";
        }
    }

    public void work() {
        System.out.println(name + " is in the hospital.");
    }

    public String getRole() {
        return "Person";
    }

    public boolean isAdult() {
        return age >= 18;
    }

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name +
                " (ID: " + id +
                ", Age: " + age +
                ", Phone: " + phone + ")";
    }
}

