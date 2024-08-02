package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Path filePath = Path.of("/Users/u.yusay1498/Desktop/IMS/IMDL/ims_practice_uchida/pathDemo/src/main/resources/org/example/memo.txt");
        System.out.println(filePath);
        System.out.println(Files.exists(filePath));

        System.out.println(filePath.getRoot());
        System.out.println(filePath.getParent());
        System.out.println(filePath.getFileName());
        System.out.println(filePath.toAbsolutePath());
        System.out.println(filePath.toUri());

        File file = filePath.toFile();

        System.out.println("x: " + file.canExecute());
        System.out.println("r: " + file.canRead());
        System.out.println("w: " + file.canWrite());
        System.out.println("exists: " + file.exists());

        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getParent());

        System.out.println("x: " + Files.isExecutable(filePath));
        System.out.println("r: " + Files.isReadable(filePath));
        System.out.println("w: " + Files.isWritable(filePath));

        System.out.println("exists: " + Files.exists(filePath));

        try {
            Files.createDirectory(Path.of("target/temp"));
            Files.createDirectories(Path.of("target/temp2/abc/def"));
            Files.createFile(Path.of("target/temp2/abc/def/xyz.txt"));
            Files.copy(Path.of("target/temp2/abc/def/xyz.txt"), Path.of("target/temp/xyz.txt"));
            Files.move(Path.of("target/temp/xyz.txt"), Path.of("target/temp/xyz2.txt"));
            Files.move(Path.of("target/temp/xyz2.txt"), Path.of("target/temp/xyz3.txt"), StandardCopyOption.ATOMIC_MOVE);
            Files.delete(Path.of("target/temp/xyz3.txt"));
            Files.deleteIfExists(Path.of("target/temp/xyz3.txt"));
            Files.deleteIfExists(Path.of("target/temp"));

            System.out.println(Files.lines(filePath).collect(Collectors.joining()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> lines = Files.lines(filePath)) {
            System.out.println(lines.collect(Collectors.joining()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        filePath = Path.of("memo.txt");
//
//        System.out.println(filePath);
//        System.out.println(Files.exists(filePath));
//
//        System.out.println(filePath.toAbsolutePath());



//        System.out.println(Main.class.getResource("memo.txt"));
//        System.out.println(Main.class.getResource(""));
//        System.out.println(Main.class.getResource("."));
//        System.out.println(Main.class.getResource("/"));
//
//        URL fileUrl = Main.class.getResource("memo.txt");
//        Path.of(fileUrl.toURI());
    }
}