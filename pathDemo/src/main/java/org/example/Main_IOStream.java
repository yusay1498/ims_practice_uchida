package org.example;

import java.io.*;
import java.nio.file.Path;

public class Main_IOStream {
    public static void main(String[] args) {
        Path filePath = Path.of("/Users/u.yusay1498/Desktop/IMS/IMDL/ims_practice_uchida/pathDemo/src/main/resources/org/example/memo.txt");


//        InputStream inputStream = null;
//        InputStreamReader reader = null;
//        BufferedReader bufferedReader = null;

        try (BufferedReader bufferedReader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(filePath.toFile())
                             ))) {

//            inputStream = new FileInputStream(filePath.toFile());
//            reader = new InputStreamReader(inputStream);
//            bufferedReader  = new BufferedReader(reader);
//            bufferedReader =
//                    new BufferedReader(
//                            new InputStreamReader(
//                                    new FileInputStream(filePath.toFile())));

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path filePathOut = Path.of("/Users/u.yusay1498/Desktop/IMS/IMDL/ims_practice_uchida/pathDemo/src/main/resources/org/example/memo_out.txt");

//        OutputStream outputStream = null;
//        OutputStreamWriter outputStreamWriter = null;
//        BufferedWriter bufferedWriter = null;

        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new FileOutputStream(filePathOut.toFile())
                             ))) {
//            outputStream = new FileOutputStream(filePathOut.toFile());
//            outputStreamWriter = new OutputStreamWriter(outputStream);
//            bufferedWriter = new BufferedWriter(outputStreamWriter);
//            bufferedWriter =
//                    new BufferedWriter(
//                            new OutputStreamWriter(
//                                    new FileOutputStream(filePathOut.toFile())));

            bufferedWriter
                    .append("Test\n").append("AAAA\n").append("BBBB\n").append("CCCC\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}