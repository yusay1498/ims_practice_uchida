package org.example;

import java.util.ArrayList;
import java.util.List;

//Nara
//Shiga
//Hokkaido
//Chiba

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("Nara");
        list.add("Shiga");
        list.add("Hokkaido");
        list.add("Chiba");
        for (String str : list) {
            System.out.println(str);
        }
    }
}