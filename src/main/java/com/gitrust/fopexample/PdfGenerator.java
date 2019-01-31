package com.gitrust.fopexample;

import org.apache.fop.apps.*;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Generate a PDF file using XML data and XSLT stylesheets
 */
public class PdfGenerator {

    private final Map<String, String> configuration;

    public PdfGenerator(Map<String, String> configuration) {
        this.configuration = configuration;
    }

    public void createPdfFile(String xmlDataFile, String templateFile, OutputStream pdfOutputStream) throws IOException, SAXException, TransformerException {
        System.out.println("Create pdf file ...");
        File tempFile = File.createTempFile("fop-" + System.currentTimeMillis(), ".pdf");

        //  holds references to configuration information and cached data
        //  reuse this instance if you plan to render multiple documents
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        try {
            // set output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, pdfOutputStream);

            // Load template
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(templateFile)));

            // Set value of parameters in stylesheet
            transformer.setParameter("version", "1.0");

            // Input for XSLT transformations
            Source xmlSource = new StreamSource(new File(xmlDataFile));

            Result result = new SAXResult(fop.getDefaultHandler());

            transformer.transform(xmlSource, result);
        } finally {
            tempFile.delete();
        }
    }

}
