package java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtilsMapped {
    public static void readFile(String fileName) {
        File file = new File("java/resources/textFiles/" + fileName);
        try (RandomAccessFile raf = new RandomAccessFile(file, "r");
             FileChannel channel = raf.getChannel()) {
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            for (int i = 0; i < buffer.limit(); i++) {
                System.out.print((char) buffer.get());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The specified file was not found in the directory java/resources/textFiles.");
        } catch (IOException e) {
            System.out.println("Error: An error occurred while reading the file.");
        }
    }
}

