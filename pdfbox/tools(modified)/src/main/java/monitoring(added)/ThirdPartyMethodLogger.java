package monitoring;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;

// ========== Ali Maher ==========
// this file is created by Ali Maher >>>

@Aspect
public class ThirdPartyMethodLogger {

    // Intercept all method calls in the BouncyCastle library
    @Before("execution(* org.bouncycastle..*(..))")
    public void logBouncyCastleMethods(JoinPoint joinPoint) {
        System.out.println("BouncyCastle method called: " + joinPoint.getSignature());
    }

    // Intercept all method calls in the Log4j2 library
    @Before("execution(* org.apache.logging.log4j..*(..))")
    public void logLog4j2Methods(JoinPoint joinPoint) {
        System.out.println("Log4j2 method called: " + joinPoint.getSignature());
    }
}
