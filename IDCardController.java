package com.example.idgenerator.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/api/idcard")
public class IDCardController {

    @PostMapping("/generate")
    public String generateIDCard(
            @RequestParam String name,
            @RequestParam String year,
            @RequestParam String section,
            @RequestParam String branch,
            @RequestParam String bloodGroup,
            @RequestParam String enrollment,
            @RequestParam String mobile,
            @RequestParam MultipartFile photo) {

        try {
            // Output PDF path (can be saved elsewhere or returned as byte stream)
            String outputPath = "ID_Card.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            // Fonts
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
            Font labelFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font valueFont = new Font(Font.FontFamily.HELVETICA, 12);

            // Title
            Paragraph title = new Paragraph("STUDENT ID CARD", headerFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f);
            document.add(title);

            // Add image if provided
            if (!photo.isEmpty()) {
                Image profilePic = Image.getInstance(photo.getBytes());
                profilePic.scaleToFit(100, 120);
                profilePic.setAlignment(Element.ALIGN_LEFT);
                document.add(profilePic);
                document.add(new Paragraph(" "));
            }

            // Info fields
            document.add(new Paragraph("Name: " + name, labelFont));
            document.add(new Paragraph("Year: " + year, valueFont));
            document.add(new Paragraph("Section: " + section, valueFont));
            document.add(new Paragraph("Branch: " + branch, valueFont));
            document.add(new Paragraph("Blood Group: " + bloodGroup, valueFont));
            document.add(new Paragraph("Enrollment No.: " + enrollment, valueFont));
            document.add(new Paragraph("Mobile No.: " + mobile, valueFont));

            document.close();
            return "ID Card generated successfully! Saved as: " + outputPath;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating ID card: " + e.getMessage();
        }
    }
}
