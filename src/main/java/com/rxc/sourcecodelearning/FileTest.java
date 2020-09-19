package com.rxc.sourcecodelearning;

import java.io.File;
import java.util.Scanner;

/**
 * @Description: display all files in a special directory
 * @Author RanXuCan
 * @Date 2020/9/19 18:00
 */
public class FileTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input the path of the special directory");
        String filename = scanner.next();
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("directory not found");
        } else
            displayFiles(file);
    }

    public static void displayFiles(File filename) {
        File[] listFiles = filename.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    System.out.println(file.getAbsolutePath());
                } else {
                    displayFiles(file);
                }
            }
        } else {
            System.out.println("not any file in directory: " + filename.getAbsolutePath());
        }
    }
}
