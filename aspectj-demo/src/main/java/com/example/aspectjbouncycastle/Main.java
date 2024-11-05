package com.example.aspectjbouncycastle;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher; //done
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class Main {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static int count = 0;

    public static void main(String[] args) throws Exception {
        String plainText = "Hello, World!";

        // Generate AES key and IV using SecureRandom
        byte[] key = new byte[16]; // 128-bit key
        byte[] iv = new byte[16];  // 128-bit IV
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(key);
        secureRandom.nextBytes(iv);

        // Encrypt
        String cipherText = encrypt(plainText, key, iv);
        System.out.println("Encrypted Text: " + cipherText);

        // Decrypt
        String decryptedText = decrypt(cipherText, key, iv);
        System.out.println("Decrypted Text: " + decryptedText);

        System.out.println("count: " + count / 2);
    }

    public static String encrypt(String plainText, byte[] key, byte[] iv) throws Exception {
        AESFastEngine engine = new AESFastEngine();
        CBCBlockCipher cbc = new CBCBlockCipher(engine);
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(cbc);
        CipherParameters params = new ParametersWithIV(new KeyParameter(key), iv);

        cipher.init(true, params); // true for encryption
        byte[] input = plainText.getBytes();
        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int bytesProcessed = cipher.processBytes(input, 0, input.length, output, 0);
        cipher.doFinal(output, bytesProcessed);

        return Base64.getEncoder().encodeToString(output);
    }

    public static String decrypt(String cipherText, byte[] key, byte[] iv) throws Exception {
        AESFastEngine engine = new AESFastEngine();
        CBCBlockCipher cbc = new CBCBlockCipher(engine);
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(cbc);
        CipherParameters params = new ParametersWithIV(new KeyParameter(key), iv);

        cipher.init(false, params); // false for decryption
        byte[] input = Base64.getDecoder().decode(cipherText);
        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int bytesProcessed = cipher.processBytes(input, 0, input.length, output, 0);
        cipher.doFinal(output, bytesProcessed);

        return new String(output).trim();
    }
}
