//import org.apache.pdfbox.multipdf.PDFMergerUtility;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.File;
//import java.io.IOException;
//
//public class PDFMergerApp {
//    public static void main(String[] args) {
//        // Create the main window with your name and progress bar
//        JFrame frame = new JFrame("PDF Merger - Noor Elahi Ali Shibly");
//        frame.setSize(600, 300);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//
//        // Label for name at the top
//        JLabel nameLabel = new JLabel("Noor Elahi Ali Shibly", SwingConstants.CENTER);
//        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        frame.add(nameLabel, BorderLayout.NORTH);
//
//        // Panel to hold the button and progress bar
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//
//        // Button to open file chooser
//        JButton mergeButton = new JButton("Merge PDF Files");
//        panel.add(mergeButton, BorderLayout.NORTH);
//
//        // Progress bar to show merging progress
//        JProgressBar progressBar = new JProgressBar();
//        progressBar.setStringPainted(true);
//        panel.add(progressBar, BorderLayout.CENTER);
//
//        // Add panel to frame
//        frame.add(panel, BorderLayout.CENTER);
//
//        // Show the frame
//        frame.setVisible(true);
//
//        mergeButton.addActionListener(e -> {
//            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.setMultiSelectionEnabled(true); // Allow multiple file selection
//            int result = fileChooser.showOpenDialog(frame);
//
//            if (result == JFileChooser.APPROVE_OPTION) {
//                File[] selectedFiles = fileChooser.getSelectedFiles(); // Get selected files
//
//                if (selectedFiles.length < 2) {
//                    JOptionPane.showMessageDialog(frame, "Select at least two PDF files to merge.");
//                    return;
//                }
//
//                // Update progress bar
//                progressBar.setValue(0);
//                progressBar.setMaximum(selectedFiles.length);
//
//                try {
//                    // Create PDFMergerUtility
//                    PDFMergerUtility merger = new PDFMergerUtility();
//
//                    // Add all selected PDF files to the merger utility
//                    for (int i = 0; i < selectedFiles.length; i++) {
//                        merger.addSource(selectedFiles[i]);
//                        progressBar.setValue(i + 1); // Update progress bar
//                    }
//
//                    // Set the output file path
//                    File outputFile = new File("merged_output.pdf");
//                    merger.setDestinationFileName(outputFile.getAbsolutePath());
//
//                    // Merge the PDFs
//                    merger.mergeDocuments(null);
//                    progressBar.setValue(selectedFiles.length); // Ensure progress bar reaches 100%
//                    JOptionPane.showMessageDialog(frame, "PDFs merged successfully! Output saved as 'merged_output.pdf'.");
//
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(frame, "Error merging PDFs.");
//                }
//            }
//        });
//    }
//}
