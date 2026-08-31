import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> people = new ArrayList<>();

        boolean done = false;

        while (!done) {
            String id = SafeInput.getNonZeroLenString(in, "Enter ID");
            String first = SafeInput.getNonZeroLenString(in, "Enter First Name");
            String last = SafeInput.getNonZeroLenString(in, "Enter Last Name");
            String title = SafeInput.getNonZeroLenString(in, "Enter Title");
            int yob = SafeInput.getRangedInt(in, "Enter Year of Birth", 0, 3000);

            String record = id + ", " + first + ", " + last + ", " + title + ", " + yob;
            people.add(record);

            done = !SafeInput.getYNConfirm(in, "Add another person?");
        }

        String filename = SafeInput.getNonZeroLenString(in, "Enter filename to save (e.g., PersonTestData.txt)");
        Path file = Path.of(filename);

        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            for (String p : people) {
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
