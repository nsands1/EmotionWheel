package java.ui;

/* 
 *
 * The program uses a try-catch block to handle the FileNotFoundException in the case the file
 *  "emotions.txt" cannot be found. In this case, the program will log a severe error message
 *  and return null.It uses a finally block to close the FileInputStream regardless of
 *  whether an exception was thrown or not.
 * It also uses a general catch block to handle any other exceptions that might be thrown
 *  while reading the file. In this case, the program will also log a severe error message and
 *  return null.
 * Also, the program uses a logger to log the error messages and exception details, this allows
 *  you to trace the error that has occurred and easily debug the problem.
 * This program creates a new JFrame and JPanel, it then calls the getEmotions() method which
 *  reads the final list of emotions from the file "emotions.txt" and stores them in a HashSet.
 * 
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EmotionUI extends JFrame {

    private static final Logger LOGGER = Logger.getLogger(EmotionUI.class.getName());
    private JPanel panel;
    private HashSet<JButton> buttons;

    public EmotionUI() {
        // Set the title of the JFrame
        super("Emotion List");
        // Create a new JPanel
        panel = new JPanel();
        // Set the layout of the panel to FlowLayout
        panel.setLayout(new FlowLayout());
        // Create a new HashSet to store the JButtons
        buttons = new HashSet<>();
        // Get the final list of emotions
        HashSet<String> emotions = getEmotions();
        if (emotions != null) {
            // Iterate through the list of emotions
            for (String emotion : emotions) {
                // Create a new JButton for each emotion
                JButton button = new JButton(emotion);
                // Add the button to the panel
                panel.add(button);
                // Add the button to the HashSet
                buttons.add(button);
                // Add an ActionListener to the button
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle the button click event
                    }
                });
            }
            // Add the panel to the JFrame
            add(panel);
            // Set the size of the JFrame
            setSize(new Dimension(3840, 2160));
            // Set the default close operation
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Set the JFrame to visible
            setVisible(true);
        }
    }

    private HashSet<String> getEmotions() {
        HashSet<String> emotions = new HashSet<>();
        FileInputStream inputStream = null;
        try {
            File file = new File("emotions.txt");
            inputStream = new FileInputStream(file);
            Scanner sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                emotions.add(line);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File not found, please check the file path and try again", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while reading the file", e);
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "An error occurred while closing the file input stream", e);
                }
            }
        }
        return emotions;
    }

    public static void main(String[] args) {
        new EmotionUI();
    }
}