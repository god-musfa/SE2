package org.hbrs.se2.project.softwaree.control;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.hbrs.se2.project.softwaree.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class DataExtractionControl {
    @Autowired
    StudentRepository repoS;

    public File createPDFFromStudent(Integer id)  throws IOException {
        StudentDTO student = repoS.findFullStudentByID(id).get();
        student.setSkills(repoS.getSkillsByStudentID(student.getId()).get());
        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage( page );

// Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA;

// Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
        contentStream.beginText();
        contentStream.setFont( font, 12 );
        contentStream.newLineAtOffset(50, 700);
        contentStream.setLeading(14.5f);
        contentStream.showText( "Name: " + student.getFirstName() + " " + student.getLastName() );
        contentStream.newLine();
        contentStream.showText("Geburtsdatum: "+student.getBirthday());
        contentStream.newLine();
        contentStream.showText("Studiengang: " + student.getSubject());
        contentStream.newLine();
        contentStream.showText("Hochschule: " + student.getUniversity());
        contentStream.newLine();
        contentStream.showText("Abschluss: " +student.getDegree());
        contentStream.newLine();
        contentStream.showText("Fähigkeiten: ");
        for(Skill skill: student.getSkills()){
            contentStream.newLine();
            contentStream.showText(skill.getDescription());
        }
        contentStream.endText();

// Make sure that the content stream is closed:
        contentStream.close();
//path where the PDF file will be store
        File file = File.createTempFile("student_"+ student.getId(), ".pdf");
        document.save(file);
//prints the message if the PDF is created successfully

//closes the document
        document.close();
        return file;
    }
    public InputStream getStream(File file) {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden!");
        }

        return stream;
    }
}
