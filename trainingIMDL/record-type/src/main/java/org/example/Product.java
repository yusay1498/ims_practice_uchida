package org.example;

public record Product(String code, String name, int price, boolean shortage) {
    public void print() {
        System.out.println("商品コード = " + code());
        System.out.println("商品名　　 = " + name());
        System.out.println("価格　　　 = " + price());
        System.out.println("欠品　　　 = " + shortage());
    }

    public Product(String code, String name, int price) {
        this(code, name, price, false);
    }
}


