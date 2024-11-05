package com.example.aspectjpdfbox;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;

@Aspect
public class BouncyCastleMonitorAspect {

//    @Before("call(* org.bouncycastle..*(..))")
//    public void logBouncyCastleMethod(JoinPoint joinPoint) {
//        System.out.println("Invoked method: " + joinPoint.getSignature().toShortString());
//    }

//    @Before("call(* org.bouncycastle.crypto.*.*(..))")
//    public void logCryptoMethod(JoinPoint joinPoint) {
//        System.out.println("call___Crypto method invoked: " + joinPoint.getSignature().toShortString());
//    }
//
//    @Before("execution(* org.bouncycastle.crypto.*.*(..))")
//    public void logCryptoMethodInvocation(JoinPoint joinPoint) {
//        System.out.println("execution___Crypto method invoked: " + joinPoint.getSignature().toShortString());
//    }

    @Before("execution(* com.example.aspectjpdfbox.Main.main(..))")
    public void logMainMethod(JoinPoint joinPoint) {
        System.out.println("Main method invoked: " + joinPoint.getSignature().toShortString());
    }

    //org.apache.pdfbox.pdmodel.encryption
    @Before("call(* org.apache.pdfbox.pdmodel.encryption.*.*(..))")
    public void logPdfBoxEncryptionMethod(JoinPoint joinPoint) {
        System.out.println("PDFBox encryption method invoked: " + joinPoint.getSignature().toShortString());
    }

    @Before("handler(org.apache.pdfbox.pdmodel.encryption.*Exception)")
    public void logWithinPdfBoxEncryption(JoinPoint joinPoint) {
        System.out.println("Within PDFBox encryption: " + joinPoint.getSignature().toShortString());
    }

//    @Before("within(org.bouncycastle..*)")
//    public void logWithinBouncyCastle(JoinPoint joinPoint) {
//        System.out.println("Within Bouncy Castle: " + joinPoint.getSignature().toShortString());
//    }
//
//    @Before("execution(public * org.bouncycastle..*(..)) || execution(protected * org.bouncycastle..*(..)) || execution(private * org.bouncycastle..*(..))")
//    public void logBouncyCastleMethod(JoinPoint joinPoint) {
//        System.out.println("Invoked method: " + joinPoint.getSignature().toShortString());
//    }

}
