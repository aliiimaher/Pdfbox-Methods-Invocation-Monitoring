package com.example.aspectjbouncycastle;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class EncryptionMonitoringAspect {

    /** part 1
     * almost everything in the main class
     */
    @Before("within(com.example.aspectjbouncycastle.Main)")
    public void logMainMethod(JoinPoint joinPoint) {
        System.out.println(">>> Main method invoked: " + joinPoint.getSignature().toShortString());
    }

    /** part 2
     *
     */
    @Pointcut("call(* org.bouncycastle.crypto.engines.*.*(..))")
    public void aesFastEngineMethods() {}

    @Pointcut("call(* org.bouncycastle.crypto.modes.CBCBlockCipher(..))")
    public void cbcBlockCipherMethods() {}

    @Pointcut("call(* org.bouncycastle.crypto.paddings.*.*(..))")
    public void paddedBufferedBlockCipherMethods() {}

    @Pointcut("call(* org.bouncycastle.crypto.params.*(..))")
    public void parametersWithIVMethods() {}

    @Pointcut("call(* org.bouncycastle.crypto.params.*.*.*(..))")
    public void keyParameterMethods() {}

    @Before("aesFastEngineMethods()")
    public void logAesFastEngineMethods(JoinPoint joinPoint) {
        System.out.println("Invoked AESFastEngine method: " + joinPoint.getSignature());
    }

    @Before("cbcBlockCipherMethods()")
    public void logCbcBlockCipherMethods(JoinPoint joinPoint) {
        System.out.println("Invoked CBCBlockCipher method: " + joinPoint.getSignature());
    }

    @Before("paddedBufferedBlockCipherMethods()")
    public void logPaddedBufferedBlockCipherMethods(JoinPoint joinPoint) {
        System.out.println("444_Invoked PaddedBufferedBlockCipher method: " + joinPoint.getSignature());
    }

    @Before("parametersWithIVMethods()")
    public void logParametersWithIVMethods(JoinPoint joinPoint) {
        System.out.println("Invoked ParametersWithIV method: " + joinPoint.getSignature());
    }

    @Before("keyParameterMethods()")
    public void logKeyParameterMethods(JoinPoint joinPoint) {
        System.out.println("Invoked KeyParameter method: " + joinPoint.getSignature());
    }
    // end of part 2

//    // Pointcut for all constructors in the specified package
//    @Pointcut("execution(org.bouncycastle.crypto.*.new(..))")
//    public void allBouncyCastleConstructors() {}
//
//    @Before("allBouncyCastleConstructors()")
//    public void logConstructorInvocations(JoinPoint joinPoint) {
//        System.out.println("Invoked constructor: " + joinPoint.getSignature());
//    }

    /** Part 3
     * I get help from this website for the following pointcuts:
     * https://docs.spring.io/spring-framework/docs/4.3.15.RELEASE/spring-framework-reference/html/aop.html
     */
    // any public
    @Pointcut("execution(public * *(..))")
    private void anyPublicOperation() {}

    // within bouncy castle crypto paddings
    @Pointcut("within(org.bouncycastle.crypto.paddings..*)")
    private void inBouncyCastlePackage() {}

    // as it is written in the website it is for:
    // the execution of any method defined in the selected package or a sub-package
    @Pointcut("call(* org.bouncycastle.crypto..*.*(..))")
    private void inBouncyCastlePackage2() {}

    // by interface
    @Pointcut("execution(* org.bouncycastle.crypto.CipherParameters.*(..))")
    private void cipherParametersMethods() {}

    @Before("inBouncyCastlePackage2()")
    public void beforeAnyPublicOperation(JoinPoint joinPoint) {
        Main.count++;
        System.out.println("???: " + joinPoint.getSignature().getName());
    }

}
