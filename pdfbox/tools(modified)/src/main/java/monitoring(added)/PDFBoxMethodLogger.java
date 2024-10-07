package monitoring;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;

@Aspect
public class PDFBoxMethodLogger {

    @Before("execution(* org.apache.pdfbox..*(..))") // Adjust package as necessary
    public void logPDFBoxMethods(JoinPoint joinPoint) {
        System.out.println("PDFBox method called: " + joinPoint.getSignature());
    }
}
