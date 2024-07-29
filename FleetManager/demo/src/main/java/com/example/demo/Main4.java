package com.example.demo;

public class Main4 {
    public static void main(String[] args) {
        System.out.println("main thread");

        new Thread(() -> {
            double sum = 0;
            for (int i = 1; i < 10001; i++ ){
                sum += ( (double) 1 / i ) * Math.pow(-1 , i - 1);
            }
            System.out.println(sum);

            System.out.println("thread test");
        }).start();;
    }
}
