package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Meibo meibo = new Meibo(5, "Uchida", "uchida_yusei@ims-sol.co.jp");
        meibo.println();
        Product product = new Product("MT890", "ステンレスネジ", 280, false);
        product.print();
        Product product2 = new Product("MT891", "ステンレスネジ", 260);
        product2.print();
    }


}

//public class Product2()