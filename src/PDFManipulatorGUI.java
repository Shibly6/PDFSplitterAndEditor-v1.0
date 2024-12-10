import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PDFManipulatorGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PDF Manipulator - Noor Elahi Ali Shibly");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(6, 1, 10, 10));

            JLabel label = new JLabel("PDF Manipulator by Noor Elahi Ali Shibly");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
            panel.add(progressBar);

            JButton splitButton = new JButton("Split PDF");
            panel.add(splitButton);

            JButton addPageButton = new JButton("Add Page");
            panel.add(addPageButton);

            JButton replacePageButton = new JButton("Replace Page");
            panel.add(replacePageButton);

            JButton saveButton = new JButton("Save Changes");
            panel.add(saveButton);

            frame.add(panel);
            frame.setVisible(true);

            final PDDocument[] document = new PDDocument[1];

            // Load PDF File
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    document[0] = Loader.loadPDF(file);
                    JOptionPane.showMessageDialog(frame, "PDF Loaded Successfully!");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Failed to load PDF file.");
                }
            }

            // Split PDF Button Action
            splitButton.addActionListener(e -> {
                if (document[0] == null) {
                    JOptionPane.showMessageDialog(frame, "No PDF loaded.");
                    return;
                }

                String startPageStr = JOptionPane.showInputDialog(frame, "Enter Start Page:");
                String endPageStr = JOptionPane.showInputDialog(frame, "Enter End Page:");

                try {
                    int startPage = Integer.parseInt(startPageStr);
                    int endPage = Integer.parseInt(endPageStr);

                    PDDocument splitDoc = new PDDocument();
                    for (int i = startPage - 1; i < endPage; i++) {
                        splitDoc.addPage(document[0].getPages().get(i));
                    }

                    JFileChooser saveChooser = new JFileChooser();
                    if (saveChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                        splitDoc.save(saveChooser.getSelectedFile());
                        JOptionPane.showMessageDialog(frame, "PDF Split and Saved Successfully!");
                    }
                    splitDoc.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error during splitting.");
                }
            });

            // Add Page Button Action
            addPageButton.addActionListener(e -> {
                if (document[0] == null) {
                    JOptionPane.showMessageDialog(frame, "No PDF loaded.");
                    return;
                }

                JFileChooser addChooser = new JFileChooser();
                int addResult = addChooser.showOpenDialog(frame);
                if (addResult == JFileChooser.APPROVE_OPTION) {
                    File file = addChooser.getSelectedFile();
                    try {
                        PDPage newPage = new PDPage();
                        document[0].addPage(newPage);

                        PDPageContentStream contentStream = new PDPageContentStream(document[0], newPage);
                        contentStream.beginText();
                        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
                        contentStream.setNonStrokingColor(Color.RED);
                        contentStream.newLineAtOffset(450, 750);
                        contentStream.showText("Added");
                        contentStream.endText();
                        contentStream.close();

                        JOptionPane.showMessageDialog(frame, "Page Added Successfully!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error adding page.");
                    }
                }
            });

            // Replace Page Button Action
            replacePageButton.addActionListener(e -> {
                if (document[0] == null) {
                    JOptionPane.showMessageDialog(frame, "No PDF loaded.");
                    return;
                }

                String replacePageStr = JOptionPane.showInputDialog(frame, "Enter Page Number to Replace:");
                int replacePage = Integer.parseInt(replacePageStr);

                JFileChooser replaceChooser = new JFileChooser();
                int replaceResult = replaceChooser.showOpenDialog(frame);
                if (replaceResult == JFileChooser.APPROVE_OPTION) {
                    File replaceFile = replaceChooser.getSelectedFile();
                    try {
                        PDPage replacementPage = new PDPage();
                        document[0].removePage(replacePage - 1);
                        document[0].getPages().insertBefore(replacementPage, document[0].getPages().get(replacePage - 1));

                        PDPageContentStream contentStream = new PDPageContentStream(document[0], replacementPage);
                        contentStream.beginText();
                        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
                        contentStream.setNonStrokingColor(Color.RED);
                        contentStream.newLineAtOffset(450, 750);
                        contentStream.showText("Updated");
                        contentStream.endText();
                        contentStream.close();

                        JOptionPane.showMessageDialog(frame, "Page Replaced Successfully!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error replacing page.");
                    }
                }
            });

            // Save Changes Button Action
            saveButton.addActionListener(e -> {
                if (document[0] == null) {
                    JOptionPane.showMessageDialog(frame, "No PDF loaded.");
                    return;
                }

                JFileChooser saveChooser = new JFileChooser();
                if (saveChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    try {
                        document[0].save(saveChooser.getSelectedFile());
                        JOptionPane.showMessageDialog(frame, "PDF Saved Successfully!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error saving PDF.");
                    }
                }
            });
        });
    }
}
