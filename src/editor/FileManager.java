package editor;

import javax.swing.*;
import java.io.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) { // avoiding null exception
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                
                textEditor.currentFile = file;
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "Could not open file.");
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                textEditor.currentFile = fileChooser.getSelectedFile(); 
            } else{
                return; 
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile))) {
            
            writer.write(textArea.getText());
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(textEditor, "Could not save file.");
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText(""); 
        textEditor.currentFile = null; 
    }
}
