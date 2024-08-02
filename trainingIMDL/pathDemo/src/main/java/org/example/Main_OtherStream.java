package org.example;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main_OtherStream {
    public static void main(String[] args) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            bufferedWriter
                    .append("Test\n").append("AAAA\n").append("BBBB\n").append("CCCC\n");

            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//            String line;
//
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(">>" + line);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try (Scanner scanner = new Scanner(System.in)) {
//            while (scanner.hasNextLine()) {
//                System.out.println(scanner.next());
//            }
//        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(
                    """
                            AAAAAAA
                            ABCDEFG
                            CCCCCCC
                            """.getBytes(StandardCharsets.UTF_8))));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(">>" + line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));

            bufferedWriter.append("aaaaa").append("\n");
            bufferedWriter.append("bbbb").append("\n");
            bufferedWriter.append("cccc");
            bufferedWriter.flush();

            System.out.println(byteArrayOutputStream.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}