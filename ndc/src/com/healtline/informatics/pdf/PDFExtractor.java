package com.healtline.informatics.pdf;

//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: wstephen
 * Date: 10/3/14
 * Time: 2:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class PDFExtractor {

//    public static void main(String[] args) {
//
//        try {
//
//            PdfReader reader = null;
//            try {
//                reader = new PdfReader("/home/wstephen/Documents/IdeaProjects/FDADrugDatabase/files/new.pdf");
//            } catch (IOException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//            System.out.println("This PDF has " + reader.getNumberOfPages() + " pages.");
//            String page = PdfTextExtractor.getTextFromPage(reader,2);
//            System.out.println("Page Content:\n\n" + page + "\n\n");
//            System.out.println("Is this document tampered: " + reader.isTampered());
//            System.out.println("Is this document encrypted: " + reader.isEncrypted());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    public static void main(String[] args) {
        String fileName = "/home/wstephen/Documents/IdeaProjects/FDADrugDatabase/files/new.pdf";
        try {
            extractRequiredPages(fileName);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//        PDFParser parser = null;
//        String parsedText = null;;
//        PDFTextStripper pdfStripper = null;
//        PDDocument pdDoc = null;
//        COSDocument cosDoc = null;
//        File file = new File(fileName);
//        if (!file.isFile()) {
//            System.err.println("File " + fileName + " does not exist.");
//        }
//        try {
//            parser = new PDFParser(new FileInputStream(file));
//        } catch (IOException e) {
//            System.err.println("Unable to open PDF Parser. " + e.getMessage());
//        }
//        try {
//            parser.parse();
//            cosDoc = parser.getDocument();
//            pdfStripper = new PDFTextStripper();
//            pdDoc = new PDDocument(cosDoc);
//            pdfStripper.setStartPage(1);
//            pdfStripper.setEndPage(2);
//            parsedText = pdfStripper.getText(pdDoc).;
//            System.out.println (parsedText);
//        } catch (Exception e) {
//            System.err
//                    .println("An exception occured in parsing the PDF Document."
//                            + e.getMessage());
//        } finally {
//            try {
//                if (cosDoc != null)
//                    cosDoc.close();
//                if (pdDoc != null)
//                    pdDoc.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void extractRequiredPages(String inputPdf) throws Exception {
        try {
            PDDocument document = null;
//            String destinationDir = "OUTPUT DIR GOES HERE";
            // Load the pdf
            document = PDDocument.load(inputPdf);
            List<PDPage> list = document.getDocumentCatalog().getAllPages();
            //System.out.println(list.size());
            // Declare output fileName
            String fileName = "output.pdf";
            // Create output file
            PDDocument newDocument = new PDDocument();
            // Create PDFTextStripper - used for searching the page string
            PDFTextStripper textStripper = new PDFTextStripper();
            // Declare "pages" and "found" variable
            String pages = null;
            List<String> pagesList = new ArrayList<String>();
            int count = 0;
            // Loop through each page and search for "SEARCH STRING". If this doesn't exist
            // ie is the image page, then copy into the new output.pdf.
            for (int i = 1; i < list.size(); i++) {
                boolean found = false;
                // Set textStripper to search one page at a time
                textStripper.setStartPage(i);
                textStripper.setEndPage(i);
                PDPage returnPage = null;
                // Fetch page text and insert into "pages" string
                pages = textStripper.getText(document);
                found = pages.contains("FULL PRESCRIBING INFORMATION: CONTENTS *");
//                if (i != 0) {
                pagesList.add(pages);
                if (found) {
                    break;
//                    }
                }
            }

            //System.out.println(pagesList.size()+"Wilson");
            for (String str : pagesList) {
                //System.out.println(str);

                //System.out.println("=====================================");

                System.out.println(StringUtils.substringBetween(str,
                        "---------------------INDICATIONS AND USAGE--------------------------------",
                        "-------------------DOSAGE AND ADMINISTRATION--------------------------"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch extract image");
        }
    }
}
