package com.rxc.sourcecodelearning;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @Description: display all files in a special directory,then output to a special file(containsFile.txt) in this directory
 * @Author RanXuCan
 * @Date 2020/9/19 18:00
 */
public class FileTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input the path of the special directory");
        String filename = scanner.next();
        File file = new File(filename);
        File outputTo = new File(file, "containsFile.txt");
        FileWriter fos = null;
        try {
            fos = new FileWriter(outputTo);
            if (!file.exists()) {
                System.out.println("directory not found");
            } else {
                displayFiles(file, fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void displayFiles(File filename, FileWriter fos) throws IOException {
        File[] listFiles = filename.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    String str = file.getAbsolutePath();
                    System.out.println(str);
                    fos.write(str + "\n");
                } else {
                    displayFiles(file, fos);
                }
            }
        } else {
            System.out.println("not any file in directory: " + filename.getAbsolutePath());
        }
    }
}
