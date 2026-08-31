import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> products = new ArrayList<>();

        boolean done = false;

        while (!done) {
            String id = SafeInput.getNonZeroLenString(in, "Enter Product ID");
            String name = SafeInput.getNonZeroLenString(in, "Enter Product Name");
            String desc = SafeInput.getNonZeroLenString(in, "Enter Description");
            double cost = SafeInput.getDouble(in, "Enter Cost");

            String record = id + ", " + name + ", " + desc + ", " + cost;
            products.add(record);

            done = !SafeInput.getYNConfirm(in, "Add another product?");
        }

        String filename = SafeInput.getNonZeroLenString(in, "Enter filename to save (e.g., ProductTestData.txt)");
        Path file = Path.of(filename);

        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            for (String p : products) {
                writer.write(p);
                writer.newLine();
            }
            System.out.println("File saved: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }

        in.close();
    }
}
