package com.example.demo;

public class Main3 {
    public static class StaticInnerClass {
        String name;
        int age;

        public StaticInnerClass(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }

    interface Animal {
        String call();
    }

    static class Dog implements Animal {
        @Override
        public String call() {
            return "bow";
        }
    }

    static class Cat implements Animal {
        @Override
        public String call() {
            return "neow";
        }
    }

    static class Pigeon implements Animal {
        @Override
        public String call() {
            return "coo";
        }

        public void fly() {
            System.out.println("I can fly");
        }
    }

    public static void main(String[] args) {
        StaticInnerClass demo = new StaticInnerClass("Taro", 24);
        System.out.println(demo.getName());
        System.out.println(demo.getAge());

        Animal dog = new Dog();
        Animal cat = new Cat();

        System.out.println(dog.call());
        System.out.println(cat.call());

        Animal animal = new Pigeon();

        if (animal instanceof Animal) {
            System.out.println("Animal type");
        }
        if (animal instanceof Dog) {
            System.out.println("Dog type");
        }
        if (animal instanceof Cat) {
            System.out.println("Cat type");
        }
        if (animal instanceof Pigeon) {
            System.out.println("Pigeon type");
            ((Pigeon) animal).fly();
        }
        if (animal instanceof Pigeon pigeon) {
            System.out.println("Pigeon type");
            pigeon.fly();
        }

        System.out.println("--------------");

        switch (animal) {
            case Dog dog1:
                System.out.println(dog1.call());
                break;
            case Cat cat1:
                System.out.println(cat1.call());
                break;
            case Pigeon pigeon:
                System.out.println(pigeon.call());
                pigeon.fly();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + animal);
        }
    }
}
