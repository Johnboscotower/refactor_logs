package com.example.model;

import com.example.view.UserInterface;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CreateReport {


    private CreateReport(){}

    public static File createReport(String fileName, String fileBody) {
        Logger logJava = Logger.getLogger(CreateReport.class);
        try {
            logJava.info("Report was created successfully");
            File fichero = new File(fileName);

            PdfWriter pdfWriter = new PdfWriter(fichero);

            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

            Paragraph p = new Paragraph(fileBody);

            document.add(p);

            document.close();
            pdfWriter.close();
            pdfDocument.close();

            return fichero;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            logJava.error("File not found");

        } catch (IOException e) {
            System.out.println("File couldn't be created");
            logJava.error("File report couldn't be created");
        }
        return null;
    }
}
