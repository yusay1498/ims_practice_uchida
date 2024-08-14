package com.example.demo;

import java.util.Optional;

public class Person {
    String id;
    String name;
    Integer age;

    public Person(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person copy(
            Optional<String> id,
            Optional<String> name,
            Optional<Integer> age
    ) {
        Person copiedPerson = new Person(
                getId(),
                getName(),
                getAge()
        );

        if (id != null) {
            id.ifPresentOrElse(
                    copiedPerson::setId,
                    () -> copiedPerson.setId(null));
        }

        if (name != null) {
            name.ifPresentOrElse(
                    copiedPerson::setName,
                    () -> copiedPerson.setName(null));
        }

        if (age != null) {
            age.ifPresentOrElse(
                    copiedPerson::setAge,
                    () -> copiedPerson.setAge(null));
        }

        return copiedPerson;
    }
}

