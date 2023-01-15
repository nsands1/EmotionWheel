package java;

/*
 * Uses a HashSet to store the emotions and improve the time complexity, it also improved on the
 *  space complexity as well, it will only store unique elements in the set and not duplicates.
 * It checks for duplicates, removes them and writes the unique emotions to the file.
 * It also includes exception handling to handle different types of exceptions that may be thrown
 *  while reading and writing the file.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmotionReader {

    private static final Logger LOGGER = Logger.getLogger(EmotionReader.class.getName());

    public static void main(String[] args) {
        // Set to store the emotions
        HashSet<String> emotions = new HashSet<>();
        // List to store the duplicate emotions
        List<String> duplicateEmotions = new ArrayList<>();
        FileInputStream inputStream = null;
        Scanner sc = null;
        PrintWriter pw = null;
        try {
            File file = new File("EmotionsList.txt");
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // add the emotion to the set
                if (!emotions.add(line)) {
                    duplicateEmotions.add(line);
                }
            }
            sc.close();
            if (!duplicateEmotions.isEmpty()) {
                System.out.println("Duplicate emotions found in the list: " + duplicateEmotions);
                //remove the duplicate emotions
                pw = new PrintWriter(file);
                for (String emotion : emotions) {
                    pw.println(emotion);
                }
                pw.flush();
            } else {
                System.out.println("No duplicate emotions found in the list");
            }
        } catch (FileNotFoundException e) {
            // Handle the FileNotFoundException
            LOGGER.log(Level.SEVERE, "File not found, please check the file path and try again", e);
        } catch (IOException e) {
            // Handle IOException
            LOGGER.log(Level.SEVERE, "An error occurred while reading/writing the file", e);
        } catch (IllegalArgumentException e) {
            // Handle IllegalArgumentException
            LOGGER.log(Level.SEVERE, "Invalid file name: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle any other exception
            LOGGER.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "An error occurred while closing the input stream", e);
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
    }
}