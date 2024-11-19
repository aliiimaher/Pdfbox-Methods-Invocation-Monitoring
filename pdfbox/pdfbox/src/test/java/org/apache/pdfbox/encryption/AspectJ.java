package org.apache.pdfbox.encryption;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Aspect
public class AspectJ {

    private static final String LOG_FILE = "bouncycastle_methods.log";

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

//    @Before("publicMethod()")
//    public void logPublicMethod(JoinPoint joinPoint) {
//        System.out.println(">>>1 Enc. method invoked: " + joinPoint.getSignature().toShortString());
//    }

    @Pointcut("execution(* org.bouncycastle..*.*(..))")
    public void bouncyCastleMethods() {}

    @Before("bouncyCastleMethods()")
    public void logBouncyFounded(JoinPoint joinPoint) {
        String content = "founded bouncy: " + joinPoint.getSignature().toString();
        TestPublicKeyEncryption.number_of_method_invocations++;
        System.out.println(content);
        writeToFile(content);
    }

    private void writeToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
