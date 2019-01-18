package com.gitrust.fopexample;

import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        PdfGenerator pdfGenerator = new PdfGenerator(new HashMap<String, String>());
        try (FileOutputStream pdfOutput = new FileOutputStream("cars.pdf")) {
            pdfGenerator.createPdfFile("src/main/resources/xslt/cars-template.xsl","src/main/resources/data/cars.xml",pdfOutput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}
