import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProductReader {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser(".");
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected.");
            return;
        }

        Path file = chooser.getSelectedFile().toPath();

        System.out.printf("%-10s %-15s %-30s %-10s%n",
                "ID#", "Name", "Description", "Cost");
        System.out.println("==============================================================");

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",\\s*");

                System.out.printf("%-10s %-15s %-30s %-10s%n",
                        fields[0], fields[1], fields[2], fields[3]);
            }

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}
