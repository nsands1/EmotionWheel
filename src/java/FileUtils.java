package java;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtils {

    public static void readFile(String fileName) {
        File file = new File("java/resources/textFiles/" + fileName);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            int c;
            while ((c = bis.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The specified file was not found in the directory java/resources/textFiles.");
        } catch (IOException e) {
            System.out.println("Error: An error occurred while reading the file.");
        }
    }
}

