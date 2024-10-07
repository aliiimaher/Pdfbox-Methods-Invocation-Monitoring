# Pdfbox-Methods-Invocation-Monitoring
This repository belongs to Apache Pdfbox Methods Invocation Monitoring.

# **PDFBox Method Invocation and System Monitoring**

This project presents a case study of monitoring method invocations and system properties (such as CPU and memory usage) during executing various **Apache PDFBox** commands. The study is based on tasks outlined in the **RICK paper**, which analyzes runtime behavior and third-party library dependencies.

The project uses **AspectJ** for method invocation tracking and **Java Management Extensions (JMX)** along with **JFreeChart** for real-time system properties monitoring. It also automates the repeated execution of commands to capture sufficient performance data.

---

## **Features**

- **Method Invocation Monitoring**: Track method calls from both internal PDFBox functions and third-party libraries using **AspectJ**.
- **System Properties Monitoring**: Monitor system properties such as **CPU usage** and **memory consumption** during command execution, with real-time graph visualization using **JFreeChart**.
- **Automated Command Execution**: Execute PDFBox commands **100 times** to gather performance data under repeated workloads.

---

## **PDFBox Commands Covered**
1. **`export:text`**: Extracts text from PDF documents.
2. **`encrypt`**: Encrypts PDF documents with a password.
3. **`export:image`**: Extracts images from PDF documents.

Each of these commands has been executed repeatedly to measure their impact on system performance and to monitor method invocations.

---

## **Project Setup**

### **Requirements**
- **Java JDK 23** or higher
- **Apache Maven 3.9.9**
- **IntelliJ IDEA** (or any IDE supporting Java)
- **Git** for version control

### **Clone the Repository**

To clone the project from GitHub, run:

```
git clone https://github.com/aliiimaher/Pdfbox-Methods-Invocation-Monitoring.git
cd Pdfbox-Methods-Invocation-Monitoring/pdfbox/tools(modified)
```
### Build the Project
The project uses Maven to manage dependencies and build the project. To build the project, run:

```
mvn clean install
```

Running the PDFBox Commands
You can run the PDFBox commands manually or use the automated scripts to run them 100 times for performance analysis.

Manual Execution Example (Text Export):
```
java -jar target/pdfbox-tools-4.0.0-SNAPSHOT-shaded.jar export:text --input sample.pdf --output output.txt
```
Automated Execution (100 Iterations): The project includes a Java script to execute the commands 100 times. Run the following command:
```
java -cp target/pdfbox-tools-4.0.0-SNAPSHOT-shaded.jar automation.PDFBoxRunner
```

## System Monitoring Setup
This project integrates JMX (Java Management Extensions) and JFreeChart to monitor system properties such as CPU load and memory usage.

Real-Time CPU Monitoring:
The system monitoring class captures CPU usage and memory consumption in real-time and visualizes them using JFreeChart. To view the CPU chart, the system starts monitoring before executing commands and stops after the commands have finished.
Method Invocation Monitoring
This project uses AspectJ to track method invocations at runtime. It logs all methods called during the execution of PDFBox commands, including third-party dependencies like Log4j2 and BouncyCastle.

Sample Method Invocation Log:
```
PDFBox method called: void org.apache.pdfbox.tools.PDFBox.main(String[])
PDFBox method called: void org.apache.pdfbox.tools.ExtractText.extractPages(int, int, PDFTextStripper, PDDocument, Writer, boolean, boolean)
PDFBox method called: void org.apache.pdfbox.tools.Encrypt.encryptFile()
PDFBox method called: void org.apache.pdfbox.tools.ExtractImages.extractImages(PDDocument, int)
```





