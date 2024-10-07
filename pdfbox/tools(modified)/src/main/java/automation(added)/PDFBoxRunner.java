package automation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PDFBoxRunner {

    public static void main(String[] args) {
        try {
            String command = "java -jar target/pdfbox-tools-4.0.0-SNAPSHOT-shaded.jar export:text --input sample.pdf --output output2.txt";

            for (int i = 1; i <= 100; i++) {
                System.out.println("Running command iteration: " + i);

                Process process = Runtime.getRuntime().exec(command);

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                int exitCode = process.waitFor();
                System.out.println("Command finished with exit code: " + exitCode);

                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
