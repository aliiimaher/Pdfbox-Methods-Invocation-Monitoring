/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pdfbox.tools;

import java.awt.GraphicsEnvironment;

import monitoring.SystemMonitor;
import org.apache.pdfbox.debugger.PDFDebugger;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Spec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple wrapper around all the command line utilities included in PDFBox.
 * Used as the main class in the runnable standalone PDFBox jar.
 */
@Command(name="pdfbox",
        subcommandsRepeatable = true,
        customSynopsis = "pdfbox [COMMAND] [OPTIONS]",
        footer = { "See 'pdfbox help <command>' to read about a specific subcommand" },
        versionProvider = Version.class)
public final class PDFBox implements Runnable
{
    @Spec CommandLine.Model.CommandSpec spec;

      /**
     * Main method.
     *
     * @param args command line arguments
     */

      private static final Logger logger = LogManager.getLogger(PDFBox.class);

    public static void main(String[] args)
    {
        // Monitoring
        SystemMonitor monitor = new SystemMonitor();
        monitor.displayChart();
        monitor.startMonitoring();

        // suppress the Dock icon on OS X
        System.setProperty("apple.awt.UIElement", "true");

        CommandLine commandLine = new CommandLine(new PDFBox()).setSubcommandsCaseInsensitive(true);
        if (!GraphicsEnvironment.isHeadless())
        {
            commandLine.addSubcommand("debug", PDFDebugger.class);
        }
        commandLine.addSubcommand("decrypt", Decrypt.class);
        commandLine.addSubcommand("encrypt", Encrypt.class);
        logger.info("Encrypt command added");
        commandLine.addSubcommand("decode", WriteDecodedDoc.class);
        commandLine.addSubcommand("export:images", ExtractImages.class);
        logger.info("Export images command added");
        commandLine.addSubcommand("export:xmp", ExtractXMP.class);
        commandLine.addSubcommand("export:text", ExtractText.class);
        logger.info("Export text command added");
        commandLine.addSubcommand("export:fdf", ExportFDF.class);
        commandLine.addSubcommand("export:xfdf", ExportXFDF.class);
        commandLine.addSubcommand("import:fdf", ImportFDF.class);
        commandLine.addSubcommand("import:xfdf", ImportXFDF.class);
        commandLine.addSubcommand("overlay", OverlayPDF.class);
        commandLine.addSubcommand("print", PrintPDF.class);
        commandLine.addSubcommand("render", PDFToImage.class);
        commandLine.addSubcommand("merge", PDFMerger.class);
        commandLine.addSubcommand("split", PDFSplit.class);
        commandLine.addSubcommand("fromimage", ImageToPDF.class);
        commandLine.addSubcommand("fromtext", TextToPDF.class);
        commandLine.addSubcommand("version", Version.class);
        commandLine.addSubcommand("help", CommandLine.HelpCommand.class);

        commandLine.execute(args);

        monitor.stopMonitoring();

        logger.info("PDFBox application finished.");
    }

    @Override
    public void run()
    {
        throw new ParameterException(spec.commandLine(), "Error: Subcommand required");
    }
}
