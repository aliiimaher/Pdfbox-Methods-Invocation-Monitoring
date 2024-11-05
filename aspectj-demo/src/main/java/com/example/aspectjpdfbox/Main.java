package com.example.aspectjpdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputPath = "src/main/resources/input.pdf";
        String outputPath = "encrypted.pdf";

        try (PDDocument document = PDDocument.load(new File(inputPath))) {
            // Set up permissions and policy
            AccessPermission permission = new AccessPermission();
            permission.setCanPrint(true);

            StandardProtectionPolicy policy = new StandardProtectionPolicy("123", "123", permission);
            policy.setEncryptionKeyLength(128);  // Set encryption strength

            document.protect(policy);
            document.save(outputPath);
            System.out.println("PDF encrypted and saved to " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

