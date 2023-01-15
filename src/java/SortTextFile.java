package java;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class SortTextFile {
    public static void sortFile(String fileName) {
        File file = new File(fileName);
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: The specified file was not found.");
            return;
        }
        Collections.sort(lines);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: An error occurred while writing to the file.");
        }
    }
}