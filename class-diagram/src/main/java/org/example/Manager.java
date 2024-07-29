package org.example;

public class Manager extends Employee{
    private String title;

    public Manager(long id, String name, int age, String title) {
        super(id, name, age);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
