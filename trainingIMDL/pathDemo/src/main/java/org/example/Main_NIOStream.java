package org.example;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main_NIOStream {
    public static void main(String[] args) {
        Path filePath = Path.of("/Users/u.yusay1498/Desktop/IMS/IMDL/ims_practice_uchida/pathDemo/src/main/resources/org/example/memo_sjis.txt");

        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath, Charset.forName("Windows-31J"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path filePathOut = Path.of("/Users/u.yusay1498/Desktop/IMS/IMDL/ims_practice_uchida/pathDemo/src/main/resources/org/example/memo_out.txt");

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePathOut)) {
            bufferedWriter
                    .append("Test\n").append("AAAA\n").append("BBBB\n").append("CCCC\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}