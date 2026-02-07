package model;

import exception.InvalidInputException;

public abstract class Person {

    protected int id;
    protected String name;
    protected int age;
    protected String diagnosis;

    public Person(int id, String name, int age, String diagnosis) {
        setId(id);
        setName(name);
        setAge(age);
        setDiagnosis(diagnosis);
    }

    public abstract void work();

    public abstract String getRole();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDiagnossis() { return diagnosis; }

    protected void setId(int id) {
        this.id = id;
    }

    protected void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidInputException("Name cannot be empty");
        }
        this.name = name;
    }

    protected void setAge(int age) {
        if (age < 0) {
            throw new InvalidInputException("Age cannot be negative");
        }
        this.age = age;
    }

    protected void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "(" + getRole() + ") " + name + " (ID: " + id + ", Age: " + age + ", Diagnosis: " + diagnosis + ")";
    }
}