package org.example;

public class Main {
    public static void main(String[] args) {
        Manager productManager = new Manager(110, "森下　樹", 32, "プロダクトマネージャー");

        System.out.println(productManager.getId() + " " + productManager.getName() + " " + productManager.getAge() + " " + productManager.getTitle());
    }
}