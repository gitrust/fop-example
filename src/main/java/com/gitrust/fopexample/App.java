package com.gitrust.fopexample;

import org.apache.commons.cli.*;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Main application class
 */
public class App 
{
    public static void main( String[] args )
    {
        // Parse options
        CommandLine cmd = parseCli(args);

        PdfGenerator pdfGenerator = new PdfGenerator(new HashMap<String, String>());
        try (FileOutputStream pdfOutput = new FileOutputStream(cmd.getOptionValue("datafile"))) {
            pdfGenerator.createPdfFile(cmd.getOptionValue("datafile"),cmd.getOptionValue("stylefile"),pdfOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);

    }

    private static CommandLine parseCli(String[] args) {
        Options options = new Options();

        Option style = new Option("s", "stylefile", true, "style file path");
        style.setRequired(true);
        options.addOption(style);

        Option xml = new Option("d", "datafile", true, "data xml file path");
        xml.setRequired(true);
        options.addOption(xml);

        Option output = new Option("o", "output", true, "output file");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new BasicParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            return  parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("App", options);

            System.exit(1);
        }
        return null;
    }
}
