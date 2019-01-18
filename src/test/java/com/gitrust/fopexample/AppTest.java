package com.gitrust.fopexample;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    @Test
    public void testGeneratePdf()
    {

        PdfGenerator pdfGenerator = new PdfGenerator(new HashMap<String, String>());
        try (FileOutputStream pdfOutput = new FileOutputStream("cars.pdf")) {
            pdfGenerator.createPdfFile("src/main/resources/data/cars.xml","src/main/resources/xslt/cars-template.xsl",pdfOutput);
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
